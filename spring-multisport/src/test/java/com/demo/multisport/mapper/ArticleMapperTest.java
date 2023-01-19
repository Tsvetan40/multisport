package com.demo.multisport.mapper;

import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleMapperTest {

    ArticleMapper articleMapper;

    @Autowired
    ArticleMapperTest(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
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
