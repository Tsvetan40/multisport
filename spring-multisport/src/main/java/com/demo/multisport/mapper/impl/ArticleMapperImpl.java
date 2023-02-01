package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.utils.MultipartUtilImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleMapperImpl implements com.demo.multisport.mapper.ArticleMapper {

    @Override
    public Article articleDtoToArticle(ArticleDto articleDto) {
        return Article
                .builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .publishedAt(articleDto.getPublishedAt())
                .comments(new HashSet<>(articleDto.getComments()))
                .build();

    }

    @Override
    public ArticleDto articleToArticleDto(Article article) throws IOException {
        return ArticleDto
                .builder()
                .title(article.getTitle())
                .comments(List.copyOf(article.getComments()))
                .content(article.getContent())
                .publishedAt(article.getPublishedAt())
                .imageBase64(MultipartUtilImpl.convertFileToBase64(article.getFilePath()))
                .build();
    }
}
