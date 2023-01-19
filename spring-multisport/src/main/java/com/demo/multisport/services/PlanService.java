package com.demo.multisport.services;

import com.demo.multisport.dao.PlanRepository;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.exceptions.plan.NoSuchPlanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }


    //връща план и после админ мапъра и обикновения мапър се оправят
    public Optional<Plan> getPlanById(Long id) {
        Optional<Plan> plan = planRepository.findById(id);

        if (plan.isEmpty()) {
            throw new NoSuchPlanException("Plan with id " + id + " doesn't exists");
        }

        return plan;
    }

}
