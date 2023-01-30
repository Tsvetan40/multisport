package com.demo.multisport.services.article;

import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public List<String> getAllTitles() {
        return articleRepository.getAllTitles();
    }

    @Override
    public Optional<ArticleDto> getArticleByTitle(String title) {
        Optional<Article> article = articleRepository.getArticleByTitle(title);
        if (article.isEmpty()) {
            throw new NoSuchArticleException("Can't find article with title " + title);
        }

        return Optional.of(articleMapper.articleToArticleDto(article.get()));
    }
}
