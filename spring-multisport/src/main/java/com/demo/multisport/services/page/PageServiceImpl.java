package com.demo.multisport.services.page;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.services.article.ArticleServiceImpl;
import com.demo.multisport.services.center.CenterService;
import com.demo.multisport.services.plan.AdminPlanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//goes to controller
@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService{

    private final ArticleServiceImpl articleService;
    private final CenterService centerService;
    private final AdminPlanServiceImpl planService;

    @Override
    public List<String> getAllArticlesTitles() {
        return articleService.getAllTitles();
    }

    public Optional<ArticleDto> getArticleByTitle(String title) {
        return this.articleService.getArticleByTitle(title);
    }

    @Override
    public CenterDto getCenterDtoFromSportCenter(SportCenter sportCenter) {
        return null;
    }

    @Override
    public CenterDto getCenterDtoFromRelaxCenter(RelaxCenter relaxCenter) {
        return null;
    }

    public Set<PlanDto> getAllPlans() {
        return new HashSet<>(planService.getAllPlans());
    }

    public Set<CenterDto> getAllSportCenters() {
        return centerService.getAllSportCenters();
    }

    public Set<CenterDto> getAllRelaxCenters() {
        return centerService.getAlRelaxCenters();
    }
}
