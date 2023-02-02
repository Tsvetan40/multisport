package com.demo.multisport.mapper.impl;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.center.TypeCenter;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.mapper.CenterMapper;
import com.demo.multisport.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class CenterMapperImpl implements CenterMapper {

    private final CommentMapper commentMapper;

    @Autowired
    public CenterMapperImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public CenterDto sportCenterToCenterDtoExtractRecord(SportCenter sportCenter) {
        return CenterDto
                .builder()
                .id(sportCenter.getId())
                .centerType(TypeCenter.SPORT_CENTER)
                .name(sportCenter.getName())
                .description(sportCenter.getDescription())
                .address(sportCenter.getAddress())
                .pictures(sportCenter.getPictures())
                .comments(sportCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .rating(sportCenter.getRating().getRate())
                .build();
    }

    @Override
    public CenterDto relaxCenterToCenterDtoExtractRecord(RelaxCenter relaxCenter) {
        return CenterDto
                .builder()
                .id(relaxCenter.getId())
                .centerType(TypeCenter.RELAX_CENTER)
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
