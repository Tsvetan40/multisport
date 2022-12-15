package com.demo.multisport.controllers;


import com.demo.multisport.entities.User;
import com.demo.multisport.exceptions.UserDuplicateException;
import com.demo.multisport.exceptions.UserNotFoundException;
import com.demo.multisport.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("multisport")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(HttpServletRequest request) {

        System.out.println("Hit!");
        return new ResponseEntity<Optional<User>>(Optional.of(new User()), HttpStatus.OK);
    }

    @PostMapping("/newuser")
    public ResponseEntity<Optional<User>> newUserRegistration(@RequestBody @Valid User user, BindingResult error) {
        if (error.hasErrors()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
        }

        Optional<User> newUser = Optional.empty();
        try {
            newUser = userService.registerUser(user);
        } catch (UserDuplicateException ignored) {

        }

        return new ResponseEntity<Optional<User>>(newUser, HttpStatus.OK);
    }
}
