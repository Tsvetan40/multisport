package com.demo.multisport.services.article;

import com.demo.multisport.dto.page.ArticleDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<String> getAllTitles();
    Optional<ArticleDto> getArticleByTitle(String title) throws IOException;
}
