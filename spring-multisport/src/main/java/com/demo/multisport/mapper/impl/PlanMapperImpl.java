package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.exceptions.plan.NoSuchPlanException;
import com.demo.multisport.mapper.PlanMapper;
import com.demo.multisport.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Qualifier("planMapper")
public class PlanMapperImpl implements PlanMapper {
    private final CenterRepository centerRepository;
    private final UserMapper userMapper;

    @Override
    public Plan planDtoToPlan(PlanDto planDto, String filePath) {
        return Plan
                .builder()
                .name(planDto.getName())
                .price(planDto.getPrice())
                .pathFile(filePath)
                .centers(planDto
                        .getCentersAddresses()
                        .stream()
                        .map(address -> centerRepository.getCenterByAddress(address)
                                .orElseThrow(() -> new NoSuchPlanException("No such a plan with address " + address)))
                        .collect(Collectors.toSet()))
                .build();

    }

    @Override
    public PlanDto planToPlanDto(Plan plan) throws IOException {
        return PlanDto
                .builder()
                .name(plan.getName())
                .price(plan.getPrice())
                .imageBase64(FileUtil.convertFileToBase64(plan.getPathFile()))
                .users(plan.getSubscribedUsers()
                        .stream()
                        .map(userMapper::userToUserDto)
                        .collect(Collectors.toSet()))
                .centersAddresses(plan.getCenters()
                        .stream()
                        .map(Center::getAddress)
                        .collect(Collectors.toSet()))
                .build();
    }
}
