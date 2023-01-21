package com.demo.multisport.services.page;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.services.article.AdminArticleService;
import com.demo.multisport.services.center.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPageServiceImpl implements PageService {
    private final CenterService centerService;
    private final AdminArticleService adminArticleService;

    @Override
    public CenterDto getCenterDtoFromSportCenter(SportCenter sportCenter) {
        return centerService.getCenterDtoFromSportCenterAdmin(sportCenter);
    }

    @Override
    public CenterDto getCenterDtoFromRelaxCenter(RelaxCenter relaxCenter) {
        return centerService.getCenterDtoFromRelaxCenterAdmin(relaxCenter);
    }

    public void addSportCenter(CenterDto centerDto) {
        centerService.addSportCenterAdmin(centerDto);
    }

    public void addRelaxCenter(CenterDto centerDto) {
        centerService.addRelaxCenterAdmin(centerDto);
    }

    public void deleteCenter(String address) {
        centerService.deleteCenter(address);
    }

    public long countCentersByAddress(CenterDto centerDto) {
        return centerService.countCentersByAddress(centerDto.getAddress());
    }

    public void addComment(CommentDto commentDto) {

    }

    public ArticleDto deleteArticleByTitle(String title) {
       return adminArticleService.deleteArticleByTitle(title);
    }

    public ArticleDto addArticle(ArticleDto articleDto) {
        return adminArticleService.addArticle(articleDto);
    }

    public List<String> getAllTitles() {
        return adminArticleService.getAllTitles();
    }

    public long countArticlesByTitle(String title) {
        return adminArticleService.countArticlesByTitle(title);
    }
}
