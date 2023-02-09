package com.demo.multisport.services.article;

import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ArticleDto addArticle(ArticleDto articleDto, String pathFile) {
        Article article = articleMapper.articleDtoToArticle(articleDto, pathFile);
        articleRepository.save(article);

        return articleDto;
    }

    public long countArticlesByTitle(String title) {
        return this.articleRepository.countArticleByTitle(title);
    }

    @Override
    public List<ArticleDto> getAllTitlesAndImages() {
        List<ArticleDto> collect = articleRepository
                .findAll()
                .stream()
                .map( article -> {
                    try {
                        return articleMapper.articleToPartArticleDto(article);
                    } catch (IOException e) {
                        throw new IllegalStateException("Cannot map article to articleDto");
                    }
                })
                .collect(Collectors.toList());
        return collect;

    }

    //to do display specific article from admin
    @Override
    public Optional<ArticleDto> getArticleByTitle(String title) throws IOException {
        Optional<Article> article = articleRepository.getArticleByTitle(title);
        if (article.isEmpty()) {
            throw new NoSuchArticleException("Can't find article with title " + title);
        }

        return Optional.of(articleMapper.articleToPartArticleDto(article.get()));
    }
}
