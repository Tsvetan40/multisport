package com.demo.multisport.controllers;


import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.exceptions.article.ArticleDuplicateException;
import com.demo.multisport.exceptions.CenterDuplicateException;
import com.demo.multisport.services.user.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("multisport/admin")
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("")
    public ResponseEntity<Optional<UserDto>> manageUsersAdmin(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");

        log.info(session.getId());
        log.info("manage users= " + user);

        if (user == null || !user.getEmail().contains("@multisport.com")) {
            Optional<UserDto> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }

        return new ResponseEntity<>(Optional.of(user), HttpStatus.OK);
    }

    @DeleteMapping("/articles")
    public ResponseEntity<Optional<ArticleDto>> deleteArticle(@RequestParam(required = true, name = "title") String title,
                                                              HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(Optional.of(adminService.deleteArticle(title)), HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<String>> getArticlesAdmin(HttpSession session) {
        User admin = (User) session.getAttribute("user");

        log.info("@GetMapping articles");
        log.info(session.getId());
        log.info("admin= " + admin);

        if (admin == null) {
            return new ResponseEntity<>( List.of(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>( adminService.getAllArticlesTitle(), HttpStatus.OK);
    }

    @PostMapping("/articles/newarticle")
    public ResponseEntity<Optional<ArticleDto>> postArticle(@RequestBody @Valid ArticleDto articleDto,
                                                         HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }

        log.info("PostMapping " + session.getId());
        try {
            adminService.addArticle(articleDto);
            return new ResponseEntity<>(Optional.of(articleDto), HttpStatus.OK);
        } catch (ArticleDuplicateException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/centers")
    public ResponseEntity<String> deleteCenter(@RequestParam(name = "address", required = true) String address,
                                                            HttpSession session) {
        //to do authorization

        try {
            adminService.deleteCenter(address);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (CenterNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

//    @GetMapping("/centers")
//    public ResponseEntity<List<CenterDto>> adminGetAllCenters(HttpSession session) {
//        // to do validate session
//
//        System.out.println("HIT GetMapping centers");
//
//        return new ResponseEntity<>(adminService.getAllCenters(), HttpStatus.OK);
//    }

    @PostMapping("/centers/newcenter")
    public ResponseEntity<Optional<CenterDto>> addCenter(@RequestBody @Valid CenterDto centerDto, HttpSession session) {

        //to do session authorization
        log.info("hit");
        try {
            adminService.addCenter(centerDto);
            return new ResponseEntity<>(Optional.of(centerDto), HttpStatus.OK);
        } catch (CenterDuplicateException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

    }

}
