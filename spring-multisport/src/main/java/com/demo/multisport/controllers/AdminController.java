package com.demo.multisport.controllers;


import com.demo.multisport.entities.page.Article;
import com.demo.multisport.entities.user.LoggedUser;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.ArticleDuplicateException;
import com.demo.multisport.services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("multisport/admin")
@Slf4j
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }



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

    @DeleteMapping("/articles")
    public ResponseEntity<Optional<Article>> deleteArticle(@RequestParam(required = true, name = "title") String title,
                                                           HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }

        adminService.deleteArticle(title);
        return new ResponseEntity<>(Optional.of(new Article()), HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getArticlesAdmin(HttpSession session) {
        User admin = (User) session.getAttribute("user");

        log.info("@GetMapping");
        log.info(session.getId());
        log.info("admin= " + admin);

        if (admin == null) {
            return new ResponseEntity<>( List.of(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>( adminService.getAllArticles(), HttpStatus.OK);
    }

    @PostMapping("/articles/newarticle")
    public ResponseEntity<Optional<Article>> postArticle(@RequestBody @Valid Article article,
                                                         HttpSession session) {
        if (((User)session.getAttribute("user")) == null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }
        log.info("PostMapping " + session.getId());
        try {
            adminService.addArticle(article.withPublishedAt());
            return new ResponseEntity<>(Optional.of(article), HttpStatus.OK);
        } catch (ArticleDuplicateException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }

}
