package com.demo.multisport.services.plan;

import com.demo.multisport.dao.PlanRepository;
import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.exceptions.plan.DuplicatePlanException;
import com.demo.multisport.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanMapper planMapper;
    private final PlanRepository planRepository;

    public void addPlanAdmin(PlanDto planDto) {
        Plan plan = planMapper.planDtoToPlan(planDto);
        try {
            planRepository.save(plan);
        } catch (Exception e) {
            throw new DuplicatePlanException("Duplicate plan");
        }
    }

    public List<PlanDto> getAllPlans() {
        return planRepository.findAll()
                .stream()
                .map(planMapper::planToPlanDto)
                .collect(Collectors.toList());
    }
}
