package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.mapper.CenterMapper;
import com.demo.multisport.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

//this is for the part of the app, where only users has access
@Component
public class CenterMapperImpl implements CenterMapper {

    private final CenterRepository centerRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CenterMapperImpl(CenterRepository centerRepository, CommentMapper commentMapper) {
        this.centerRepository = centerRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public SportCenter centerDtoToSportCenter(CenterDto centerDto) {
        //user part, so i don't need to show plan
        //user part, i need to show comments
        //and i also need to show rating
        return new SportCenter()
                .withName(centerDto.getName())
                .withAddress(centerDto.getAddress())
                .withDescription(centerDto.getDescription())
                .withPictures(centerDto.getPictures())
                .withComments(centerRepository.getComments(centerDto.getAddress()).stream().collect(Collectors.toSet()))
                .withRating(centerRepository.getRating(centerDto.getAddress()));
                //plan -> user part no plan at all, admin part whole plan
                //comments -> user part comment content and user name, admin part comment content, user email
                //rating -> user part rating, admin part rating

    }

    @Override
    public RelaxCenter centerDtoToRelaxCenter(CenterDto centerDto) {

        return new RelaxCenter()
                .withAddress(centerDto.getAddress())
                .withName(centerDto.getName())
                .withDescription(centerDto.getDescription())
                .withPictures(centerDto.getPictures())
                .withRating(centerRepository.getRating(centerDto.getAddress()))
                .withComments(centerRepository.getComments(centerDto.getAddress()).stream().collect(Collectors.toSet()))
                .withServices(centerDto.getServices());
    }

    @Override
    public CenterDto sportCenterToCenterDto(SportCenter sportCenter) {
        //this is user part i don't need plans
        return CenterDto
                .builder()
                .name(sportCenter.getName())
                .description(sportCenter.getDescription())
                .address(sportCenter.getAddress())
                .pictures(sportCenter.getPictures())
                .comments(sportCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .rating(sportCenter.getRating().getRate())
                .build();
    }

    @Override
    public CenterDto relaxCenterToCenterDto(RelaxCenter relaxCenter) {
        return CenterDto
                .builder()
                .name(relaxCenter.getName())
                .description(relaxCenter.getDescription())
                .address(relaxCenter.getAddress())
                .pictures(relaxCenter.getPictures())
                .comments(relaxCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .rating(relaxCenter.getRating().getRate())
                .services(relaxCenter.getServices())
                .build();
    }
}
