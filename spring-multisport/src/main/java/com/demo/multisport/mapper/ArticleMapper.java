package com.demo.multisport.mapper;

import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;

import java.io.IOException;

public interface ArticleMapper {

    Article articleDtoToArticle(ArticleDto articleDto);
    ArticleDto articleToArticleDto(Article article) throws IOException;
}
