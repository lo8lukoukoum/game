package com.example.gameshare.service;

import com.example.gameshare.model.Rating;

import java.util.List;

public interface RatingService {

    Rating createOrUpdateRating(Rating rating);

    // Could return a list of ratings or a calculated average
    List<Rating> getRatingByGameId(Integer gameId);

    // Optional: Could return a DTO with average score and count
    // Double getAverageRatingForGame(Integer gameId);

    Rating getUserRatingForGame(Integer userId, Integer gameId);
}
