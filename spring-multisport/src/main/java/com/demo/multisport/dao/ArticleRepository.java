package com.demo.multisport.dao;

import com.demo.multisport.entities.page.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    long countArticleByTitle(String title);
    Optional<Article> getArticleByTitle(String article);
}
