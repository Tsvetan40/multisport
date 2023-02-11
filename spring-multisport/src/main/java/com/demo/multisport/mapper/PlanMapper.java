package com.demo.multisport.mapper;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.entities.Plan;
import java.io.IOException;


public interface PlanMapper {
    Plan planDtoToPlan(PlanDto planDto, String filePath);
    PlanDto planToPlanDto(Plan plan) throws IOException;
}
