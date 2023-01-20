package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.RatingRepository;
import com.demo.multisport.dto.page.RatingDto;
import com.demo.multisport.entities.page.Rating;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.mapper.RatingMapper;
import org.springframework.stereotype.Component;

@Component
public class RatingMapperImpl implements RatingMapper {

    private final RatingRepository ratingRepository;

    public RatingMapperImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating ratingDtoToRating(RatingDto ratingDto) {

        if (ratingRepository.getCenter(ratingDto.getAddress()).isEmpty()) {
            throw new CenterNotFoundException("Center now found ar address " + ratingDto.getAddress());
        }

        return new Rating(ratingDto.getRate(), ratingRepository.getCenter(ratingDto.getAddress()).get());
    }

    @Override
    public RatingDto ratingToRatingDto(Rating rating) {
        return new RatingDto(rating.getRate(), rating.getCenter().getAddress());
    }
}
