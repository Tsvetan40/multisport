package com.demo.multisport.controllers;

import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.entities.User;
import com.demo.multisport.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public void justTest() {
        User user = new User("Tsvetan", "Gabrovski", "test@test.com", "password", 23 );
        System.out.println("hit");
        userService.saveUser(user);
    }
}
