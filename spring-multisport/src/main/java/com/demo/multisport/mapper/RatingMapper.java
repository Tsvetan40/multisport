package com.demo.multisport.mapper;

import com.demo.multisport.dto.page.RatingDto;
import com.demo.multisport.entities.page.Rating;


public interface RatingMapper {
    Rating ratingDtoToRating(RatingDto ratingDto);
    RatingDto ratingToRatingDto(Rating rating);
}
