package com.demo.multisport.services;


import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.article.ArticleDuplicateException;
import com.demo.multisport.exceptions.CenterDuplicateException;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final ArticleRepository articleRepository;
    private final CenterRepository centerRepository;
    private final ArticleMapper articleMapper;

    @Autowired
    public AdminService(ArticleRepository articleRepository, CenterRepository centerRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.centerRepository = centerRepository;
        this.articleMapper = articleMapper;
    }

    public List<String> getAllArticlesTitle() {
        return articleRepository.getAllTitles();
    }

    public ArticleDto deleteArticle(String title) {
        try {
            Article article = articleRepository.deleteByTitle(title);
            return  articleMapper.articleToArticleDto(article);
        } catch (Exception e) {
            throw new NoSuchArticleException("No article with title " + title);
        }
    }

    public void addArticle(ArticleDto articleDto) {
        Article article = articleMapper.articleDtoToArticle(articleDto);
        article.withPublishedAt();
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
