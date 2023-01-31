package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.exceptions.plan.NoSuchPlanException;
import com.demo.multisport.mapper.PlanMapper;
import com.demo.multisport.services.impl.PlanMultipart;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlanMapperImpl implements PlanMapper {

    private final CenterRepository centerRepository;

    @Override
    public Plan planDtoToPlan(PlanDto planDto, String filePath) {
        return Plan
                .builder()
                .name(planDto.getName())
                .price(planDto.getPrice())
                .pathFile(filePath)
                .subscribedUsers(planDto.getUsers())
                .centers(planDto
                        .getCentersAddresses()
                        .stream()
                        .map(address -> centerRepository.getCenterByAddress(address)
                                .orElseThrow(() -> new NoSuchPlanException("No such a plan with address " + address)))
                        .collect(Collectors.toSet()))
                .build();
    }

    private String fileToString(String pathFile) throws IOException {
        PlanMultipart planMultipart = new PlanMultipart();
        return Base64.encodeBase64String(planMultipart.getFileBytes(pathFile));
    }


    @Override
    public PlanDto planToPlanDto(Plan plan) throws IOException {
        return PlanDto
                .builder()
                .name(plan.getName())
                .price(plan.getPrice())
                .imageBase64(fileToString(plan.getPathFile()))
                .users(plan.getSubscribedUsers())
                .centersAddresses(plan.getCenters()
                        .stream()
                        .map(Center::getAddress)
                        .collect(Collectors.toSet()))
                .build();
    }
}
