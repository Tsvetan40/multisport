package com.demo.multisport.dao;

import com.demo.multisport.entities.page.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Transactional
    void deleteByTitle(String title);
    long countArticleByTitle(String title);
}
