package com.demo.multisport.controllers;


import com.demo.multisport.entities.LoggedUser;
import com.demo.multisport.entities.User;
import com.demo.multisport.exceptions.UserDuplicateException;
import com.demo.multisport.exceptions.UserNotFoundException;
import com.demo.multisport.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("multisport")
@SessionAttributes("user")
@Slf4j
public class RegistrationController {

    @ModelAttribute
    public User user() {
        return new User();
    }

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoggedUser> login(@RequestBody @Valid LoggedUser user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }

        User loggedUser;
        try {
            loggedUser = userService.loginUser(user.getEmail(), user.getPassword());
            session.setAttribute("user", loggedUser);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/newuser")
    public ResponseEntity<User> newUserRegistration(@RequestBody @Valid User user, BindingResult error, HttpSession session) {
        if (error.hasErrors()) {
            return new ResponseEntity<>(new User().withEmail(user.getEmail()).withPassword(user.getPassword()),
                                        HttpStatus.BAD_REQUEST);
        }

        User registeredUser;
        try {
            registeredUser = userService.registerUser(user);
            session.setAttribute("user", registeredUser);
            return new ResponseEntity<>(registeredUser, HttpStatus.OK);
        } catch (UserDuplicateException e) {
            return new ResponseEntity<>(new User().withPassword(user.getPassword()).withEmail(user.getEmail()),
                    HttpStatus.BAD_REQUEST);
        }

    }

}
