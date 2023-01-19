package com.demo.multisport.controllers;


import com.demo.multisport.dto.user.LoggedUserDto;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.UserDuplicateException;
import com.demo.multisport.exceptions.UserNotFoundException;
import com.demo.multisport.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("multisport")
@Slf4j
public class AuthenticationController {
    private static final int MAX_INACTIVE_INTERVAL  = 60*10;
    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<Optional<UserDto>> login(@RequestBody @Valid LoggedUserDto loggedUser,
                                                BindingResult bindingResult,
                                                HttpSession session) {
        if (bindingResult.hasErrors()) {
            Optional<UserDto> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }

        log.info(session.getId());
        log.info("session= " + ((UserDto)session.getAttribute("user")));

        UserDto user;
        try {
            user = userService.loginUser(loggedUser.getEmail(), loggedUser.getPassword());
            if (session.getAttribute("user") == null) {
                session.setAttribute("user", user);
            }

            log.info("Logged info successfully");
            session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
            return new ResponseEntity<>(Optional.of(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            Optional<UserDto> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }
    }

    @PostMapping("/newuser")
    public ResponseEntity<Optional<UserDto>> newUserRegistration(@RequestBody @Valid UserDto userDto, BindingResult error,
                                                              HttpSession session) {
        if (error.hasErrors()) {
            Optional<UserDto> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }

        User registeredUser;
        try {
            registeredUser = userService.registerUser(userDto);
            log.info("Logged info successfully");
            session.setAttribute("user", userDto);
            log.info(session.getId());
            session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
            return new ResponseEntity<>(Optional.of(userDto), HttpStatus.OK);
        } catch (UserDuplicateException e) {
            Optional<UserDto> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<Optional<UserDto>> logout(HttpSession session) {
        Optional<UserDto> user = Optional.empty();
        if (session.getAttribute("user") != null) {
            user = Optional.of((UserDto)session.getAttribute("user"));
        }

        session.invalidate();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Optional<User>> authentication(HttpSession session) {
        log.info("Hit check if has session");
        if (session.getAttribute("user") != null) {
            return new ResponseEntity<>(Optional.of((User)session.getAttribute("user")), HttpStatus.OK);
        }

        return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
    }

}
