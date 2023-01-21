package com.demo.multisport.mapper;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dao.RatingRepository;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.entities.page.Rating;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ArticleMapperTest {

    ArticleMapper articleMapper;
    CenterRepository  centerRepository;
    RatingRepository ratingRepository;

    @Autowired
    ArticleMapperTest(ArticleMapper articleMapper, CenterRepository centerRepository, RatingRepository ratingRepository) {
        this.articleMapper = articleMapper;
        this.centerRepository = centerRepository;
        this.ratingRepository = ratingRepository;
    }

    @Test
    void articleToArticleDtoTest(){
        Article article = new Article("title", "content", null);
        ArticleDto dto = articleMapper.articleToArticleDto(article);

        Assertions.assertEquals(dto.getTitle(), article.getTitle());
        Assertions.assertEquals(dto.getContent(),article.getContent());
        Assertions.assertTrue(article.getPublishedAt() == dto.getPublishedAt());
    }

    @Test
    void articleDtoToArticle(){
        ArticleDto dto = new ArticleDto("title", "content", null);
        Article article = articleMapper.articleDtoToArticle(dto);

        Assertions.assertEquals(dto.getTitle(), article.getTitle());
        Assertions.assertEquals(dto.getContent(),article.getContent());
        Assertions.assertTrue(article.getPublishedAt() == dto.getPublishedAt());
    }
}
