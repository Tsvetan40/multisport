package com.demo.multisport.mapper;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.entities.Plan;


public interface PlanMapper {

    Plan planDtoToPlan(PlanDto planDto);
    PlanDto planToPlanDto(Plan plan);
}
