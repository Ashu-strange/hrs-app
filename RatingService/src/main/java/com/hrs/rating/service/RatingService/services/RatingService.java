package com.hrs.rating.service.RatingService.services;

import com.hrs.rating.service.RatingService.Entities.Rating;

import java.util.List;

public interface RatingService {

    Rating addRating(Rating rating);

    List<Rating> getRatings();

    Rating getRating(String ratingId);

    List<Rating> getRatingsByUserID(String userId);

    List<Rating> getRatingsByHotelId(String hotelId);
}
