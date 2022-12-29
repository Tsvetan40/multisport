package com.demo.multisport.services;


import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.ArticleDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminService {

    private ArticleRepository articleRepository;

    @Autowired
    public AdminService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void deleteArticle(String title) {
        articleRepository.deleteByTitle(title);
    }

    public void addArticle(Article article) {
        if (articleRepository.countArticleByTitle(article.getTitle()) > 0) {
            throw new ArticleDuplicateException("Article Already exists! Change Title");
        }
        articleRepository.save(article);
    }
}
