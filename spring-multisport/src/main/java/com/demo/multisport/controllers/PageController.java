package com.demo.multisport.controllers;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.services.page.PageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/multisport")
public class PageController {

    private final PageServiceImpl pageService;

    @Autowired
    public PageController(PageServiceImpl pageService) {
        this.pageService = pageService;
    }


    @GetMapping("/articles/{title}")
    public ResponseEntity<Optional<ArticleDto>> getSpecificArticle(@PathVariable(value = "title", required = true) String title) {

        //can't find the article return error page in angular
        try {
            Optional<ArticleDto> articleDto = pageService.getArticleByTitle(title);
            return new ResponseEntity<>(articleDto, HttpStatus.OK);
        } catch (NoSuchArticleException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    //get the titles and then navigate to them
    @GetMapping("/articles")
    public ResponseEntity<List<String>> getAllArticles() {
        return new ResponseEntity<>(pageService.getAllArticlesTitles(), HttpStatus.OK);
    }


    @GetMapping("/plans")
    public ResponseEntity<Set<PlanDto>> getAllPlans() {
        return new ResponseEntity<>(pageService.getAllPlans(), HttpStatus.OK);
    }

    @GetMapping("/sport-centers")
    public ResponseEntity<Set<CenterDto>> getAllSportCenters() {
        return new ResponseEntity<>(pageService.getAllSportCenters(), HttpStatus.OK);
    }

    @GetMapping("/relax-centers")
    public ResponseEntity<Set<CenterDto>> getAllRelaxCenters() {
        return new ResponseEntity<>(pageService.getAllRelaxCenters(), HttpStatus.OK);
    }
}
