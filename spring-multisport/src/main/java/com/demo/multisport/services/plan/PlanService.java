package com.demo.multisport.services.plan;

import com.demo.multisport.dao.PlanRepository;
import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.exceptions.plan.DuplicatePlanException;
import com.demo.multisport.exceptions.plan.NoSuchPlanException;
import com.demo.multisport.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    private final PlanMapper planAdminMapper;
    private final PlanMapper planMapper;
    private final PlanRepository planRepository;

    @Autowired
    public PlanService(@Qualifier(value = "planAdminMapper") PlanMapper planAdminMapper,
                       @Qualifier(value = "planMapper") PlanMapper planMapper,
                       PlanRepository planRepository) {
        this.planMapper = planMapper;
        this.planAdminMapper = planAdminMapper;
        this.planRepository = planRepository;
    }

    public void addPlanAdmin(PlanDto planDto, String filePath) {
        Plan plan = planAdminMapper.planDtoToPlan(planDto, filePath);
        plan.setPathFile(filePath);
        try {
            planRepository.save(plan);
        } catch (Exception e) {
            throw new DuplicatePlanException("Duplicate plan");
        }
    }

    public List<PlanDto> getAllPlans() throws IOException {
        List<Plan> allPlans = planRepository.findAll();
        List<PlanDto> allPlansDto = new LinkedList<>();
        for (Plan plan : allPlans) {
            allPlansDto.add(planMapper.planToPlanDto(plan));
        }

        return allPlansDto;
    }

    public List<PlanDto> getAllPlansAdmin() throws IOException {
        List<Plan> allPlans = planRepository.findAll();
        List<PlanDto> allPlansDto = new LinkedList<>();
        for (Plan plan : allPlans) {
            allPlansDto.add(planAdminMapper.planToPlanDto(plan));
        }

        return allPlansDto;
    }


    public Optional<PlanDto> getPlanByName(String planName) throws IOException {
        Optional<Plan> plan = planRepository.getPlanByName(planName);
        if (plan.isEmpty()) {
            throw new NoSuchPlanException("plan with name " + planName + "not found");
        }
        return Optional.of(planMapper.planToPlanDto(plan.get()));


    }
}
