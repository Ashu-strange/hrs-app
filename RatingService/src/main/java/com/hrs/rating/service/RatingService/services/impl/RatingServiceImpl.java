package com.hrs.rating.service.RatingService.services.impl;

import com.hrs.rating.service.RatingService.Entities.Rating;
import com.hrs.rating.service.RatingService.exceptions.ResourceNotFoundException;
import com.hrs.rating.service.RatingService.repositories.RatingRepository;
import com.hrs.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating addRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRating(String ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Ratings not found with the given Id"));
        return rating;
    }

    @Override
    public List<Rating> getRatingsByUserID(String userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        return ratings;
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
