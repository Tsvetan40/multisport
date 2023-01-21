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
import com.demo.multisport.mapper.ArticleMapper;
import com.demo.multisport.mapper.CenterMapper;
import com.demo.multisport.mapper.impl.AdminCenterMapperImpl;
import com.demo.multisport.mapper.impl.CenterMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final ArticleRepository articleRepository;
    private final CenterRepository centerRepository;
    private final ArticleMapper articleMapper;
    private final CenterMapper centerMapper;
    private final CenterMapper adminCenterMapper;
    private final RatingRepository ratingRepository;

    @Autowired
    public AdminService(ArticleRepository articleRepository,
                        CenterRepository centerRepository,
                        ArticleMapper articleMapper,
                        CenterMapperImpl centerMapper,
                        AdminCenterMapperImpl adminCenterMapper,
                        RatingRepository ratingRepository) {
        this.articleRepository = articleRepository;
        this.centerRepository = centerRepository;
        this.articleMapper = articleMapper;
        this.centerMapper = centerMapper;
        this.adminCenterMapper = adminCenterMapper;
        this.ratingRepository = ratingRepository;
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

    public List<CenterDto> getAllCenters() {
        return centerRepository.findAll()
                .stream()
                .map(center -> {
                    if (centerMapper instanceof RelaxCenter)
                        return centerMapper.relaxCenterToCenterDto((RelaxCenter) center);

                    return centerMapper.sportCenterToCenterDto((SportCenter) center);
                    })
                .collect(Collectors.toList());

    }

    public void addCenter(CenterDto center) {
        if (centerRepository.countCentersByAddress(center.getAddress()) > 0) {
            throw new CenterDuplicateException("Center already exists!" + center.getName());
        }

        Rating rating = new Rating();
        ratingRepository.save(rating);

        if (center.getServices() == null) {
            SportCenter sportCenter = adminCenterMapper.centerDtoToSportCenter(center);
            System.out.println(sportCenter);
            centerRepository.save(sportCenter);
        } else {
            centerRepository.save(adminCenterMapper.centerDtoToRelaxCenter(center));
        }

    }

    public CenterDto deleteCenter(String address) {
        Optional<Center> center = centerRepository.deleteCenterByAddress(address);
        if (center.isEmpty()) {
            throw new CenterNotFoundException("Center with " + address + " not found");
        }

        return center.get() instanceof RelaxCenter ? centerMapper.relaxCenterToCenterDto((RelaxCenter) center.get()) :
                                                     centerMapper.sportCenterToCenterDto((SportCenter) center.get());

    }
}
