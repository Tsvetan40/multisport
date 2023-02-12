package com.demo.multisport.controllers;


import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.exceptions.plan.NoSuchPlanException;
import com.demo.multisport.exceptions.user.UserNotFoundException;
import com.demo.multisport.services.page.PageServiceImpl;
import com.demo.multisport.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/multisport")
public class PlanController {
    private final PageServiceImpl pageService;
    private final UserService userService;

    public PlanController(PageServiceImpl pageService, UserService userService) {
        this.pageService = pageService;
        this.userService = userService;
    }

    @PostMapping("/plans/{name}")
    public ResponseEntity<PlanDto> enrollPlan(@PathVariable("name") String planName,
                                              @RequestBody PlanDto plan,
                                              HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>(plan, HttpStatus.UNAUTHORIZED);
        }

        try {
            userService.enrollPlan(user, planName);
            return new ResponseEntity<>(plan, HttpStatus.OK);
        } catch (NoSuchPlanException | UserNotFoundException e) {
            return new ResponseEntity<>(plan, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/plans/{name}")
    public ResponseEntity<Optional<PlanDto>> getPlan(@PathVariable(name = "name") String planName) {
        try{
            return new ResponseEntity<>(pageService.getPlanByName(planName), HttpStatus.OK);
        } catch (NoSuchPlanException | IOException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
        }
    }

    @GetMapping("/plans")
    public ResponseEntity<Set<PlanDto>> getAllPlans() {
        System.out.println("Hit plans");
        try {
            return new ResponseEntity<>(pageService.getAllPlans(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new HashSet<>(), HttpStatus.OK);
        }
    }

}
