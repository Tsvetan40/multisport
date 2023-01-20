package com.demo.multisport.services.page;

import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService {
    private final ArticleRepository articleRepository;

    @Autowired
    public PageService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Optional<Article> getArticleByTitle(String title) {
        Optional<Article> article = articleRepository.getArticleByTitle(title);
        if (article.isEmpty()) {
            throw new NoSuchArticleException("Article with title " + title + " does not exist");
        }

        return article;
    }
}
