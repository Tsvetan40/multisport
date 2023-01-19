package com.demo.multisport.mapper;

import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArticleMapper {

    Article articleDtoToArticle(ArticleDto articleDto);
    ArticleDto articleToArticleDto(Article article);
}
