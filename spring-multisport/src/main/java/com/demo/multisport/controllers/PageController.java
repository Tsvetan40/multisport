package com.demo.multisport.controllers;


import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.center.ICenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.services.page.PageServiceImpl;
import com.demo.multisport.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/multisport")
public class PageController {

    private final UserService userService;
    private final PageServiceImpl pageService;
    private final String SPORT_CENTER_TYPE = "SportCenter";
    private final String RELAX_CENTER_TYPE = "RelaxCenter";

    @Autowired
    public PageController(PageServiceImpl pageService, UserService userService) {
        this.pageService = pageService;
        this.userService = userService;
    }


    @GetMapping("/news/{title}")
    public ResponseEntity<Optional<ArticleDto>> getSpecificArticle(@PathVariable(value = "title", required = true) String title) {

        try {
            Optional<ArticleDto> articleDto = pageService.getArticleByTitle(title);
            return new ResponseEntity<>(articleDto, HttpStatus.OK);
        } catch (NoSuchArticleException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/news")
    public ResponseEntity<List<ArticleDto>> getAllArticlesTitles() {
        try {
            return new ResponseEntity<>(pageService.getAllArticlesTitlesAndImages(), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/sport-centers/{id}")
    public ResponseEntity<Optional<CenterDto>> getSportCenter(@PathVariable(value = "id", required = true) Long id) {
        try {
            CenterDto centerDto = pageService.getCenterDtoById(id);
            if (!centerDto.getCenterType().getCenter().equals(SPORT_CENTER_TYPE)) {
                return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(Optional.of(centerDto), HttpStatus.OK);
        } catch (CenterNotFoundException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/relax-centers/{id}")
    public ResponseEntity<Optional<CenterDto>> getRelaxCenter(@PathVariable(value = "id", required = true) Long id) {
        try {
            CenterDto centerDto = pageService.getCenterDtoById(id);
            if (!centerDto.getCenterType().getCenter().equals(RELAX_CENTER_TYPE)) {
                return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
            }

           return new ResponseEntity<>(Optional.of(centerDto), HttpStatus.OK);
        } catch (CenterNotFoundException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);

        }
 }

    @GetMapping("/sport-centers")
    public ResponseEntity<Set<ICenterDto>> getAllSportCenters() {
        return new ResponseEntity<>(pageService.getAllSportCenters(), HttpStatus.OK);
    }

    @GetMapping("/relax-centers")
    public ResponseEntity<Set<ICenterDto>> getAllRelaxCenters() {
        return new ResponseEntity<>(pageService.getAllRelaxCenters(), HttpStatus.OK);
    }

    private CommentDto addInfoCommentDto(CommentDto commentDto, String email) {
        commentDto.setPublishedAt(LocalDateTime.now());
        commentDto.setEmail(email);
        return commentDto;
    }

    @PostMapping("news/{title}")
    public ResponseEntity<CommentDto> addCommentArticle(@PathVariable("title") String title,
                                                        @RequestBody CommentDto comment,
                                                        Authentication authentication) {
        if (userService.isUserBlocked(authentication.getName())) {
            return new ResponseEntity<>(comment, HttpStatus.FORBIDDEN);
        }

        addInfoCommentDto(comment, authentication.getName());
        try {
            return new ResponseEntity<>(pageService.addComment(comment), HttpStatus.CREATED);
        } catch (InvalidParameterException e) {
            return new ResponseEntity<>(comment, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("relax-centers/{id}")
    public ResponseEntity<CommentDto> addCommentRelaxCenter(@PathVariable("id") Long id,
                                                            @RequestBody CommentDto comment,
                                                            Authentication authentication) {
        if (userService.isUserBlocked(authentication.getName())) {
            return new ResponseEntity<>(comment, HttpStatus.FORBIDDEN);
        }

        addInfoCommentDto(comment, authentication.getName());

        try {
            return new ResponseEntity<>(pageService.addComment(comment), HttpStatus.CREATED);
        } catch (InvalidParameterException e) {
            return new ResponseEntity<>(comment, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("sport-centers/{id}")
    public ResponseEntity<CommentDto> addCommentSportCenter(@PathVariable("id") Long id,
                                                            @RequestBody CommentDto comment,
                                                            Authentication authentication) {
        if (userService.isUserBlocked(authentication.getName())) {
            return new ResponseEntity<>(comment, HttpStatus.FORBIDDEN);
        }

        addInfoCommentDto(comment, authentication.getName());
        try {
            return new ResponseEntity<>(pageService.addComment(comment), HttpStatus.CREATED);
        } catch (InvalidParameterException e) {
            return new ResponseEntity<>(comment, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("rating/{id}")
    public ResponseEntity<Double> rateCenter(@PathVariable("id") Long centerId,
                                             @RequestBody Integer rating) {
        if (rating == null || rating < 1 || rating > 5) {
            return new ResponseEntity<Double>((double)rating, HttpStatus.BAD_REQUEST);
        }

        try {
            double newRating = pageService.rate(rating, centerId);
            return new ResponseEntity<Double>(newRating, HttpStatus.OK);
        } catch (InvalidParameterException | CenterNotFoundException e) {
            return new ResponseEntity<Double>((double)rating, HttpStatus.BAD_REQUEST);
        }

    }
}
