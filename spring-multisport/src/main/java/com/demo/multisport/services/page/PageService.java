package com.demo.multisport.services.page;


import com.demo.multisport.dto.page.ArticleDto;

import java.util.List;

public interface PageService {
    List<ArticleDto> getAllArticlesTitlesAndImages();
}
