package com.demo.multisport.dao;

import com.demo.multisport.entities.page.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    void deleteArticleByTitle(String title);
    long countArticleByTitle(String title);

    @Query(nativeQuery = true, value = "SELECT title FROM articles")
    List<String> getAllTitles();

    Optional<Article> getArticleByTitle(String article);
}
