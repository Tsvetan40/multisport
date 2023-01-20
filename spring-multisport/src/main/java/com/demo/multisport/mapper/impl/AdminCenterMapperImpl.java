package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.mapper.CenterMapper;
import com.demo.multisport.mapper.CommentMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AdminCenterMapperImpl implements CenterMapper {
    private final CenterRepository centerRepository;
    private final CommentMapper commentMapper;

    public AdminCenterMapperImpl(CenterRepository centerRepository, CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
        this.centerRepository = centerRepository;
    }

    @Override
    public SportCenter centerDtoToSportCenter(CenterDto centerDto) {
        Optional<SportCenter> sportCenter = centerRepository.getSportCenterByAddress(centerDto.getAddress());
        if (sportCenter.isEmpty()) {
            throw new CenterNotFoundException("Sport center not found " + centerDto.getName());
        }

        return sportCenter.get();
    }

    @Override
    public RelaxCenter centerDtoToRelaxCenter(CenterDto centerDto) {
        Optional<RelaxCenter> relaxCenter = centerRepository.getRelaxCenterByAddress(centerDto.getAddress());
        if (relaxCenter.isEmpty()) {
            throw new CenterNotFoundException("Relax Center not found " + centerDto.getName());
        }
        return relaxCenter.get();
    }

    @Override
    public CenterDto sportCenterToCenterDto(SportCenter sportCenter) {
        return CenterDto
                .builder()
                .address(sportCenter.getAddress())
                .name(sportCenter.getName())
                .description(sportCenter.getDescription())
                .pictures(sportCenter.getPictures())
                .plans(sportCenter.getPlans())
                .comments(sportCenter.getComments().stream().map(commentMapper::commentToCommentDto).collect(Collectors.toSet()))
                .rating(this.map(sportCenter.getRating()))
                .build();


    }

    @Override
    public CenterDto relaxCenterToCenterDto(RelaxCenter relaxCenter) {
        return CenterDto
                .builder()
                .address(relaxCenter.getAddress())
                .name(relaxCenter.getName())
                .description(relaxCenter.getDescription())
                .pictures(relaxCenter.getPictures())
                .plans(relaxCenter.getPlans())
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
