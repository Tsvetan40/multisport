package com.demo.multisport.mapper;

import com.demo.multisport.dto.page.RatingDto;
import com.demo.multisport.entities.page.Rating;
import org.mapstruct.Mapper;


public interface RatingMapper {
    Rating ratingDtoToRating(RatingDto ratingDto);
    RatingDto ratingToRatingDto(Rating rating);
}
