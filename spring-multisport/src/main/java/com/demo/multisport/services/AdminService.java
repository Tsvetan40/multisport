package com.demo.multisport.services;


import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.ArticleDuplicateException;
import com.demo.multisport.exceptions.CenterDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminService {

    private ArticleRepository articleRepository;
    private CenterRepository centerRepository;

    @Autowired
    public AdminService(ArticleRepository articleRepository, CenterRepository centerRepository) {
        this.articleRepository = articleRepository;
        this.centerRepository = centerRepository;
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

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    public void addCenter(Center center) {
        if (centerRepository.countCentersByAddress(center.getAddress()) > 0) {
            throw new CenterDuplicateException("Center already exists!");
        }

        centerRepository.save(center);
    }

    public void deleteCenter(String address) {
        centerRepository.deleteCenterByAddress(address);
    }
}
