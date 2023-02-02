package com.demo.multisport.services.user;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.exceptions.article.ArticleDuplicateException;
import com.demo.multisport.exceptions.CenterDuplicateException;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.services.page.AdminPageServiceImpl;
import com.demo.multisport.services.plan.PlanService;
import com.demo.multisport.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

//goes to controllers only
@Service
public class AdminService {

    private final AdminPageServiceImpl adminPageService;
    private final PlanService adminPlanService;

    @Autowired
    public AdminService(AdminPageServiceImpl adminPageService, PlanService adminPlanService) {
        this.adminPlanService = adminPlanService;
        this.adminPageService = adminPageService;
    }

    public List<ArticleDto> getAllArticlesTitlesAndImages() {
        return adminPageService.getAllArticlesTitlesAndImages();
    }

    public void deleteArticle(String title) {
        try {
            adminPageService.deleteArticleByTitle(title);
        } catch (Exception e) {
            throw new NoSuchArticleException("No article with title " + title);
        }
    }

    public ArticleDto addArticle(ArticleDto articleDto, MultipartFile picture) {
        articleDto.setPublishedAt(LocalDateTime.now());

        if (adminPageService.countArticlesByTitle(articleDto.getTitle()) > 0) {
            throw new ArticleDuplicateException("Article Already exists! Change Title");
        }

        String pathFile = FileUtil.saveArticleImage(picture);

        return adminPageService.addArticle(articleDto, pathFile);
    }

    public void addCenter(CenterDto centerDto) {
        if (adminPageService.countCentersByAddress(centerDto) > 0) {
            throw new CenterDuplicateException("Center already exists!" + centerDto.getName());
        }

        if (centerDto.getServices() == null) {
            adminPageService.addSportCenter(centerDto);
        } else {
            adminPageService.addRelaxCenter(centerDto);
        }

    }

    public void deleteCenter(String address) {
        adminPageService.deleteCenter(address);
    }

    public CommentDto addComment(CommentDto commentDto) {
        return adminPageService.addComment(commentDto);
    }

    public CommentDto deleteComment(CommentDto commentDto) {
        return adminPageService.deleteComment(commentDto);
    }

    public void addPlan(PlanDto planDto, String filePath) {
        adminPlanService.addPlanAdmin(planDto, filePath);
    }

    public List<PlanDto> getAllPlans() throws IOException {
        return adminPlanService.getAllPlansAdmin();
    }
}
