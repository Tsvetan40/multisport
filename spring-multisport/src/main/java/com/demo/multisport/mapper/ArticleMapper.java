package com.demo.multisport.mapper;

import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.utils.FileUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

@Component
public class ArticleMapper {

    public Article articleDtoToArticle(ArticleDto articleDto, String pathFile) {
        return Article
                .builder()
                .title(articleDto.getTitle())
                .comments(new HashSet<>(articleDto.getComments()))
                .publishedAt(articleDto.getPublishedAt())
                .content(articleDto.getContent())
                .pathFile(pathFile)
                .build();
    }

    public ArticleDto articleToFullArticleDto(Article article) throws IOException {
        return ArticleDto
                .builder()
                .title(article.getTitle())
                .content(article.getContent())
                .publishedAt(article.getPublishedAt())
                .comments(new LinkedList<>(article.getComments()))
                .pictureBase64(FileUtil.convertFileToBase64(article.getPathFile()))
                .build();
    }

    public ArticleDto articleToPartArticleDto(Article article) throws IOException {
        return ArticleDto
                .builder()
                .title(article.getTitle())
                .publishedAt(article.getPublishedAt())
                .pictureBase64(FileUtil.convertFileToBase64(article.getPathFile()))
                .build();
    }
}
