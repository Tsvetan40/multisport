package com.demo.multisport.services.page;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.services.article.ArticleServiceImpl;
import com.demo.multisport.services.center.CenterService;
import com.demo.multisport.services.comment.CommentService;
import com.demo.multisport.services.plan.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


//goes to controller
@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    private final ArticleServiceImpl articleService;
    private final CenterService centerService;
    private final PlanService planService;
    private final CommentService commentService;
    private final String SPORT_CENTER_TYPE = "SportCenter";
    private final String RELAX_CENTER_TYPE = "RelaxCenter";

    @Override
    public List<ArticleDto> getAllArticlesTitlesAndImages() {
        return articleService.getAllTitlesAndImages();
    }

    public Optional<CenterDto> getCenterDtoById(Long id, String type) {
        if (type.equals(SPORT_CENTER_TYPE)) {
            return centerService.sportCenterToCenterDto(id);
        }
        return centerService.relaxCenterToCenterDto(id);
    }

    public Optional<ArticleDto> getArticleByTitle(String title) {
        try {
            return articleService.getArticleByTitle(title);
        } catch (IOException e) {
            throw new IllegalStateException("ArticleDto cannot be mapped from article title" + title);
        }
    }

    public Set<PlanDto> getAllPlans() throws IOException {
        return new HashSet<>(planService.getAllPlans());
    }

    public Set<CenterDto> getAllSportCenters() {
        return centerService.getAllSportCenters();
    }

    public Set<CenterDto> getAllRelaxCenters() {
        return centerService.getAlRelaxCenters();
    }

    public Optional<PlanDto> getPlanByName(String planName) throws IOException {
        return planService.getPlanByName(planName);
    }

    public void addCommentArticle(CommentDto comment) {
        commentService.addComment(comment);
    }

    public void addCommentRelaxCenter(CommentDto comment) {
        commentService.addComment(comment);
    }

    public void addCommentSportCenter(CommentDto comment) {
        commentService.addComment(comment);
    }
}
