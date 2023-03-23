package com.demo.multisport.services.page;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.center.ICenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.services.RatingService;
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


@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    private final RatingService ratingService;
    private final ArticleServiceImpl articleService;
    private final CenterService centerService;
    private final PlanService planService;
    private final CommentService commentService;

    @Override
    public List<ArticleDto> getAllArticlesTitlesAndImages() {
        return articleService.getAllTitlesAndImages();
    }

    public CenterDto getCenterDtoById(Long id) {
            return centerService.centerToCenterDto(id);
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

    public Set<ICenterDto> getAllSportCenters() {
        return centerService.getAllSportCenters();
    }

    public Set<ICenterDto> getAllRelaxCenters() {
        return centerService.getAlRelaxCenters();
    }

    public Optional<PlanDto> getPlanByName(String planName) throws IOException {
        return planService.getPlanByName(planName);
    }

    public CommentDto addComment(CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }

    public double rate(int rating, long centerId) {
        return ratingService.rate(rating, centerId);
    }
}
