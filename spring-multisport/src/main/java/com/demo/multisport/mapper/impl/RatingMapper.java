package com.demo.multisport.mapper.impl;

import com.demo.multisport.dto.page.RatingDto;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.page.Rating;
import org.mapstruct.Mapper;

@Mapper
public interface RatingMapper {
    Rating ratingDtoToRating(Double price, Center center);
    RatingDto ratingToRatingDto(Double price, String address);
}
