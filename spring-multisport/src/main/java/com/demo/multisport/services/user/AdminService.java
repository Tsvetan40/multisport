package com.demo.multisport.services.user;


import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.article.ArticleDuplicateException;
import com.demo.multisport.exceptions.CenterDuplicateException;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.mapper.ArticleMapper;
import com.demo.multisport.services.page.AdminPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;
    private final AdminPageServiceImpl adminPageService;

    @Autowired
    public AdminService(ArticleRepository articleRepository,
                        ArticleMapper articleMapper,
                        AdminPageServiceImpl adminPageService) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.adminPageService = adminPageService;
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


    public void addCenter(CenterDto centerDto) {
        if (adminPageService.countCentersByAddress(centerDto) > 0) {
            throw new CenterDuplicateException("Center already exists!" + centerDto.getName());
        }

        if (centerDto.getServices() == null) {
            adminPageService.addSportCenter(centerDto);
        } else {
            adminPageService.addRelaxCenter(centerDto);
        }

    }

    public void deleteCenter(String address) {
        adminPageService.deleteCenter(address);
    }
}
