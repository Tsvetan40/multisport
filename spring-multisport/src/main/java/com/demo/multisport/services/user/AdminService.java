package com.demo.multisport.services.user;


import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dao.RatingRepository;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.entities.page.Rating;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.exceptions.article.ArticleDuplicateException;
import com.demo.multisport.exceptions.CenterDuplicateException;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.mapper.AdminCenterMapper;
import com.demo.multisport.mapper.ArticleMapper;
import com.demo.multisport.mapper.CenterMapper;
import com.demo.multisport.mapper.impl.AdminCenterMapperImpl;
import com.demo.multisport.mapper.impl.CenterMapperImpl;
import com.demo.multisport.services.page.AdminPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final ArticleRepository articleRepository;
    private final CenterRepository centerRepository;
    private final ArticleMapper articleMapper;
    private final AdminCenterMapper adminCenterMapper;
    private final RatingRepository ratingRepository;
    private final AdminPageServiceImpl adminPageService;

    @Autowired
    public AdminService(ArticleRepository articleRepository,
                        CenterRepository centerRepository,
                        ArticleMapper articleMapper,
                        CenterMapperImpl centerMapper,
                        AdminCenterMapperImpl adminCenterMapper,
                        RatingRepository ratingRepository,
                        AdminPageServiceImpl adminPageService) {
        this.articleRepository = articleRepository;
        this.centerRepository = centerRepository;
        this.articleMapper = articleMapper;
        this.adminCenterMapper = adminCenterMapper;
        this.ratingRepository = ratingRepository;
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
        if (centerRepository.countCentersByAddress(centerDto.getAddress()) > 0) {
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
