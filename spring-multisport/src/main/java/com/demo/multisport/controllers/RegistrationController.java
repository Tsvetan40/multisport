package com.demo.multisport.controllers;

import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.entities.User;
import com.demo.multisport.exceptions.UserDuplicateException;
import com.demo.multisport.exceptions.UserNotFoundException;
import com.demo.multisport.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Optional<User>> login(@RequestBody User user) {

        Optional<User> newUser = Optional.empty();
        try {
            newUser = userService.loginUser(user);
        } catch (UserNotFoundException e) {
            System.out.println("User is null, already exists");
        }

        return new ResponseEntity<Optional<User>>(newUser, HttpStatus.OK);
    }
}
