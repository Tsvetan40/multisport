package com.demo.multisport.mapper.impl;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Rating;
import com.demo.multisport.mapper.AdminCenterMapper;
import com.demo.multisport.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdminCenterMapperImpl implements AdminCenterMapper {

    private final CommentMapper commentMapper;

    @Override
    public CenterDto sportCenterToCenterDtoExtractRecord(SportCenter sportCenter) {
        return CenterDto
                .builder()
                .name(sportCenter.getName())
                .address(sportCenter.getAddress())
                .description(sportCenter.getDescription())
                .pictures(sportCenter.getPictures())
                .comments(sportCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .plans(sportCenter.getPlans())
                .rating(sportCenter.getRating().getRate())
                .build();
    }

    @Override
    public CenterDto relaxCenterToCenterDtoExtractRecord(RelaxCenter relaxCenter) {
        return CenterDto
                .builder()
                .name(relaxCenter.getName())
                .address(relaxCenter.getAddress())
                .description(relaxCenter.getDescription())
                .pictures(relaxCenter.getPictures())
                .comments(relaxCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .plans(relaxCenter.getPlans())
                .rating(relaxCenter.getRating().getRate())
                .services(relaxCenter.getServices())
                .build();

    }

    @Override
    public SportCenter centerDtoToSportCenterCreateRecord(CenterDto centerDto) {
        return new SportCenter()
                .withName(centerDto.getName())
                .withDescription(centerDto.getDescription())
                .withAddress(centerDto.getAddress())
                .withPictures(centerDto.getPictures())
                .withRating(new Rating());
    }

    @Override
    public RelaxCenter centerDtoToRelaxCenterCreateRecord(CenterDto centerDto) {
        return new RelaxCenter()
                .withName(centerDto.getName())
                .withDescription(centerDto.getDescription())
                .withAddress(centerDto.getAddress())
                .withPictures(centerDto.getPictures())
                .withRating(new Rating())
                .withServices(centerDto.getServices());
    }
}
