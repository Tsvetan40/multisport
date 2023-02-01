package com.demo.multisport.services.page;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.services.article.ArticleServiceImpl;
import com.demo.multisport.services.center.CenterService;
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
    private final String SPORT_CENTER_TYPE = "SportCenter";
    private final String RELAX_CENTER_TYPE = "RelaxCenter";

    @Override
    public List<String> getAllArticlesTitles() {
        return articleService.getAllTitles();
    }

    public Optional<CenterDto> getCenterDtoByAddress(String address, String type) {
        if (type.equals(SPORT_CENTER_TYPE)) {
            return centerService.sportCenterToCenterDto(address);
        }
        return centerService.relaxCenterToCenterDto(address);
    }

    public Optional<ArticleDto> getArticleByTitle(String title) throws IOException{
        return this.articleService.getArticleByTitle(title);
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
}
