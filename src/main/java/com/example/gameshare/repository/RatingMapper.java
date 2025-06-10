package com.example.gameshare.repository;

import com.example.gameshare.model.Rating;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RatingMapper {

    List<Rating> findByGameId(Integer gameId);

    Rating findByUserIdAndGameId(@Param("userId") Integer userId, @Param("gameId") Integer gameId);

    int insert(Rating rating);

    int update(Rating rating);
}
