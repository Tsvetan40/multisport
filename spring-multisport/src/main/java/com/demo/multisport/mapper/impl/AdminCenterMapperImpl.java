package com.demo.multisport.mapper.impl;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.center.ICenterDto;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.center.ICenter;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Rating;
import com.demo.multisport.exceptions.PlanMapperException;
import com.demo.multisport.mapper.AdminCenterMapper;
import com.demo.multisport.mapper.CommentMapper;
import com.demo.multisport.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Qualifier("adminCenterMapper")
public class AdminCenterMapperImpl implements AdminCenterMapper {

    private final CommentMapper commentMapper;
    private final PlanMapper planMapper;

    public AdminCenterMapperImpl(CommentMapper commentMapper, @Qualifier("planAdminMapper") PlanMapper planMapper) {
        this.commentMapper = commentMapper;
        this.planMapper = planMapper;
    }

    private CenterDto sportCenterToCenterDtoExtractRecord(SportCenter sportCenter) {
        return CenterDto
                .builder()
                .id(sportCenter.getId())
                .name(sportCenter.getName())
                .address(sportCenter.getAddress())
                .description(sportCenter.getDescription())
                .pictures(sportCenter.getPictures())
                .comments(sportCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .plans(sportCenter.getPlans().stream().map(plan -> {
                    try {
                        return planMapper.planToPlanDto(plan);
                    } catch (IOException e) {
                        throw new PlanMapperException("Cannot map SportCenter to CenterDto ");
                    }
                }).collect(Collectors.toSet()))
                .rating(sportCenter.getRating().getRate())
                .build();
    }

    private CenterDto relaxCenterToCenterDtoExtractRecord(RelaxCenter relaxCenter) {
        return CenterDto
                .builder()
                .id(relaxCenter.getId())
                .name(relaxCenter.getName())
                .address(relaxCenter.getAddress())
                .description(relaxCenter.getDescription())
                .pictures(relaxCenter.getPictures())
                .comments(relaxCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .plans(relaxCenter.getPlans().stream().map(plan -> {
                    try {
                        return planMapper.planToPlanDto(plan);
                    } catch (IOException e) {
                        throw new PlanMapperException("Cannot map RelaxCenter to CenterDto ");
                    }
                }).collect(Collectors.toSet()))
                .rating(relaxCenter.getRating().getRate())
                .services(relaxCenter.getServices())
                .build();
    }

    @Override
    public ICenter centerDtoToCenterCreateRecord(CenterDto centerDto) {
        if (centerDto.getServices() != null) {
            return centerDtoToRelaxCenterCreateRecord(centerDto);
        }

        return centerDtoToSportCenterCreateRecord(centerDto);
    }

    private SportCenter centerDtoToSportCenterCreateRecord(CenterDto centerDto) {
        return new SportCenter()
                .withId(centerDto.getId())
                .withName(centerDto.getName())
                .withDescription(centerDto.getDescription())
                .withAddress(centerDto.getAddress())
                .withPictures(centerDto.getPictures())
                .withRating(new Rating());
    }

    private RelaxCenter centerDtoToRelaxCenterCreateRecord(CenterDto centerDto) {
        return new RelaxCenter()
                .withId(centerDto.getId())
                .withName(centerDto.getName())
                .withDescription(centerDto.getDescription())
                .withAddress(centerDto.getAddress())
                .withPictures(centerDto.getPictures())
                .withRating(new Rating())
                .withServices(centerDto.getServices());
    }

    @Override
    public ICenterDto centerToCenterDtoExtractRecord(Center center) {
        if (center instanceof SportCenter) {
            return this.sportCenterToCenterDtoExtractRecord((SportCenter) center);
        }

        return this.relaxCenterToCenterDtoExtractRecord((RelaxCenter) center);
    }
}
