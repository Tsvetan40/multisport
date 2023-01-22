package com.demo.multisport.services.article;

import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminArticleService implements ArticleService {
    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;

    public void deleteArticleByTitle(String title) {
        Optional<Article> article = articleRepository.getArticleByTitle(title);

        if (article.isPresent()) {
            articleRepository.delete(article.get());
            return;
        }

        throw new NoSuchArticleException("No such article " + title);
    }

    public ArticleDto addArticle(ArticleDto articleDto) {
        Article article = articleMapper.articleDtoToArticle(articleDto);
        article.setPublishedAt(LocalDateTime.now());
        articleRepository.save(article);
        return articleDto;
    }

    public long countArticlesByTitle(String title) {
        return this.articleRepository.countArticleByTitle(title);
    }

    public List<String> getAllTitles() {
        return articleRepository.getAllTitles();
    }
}
