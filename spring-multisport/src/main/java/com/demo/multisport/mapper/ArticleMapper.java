package com.demo.multisport.mapper;

import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ArticleMapper {

    private final CommentMapper commentMapper;

    public Article articleDtoToArticle(ArticleDto articleDto, String pathFile) {
        return Article
                .builder()
                .title(articleDto.getTitle())
                .comments(articleDto.getComments() != null ?
                                                            articleDto.getComments()
                                                            .stream()
                                                            .map(commentMapper::commentDtoToComment)
                                                            .collect(Collectors.toSet())
                                                    : null)
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
                .comments(article.getComments()
                        .stream()
                        .map(commentMapper::commentToCommentDto)
                        .collect(Collectors.toList()))
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
