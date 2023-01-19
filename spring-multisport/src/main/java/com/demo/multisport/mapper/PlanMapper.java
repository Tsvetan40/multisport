package com.demo.multisport.mapper;

import com.demo.multisport.dto.plan.PlanDto;
import com.demo.multisport.entities.Plan;
import org.mapstruct.Mapper;

@Mapper
public interface PlanMapper {

    Plan planDtoToPlan(PlanDto planDto);
    PlanDto planToPlanDto(Plan plan);
}
