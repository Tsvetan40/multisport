package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.exceptions.plan.NoSuchPlanException;
import com.demo.multisport.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlanMapperImpl implements PlanMapper {

    private final CenterRepository centerRepository;

    @Override
    public Plan planDtoToPlan(PlanDto planDto) {
        return Plan
                .builder()
                .name(planDto.getName())
                .price(planDto.getPrice())
                .subscribedUsers(planDto.getUsers())
                .centers(planDto
                        .getCentersAddresses()
                        .stream()
                        .map(address -> centerRepository.getCenterByAddress(address)
                                .orElseThrow(() -> new NoSuchPlanException("No such a plan with address " + address)))
                        .collect(Collectors.toSet()))
                .build();
    }

    //maybe not working because of the relationships user and centers
    @Override
    public PlanDto planToPlanDto(Plan plan) {
        return PlanDto
                .builder()
                .name(plan.getName())
                .price(plan.getPrice())
                .users(plan.getSubscribedUsers())
                .centersAddresses(plan.getCenters()
                        .stream()
                        .map(Center::getAddress)
                        .collect(Collectors.toSet()))
                .build();
    }
}
