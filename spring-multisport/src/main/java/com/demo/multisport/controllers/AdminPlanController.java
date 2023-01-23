package com.demo.multisport.controllers;


import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.exceptions.plan.DuplicatePlanException;
import com.demo.multisport.services.user.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("multisport/admin")
public class AdminPlanController {

    private final AdminService adminService;

    public AdminPlanController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/newplan")
    public ResponseEntity<Optional<PlanDto>> addPlan(@RequestBody PlanDto planDto) {

        try {
            adminService.addPlan(planDto);
            return new ResponseEntity<>(Optional.of(planDto), HttpStatus.OK);
        } catch (DuplicatePlanException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
        }
    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlanDto>> getAllPlans() {
        return new ResponseEntity<>(adminService.getAllPlans(), HttpStatus.OK);
    }
}
