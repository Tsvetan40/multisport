package com.demo.multisport.controllers;


import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.exceptions.CenterDuplicateException;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.exceptions.article.ArticleDuplicateException;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.services.user.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("multisport/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminPageController {

    private final AdminService adminService;

    @DeleteMapping("/articles")
    public ResponseEntity<String> deleteArticle(@RequestParam(required = true, name = "title") String title,
                                                              HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ResponseEntity<>("Unauthorized user", HttpStatus.UNAUTHORIZED);
        }
        try {
            adminService.deleteArticle(title);
            return new ResponseEntity<>("Article with title " + title + "deleted", HttpStatus.OK);
        } catch (NoSuchArticleException e) {
            return new ResponseEntity<>("Article with title " + title + " doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/articles")
    public ResponseEntity<List<String>> getArticlesAdmin(HttpSession session) {
        UserDto admin = (UserDto) session.getAttribute("user");

        log.info("@GetMapping articles");
        log.info(session.getId());
        log.info("admin= " + admin);

        if (admin == null) {
            return new ResponseEntity<>( List.of(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>( adminService.getAllArticlesTitle(), HttpStatus.OK);
    }

    @PostMapping("/articles/newarticle")
    public ResponseEntity<Optional<ArticleDto>> postArticle(@RequestPart String title,
                                                            @RequestPart String content,
                                                            @RequestPart MultipartFile picture,
                                                            HttpSession session) {
        ArticleDto articleDto = null;

        try {
            articleDto = new ArticleDto(title, content);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }

        if (session.getAttribute("user") == null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }

        log.info("PostMapping " + session.getId());
        try {
            adminService.addArticle(articleDto, picture);
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
