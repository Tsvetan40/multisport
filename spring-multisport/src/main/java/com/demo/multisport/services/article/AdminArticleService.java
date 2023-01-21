package com.demo.multisport.services.article;

import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminArticleService implements ArticleService {
    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;

    public ArticleDto deleteArticleByTitle(String title) {
        Article article = articleRepository.deleteByTitle(title);
        return articleMapper.articleToArticleDto(article);
    }

    public ArticleDto addArticle(ArticleDto articleDto) {
        articleRepository.save(articleMapper.articleDtoToArticle(articleDto));
        return articleDto;
    }

    public long countArticlesByTitle(String title) {
        return this.articleRepository.countArticleByTitle(title);
    }

    public List<String> getAllTitles() {
        return articleRepository.getAllTitles();
    }
}
