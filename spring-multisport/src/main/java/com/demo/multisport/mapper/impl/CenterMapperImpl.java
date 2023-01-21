package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dao.CommentRepository;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.center.TypeCenter;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Rating;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.mapper.CenterMapper;
import com.demo.multisport.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

//this is for the part of the app, where only users has access
@Component
public class CenterMapperImpl implements CenterMapper {

    private final CenterRepository centerRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private static final String SPORT_CENTER_DISCRIMINATOR_VALUE = "SportCenter";
    private static final String RELAX_CENTER_DISCRIMINATOR_VALUE = "RelaxCenter";

    @Autowired
    public CenterMapperImpl(CenterRepository centerRepository,
                            CommentMapper commentMapper,
                            CommentRepository commentRepository) {
        this.centerRepository = centerRepository;
        this.commentMapper = commentMapper;
        this.commentRepository =commentRepository;
    }

    @Override
    public SportCenter centerDtoToSportCenter(CenterDto centerDto) {
        SportCenter sportCenter = new SportCenter();

        sportCenter.setName(centerDto.getName());
        sportCenter.setAddress(centerDto.getAddress());
        sportCenter.setDescription(centerDto.getDescription());
        sportCenter.setPictures(centerDto.getPictures());
        sportCenter.setComments(commentRepository.getCommentsByAddress(centerDto.getAddress()).stream().collect(Collectors.toSet()));
        sportCenter.setRating(centerRepository.getRating(centerDto.getAddress()));

        return sportCenter;
    }

    @Override
    public RelaxCenter centerDtoToRelaxCenter(CenterDto centerDto) {
        RelaxCenter relaxCenter = new RelaxCenter();
        relaxCenter.setAddress(centerDto.getAddress());
        relaxCenter.setName(centerDto.getName());
        relaxCenter.setDescription(centerDto.getDescription());
        relaxCenter.setPictures(centerDto.getPictures());
        relaxCenter.setRating(centerRepository.getRating(centerDto.getAddress()));
        relaxCenter.setComments(commentRepository.getCommentsByAddress(centerDto.getAddress()).stream().collect(Collectors.toSet()));
        relaxCenter.setServices(centerDto.getServices());

        return relaxCenter;
    }

    @Override
    public CenterDto sportCenterToCenterDto(SportCenter sportCenter) {
        //this is user part i don't need plans
        return CenterDto
                .builder()
                .centerType(TypeCenter.SPORT_CENTER)
                .name(sportCenter.getName())
                .description(sportCenter.getDescription())
                .address(sportCenter.getAddress())
                .pictures(sportCenter.getPictures())
                .comments(sportCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .rating(this.map(sportCenter.getRating()))
                .build();
    }

    @Override
    public CenterDto relaxCenterToCenterDto(RelaxCenter relaxCenter) {
        return CenterDto
                .builder()
                .centerType(TypeCenter.RELAX_CENTER)
                .name(relaxCenter.getName())
                .description(relaxCenter.getDescription())
                .address(relaxCenter.getAddress())
                .pictures(relaxCenter.getPictures())
                .comments(relaxCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .rating(this.map(relaxCenter.getRating()))
                .services(relaxCenter.getServices())
                .build();
    }

    @Override
    public Double map(Rating rating) {
        return rating.getRate();
    }
}
