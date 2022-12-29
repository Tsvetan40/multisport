package com.demo.multisport.controllers;


import com.demo.multisport.entities.page.Article;
import com.demo.multisport.entities.user.LoggedUser;
import com.demo.multisport.entities.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("multisport/admin")
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

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getArticlesAdmin(HttpSession session) {
        User admin = (User) session.getAttribute("user");
        log.info(session.getId());
        log.info("admin= " + admin);


        if (admin == null) {
            return new ResponseEntity<>( List.of(), HttpStatus.UNAUTHORIZED);
        }

        //get All Articles

        return new ResponseEntity<>( List.of(), HttpStatus.UNAUTHORIZED);

    }
}
