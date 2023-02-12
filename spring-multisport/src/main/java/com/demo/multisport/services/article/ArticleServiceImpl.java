package com.demo.multisport.services.article;

import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

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

    public Optional<ArticleDto> getArticleByTitle(String title) throws IOException {
        Article article = articleRepository.getArticleByTitle(title)
                .orElseThrow(() -> new NoSuchArticleException("Can't find article with title " + title));

        ArticleDto articleDto = articleMapper.articleToFullArticleDto(article);
        return Optional.of(articleDto);
    }
}
