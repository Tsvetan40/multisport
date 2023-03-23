package com.demo.multisport.services;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dao.RatingRepository;
import com.demo.multisport.entities.page.Rating;
import com.demo.multisport.exceptions.CenterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final CenterRepository centerRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(CenterRepository centerRepository, RatingRepository ratingRepository) {
        this.centerRepository = centerRepository;
        this.ratingRepository = ratingRepository;
    }

    private double calcNewRating(int newRate, double oldRating) {
        return (newRate + oldRating) / 2;
    }

    public double rate(int rating, long centerId) {
        Long ratingId = centerRepository.getCenterRatingId(centerId)
                .orElseThrow(() -> new CenterNotFoundException("Center rate cannot be done"));

        Rating r = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find rating"));


        double brandNewRating = calcNewRating(rating, r.getRate());
        r.setRate(brandNewRating);

        ratingRepository.save(r);

        return brandNewRating;
    }
}
