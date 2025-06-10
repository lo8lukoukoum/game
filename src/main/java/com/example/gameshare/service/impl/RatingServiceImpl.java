package com.example.gameshare.service.impl;

import com.example.gameshare.model.Rating;
import com.example.gameshare.repository.RatingMapper;
import com.example.gameshare.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingMapper ratingMapper;

    @Autowired
    public RatingServiceImpl(RatingMapper ratingMapper) {
        this.ratingMapper = ratingMapper;
    }

    @Override
    public Rating createOrUpdateRating(Rating rating) {
        Rating existingRating = ratingMapper.findByUserIdAndGameId(rating.get用户id(), rating.get游戏id());
        rating.set更新时间(LocalDateTime.now());
        if (existingRating != null) {
            rating.setId(existingRating.getId()); // Set ID for update
            // Preserve original creation time
            rating.set创建时间(existingRating.get创建时间());
            ratingMapper.update(rating);
        } else {
            rating.set创建时间(LocalDateTime.now());
            ratingMapper.insert(rating);
        }
        // Fetch the rating by its ID if available (after insert) or by userId/gameId
        if (rating.getId() != null) {
             // A findById method in RatingMapper would be ideal here.
             // For now, we'll re-fetch using userId and gameId as it's guaranteed to be unique.
             return ratingMapper.findByUserIdAndGameId(rating.get用户id(), rating.get游戏id());
        }
        return ratingMapper.findByUserIdAndGameId(rating.get用户id(), rating.get游戏id());
    }

    @Override
    public List<Rating> getRatingByGameId(Integer gameId) {
        return ratingMapper.findByGameId(gameId);
    }

    @Override
    public Rating getUserRatingForGame(Integer userId, Integer gameId) {
        return ratingMapper.findByUserIdAndGameId(userId, gameId);
    }
}
