package com.demo.multisport.controllers;


import com.demo.multisport.entities.user.LoggedUser;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.UserDuplicateException;
import com.demo.multisport.exceptions.UserNotFoundException;
import com.demo.multisport.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("multisport")
//@SessionAttributes({"user"})
@Slf4j
public class RegistrationController {

//    @ModelAttribute("user")
//    public User user() {
//        return new User();
//    }

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestBody @Valid LoggedUser loggedUser1, BindingResult bindingResult,
                                                        HttpSession session) {
        if (bindingResult.hasErrors()) {
            Optional<User> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }
        log.info(session.getId());
        log.info("session= " + ((User)session.getAttribute("user")));

        User user;
        try {
            user = userService.loginUser(loggedUser1.getEmail(), loggedUser1.getPassword());
            if (session.getAttribute("user") == null) {
                session.setAttribute("user", user);
            }
            log.info("Logged info successfully");
        } catch (UserNotFoundException e) {
            Optional<User> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of(user), HttpStatus.OK);
    }

    @PostMapping("/newuser")
    public ResponseEntity<Optional<User>> newUserRegistration(@RequestBody @Valid User newUser, BindingResult error,
                                                               HttpSession session) {
        if (error.hasErrors()) {
            Optional<User> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }

        User registeredUser;
        try {
            registeredUser = userService.registerUser(newUser);
            log.info("Logged info successfully");
            session.setAttribute("user", registeredUser);
            log.info(session.getId());
            return new ResponseEntity<>(Optional.of(registeredUser), HttpStatus.OK);
        } catch (UserDuplicateException e) {
            Optional<User> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }

    }

}
