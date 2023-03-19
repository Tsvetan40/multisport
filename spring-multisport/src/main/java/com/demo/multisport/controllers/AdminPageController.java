package com.demo.multisport.controllers;


import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
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
import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("multisport/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminPageController {

    private final AdminService adminService;

    @DeleteMapping("/articles")
    public ResponseEntity<HttpStatus> deleteArticle(@RequestParam(required = true, name = "title") String title) {

        try {
            adminService.deleteArticle(title);
            return ResponseEntity.ok().build();
        } catch (NoSuchArticleException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getAllArticles() {

        try {
            return new ResponseEntity<>(adminService.getAllArticlesTitlesAndImages(), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/articles/newarticle")
    public ResponseEntity<Optional<ArticleDto>> postArticle(@RequestPart String title,
                                                            @RequestPart String content,
                                                            @RequestPart MultipartFile picture) {

        if (title.length() > 50 || title.length() < 4) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        try {
            ArticleDto articleDto = new ArticleDto(title, content);
            adminService.addArticle(articleDto, picture);

            return new ResponseEntity<>(Optional.of(articleDto), HttpStatus.CREATED);
        } catch (ArticleDuplicateException | InvalidParameterException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/centers")
    public ResponseEntity<String> deleteCenter(@RequestParam(name = "address", required = true) String address) {

        try {
            adminService.deleteCenter(address);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (CenterNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

    @PostMapping("/centers/newcenter")
    public ResponseEntity<Optional<CenterDto>> addCenter(@RequestBody @Valid CenterDto centerDto) {

        try {
            adminService.addCenter(centerDto);
            return new ResponseEntity<>(Optional.of(centerDto), HttpStatus.CREATED);
        } catch (CenterDuplicateException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

    }

}
