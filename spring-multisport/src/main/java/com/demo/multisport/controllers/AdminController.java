package com.demo.multisport.controllers;


import com.demo.multisport.entities.user.LoggedUser;
import com.demo.multisport.entities.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("multisport/manageusers")
@Slf4j
public class AdminController {

    @GetMapping("")
    public ResponseEntity<Optional<User>> manageUsersAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        log.info(session.getId());
        log.info("manage users= " + user);
        if (user == null) {
            Optional<User> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }
        if (!user.getEmail().contains("@multisport.com")) {
            Optional<User> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of(user), HttpStatus.OK);
    }


}
