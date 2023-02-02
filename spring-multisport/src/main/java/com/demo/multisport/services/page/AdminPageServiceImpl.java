package com.demo.multisport.services.page;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.services.article.AdminArticleService;
import com.demo.multisport.services.center.CenterService;
import com.demo.multisport.services.comment.CommentServiceAdminImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPageServiceImpl implements PageService {
    private final CenterService centerService;
    private final AdminArticleService adminArticleService;
    private final CommentServiceAdminImpl commentServiceAdmin;

    @Override
    public List<ArticleDto> getAllArticlesTitlesAndImages() {
        return adminArticleService.getAllTitlesAndImages();
    }

    public void addSportCenter(CenterDto centerDto) {
        centerService.addSportCenterAdmin(centerDto);
    }

    public void addRelaxCenter(CenterDto centerDto) {
        centerService.addRelaxCenterAdmin(centerDto);
    }

    public void deleteCenter(String address) {
        centerService.deleteCenterAdmin(address);
    }

    public long countCentersByAddress(CenterDto centerDto) {
        return centerService.countCentersByAddress(centerDto.getAddress());
    }

    public void deleteArticleByTitle(String title) {
        adminArticleService.deleteArticleByTitle(title);
    }

    public ArticleDto addArticle(ArticleDto articleDto, String pathFile) {
        return adminArticleService.addArticle(articleDto, pathFile);
    }

    public long countArticlesByTitle(String title) {
        return adminArticleService.countArticlesByTitle(title);
    }

    public CommentDto addComment(CommentDto commentDto) {
        return commentServiceAdmin.addComment(commentDto);
    }

    public CommentDto deleteComment(CommentDto commentDto) {
        return commentServiceAdmin.deleteComment(commentDto);
    }
}
