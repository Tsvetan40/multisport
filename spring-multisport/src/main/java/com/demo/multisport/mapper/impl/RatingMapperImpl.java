package com.demo.multisport.mapper.impl;

import com.demo.multisport.dto.page.RatingDto;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.page.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapperImpl implements RatingMapper{

    @Override
    public Rating ratingDtoToRating(Double price, Center center) {
        return new Rating(price, center);
    }

    @Override
    public RatingDto ratingToRatingDto(Double price, String address) {
        return new RatingDto(price, address);
    }
}
