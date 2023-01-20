package com.demo.multisport.services.impl;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.mapper.PlanMapper;
import org.springframework.stereotype.Component;

@Component
public class PlanMapperImpl implements PlanMapper {


    @Override
    public Plan planDtoToPlan(PlanDto planDto) {
        return Plan
                .builder()
                .name(planDto.getName())
                .price(planDto.getPrice())
                .subscribedUsers(planDto.getUsers())
                .centers(planDto.getCenters())
                .build();
    }

    //maybe not working because of the ralationships user and centers
    @Override
    public PlanDto planToPlanDto(Plan plan) {
        return PlanDto
                .builder()
                .name(plan.getName())
                .price(plan.getPrice())
                .users(plan.getSubscribedUsers())
                .centers(plan.getCenters())
                .build();
    }
}
