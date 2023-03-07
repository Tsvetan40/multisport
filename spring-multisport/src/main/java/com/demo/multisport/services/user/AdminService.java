package com.demo.multisport.services.user;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.ArticleDto;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.article.ArticleDuplicateException;
import com.demo.multisport.exceptions.CenterDuplicateException;
import com.demo.multisport.exceptions.article.NoSuchArticleException;
import com.demo.multisport.services.page.AdminPageServiceImpl;
import com.demo.multisport.services.plan.PlanService;
import com.demo.multisport.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService {

    private final AdminPageServiceImpl adminPageService;
    private final PlanService adminPlanService;
    private final UserService userService;

    @Autowired
    public AdminService(AdminPageServiceImpl adminPageService, PlanService adminPlanService, UserService userService) {
        this.adminPlanService = adminPlanService;
        this.adminPageService = adminPageService;
        this.userService = userService;
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

        adminPageService.addCenter(centerDto);
    }

    public void deleteCenter(String address) {
        adminPageService.deleteCenter(address);
    }

    public void addPlan(PlanDto planDto, String filePath) {
        adminPlanService.addPlanAdmin(planDto, filePath);
    }

    public List<PlanDto> getAllPlans() throws IOException {
        return adminPlanService.getAllPlansAdmin();
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public boolean checkAdminCredentials(Authentication authentication) {
        return userService.checkAdminCredentials(authentication);
    }

    public UserDto getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public Optional<UserDto> blockUser(Long id) {
      return userService.blockUser(id);
    }

    public Optional<UserDto> restoreUser(Long id) {
        return userService.restoreUserRights(id);
    }

    public void addAdmin(UserDto userDto) {
        this.userService.registerUser(userDto);
    }
}
