package com.example.gameshare.repository;

import com.example.gameshare.model.Game;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameMapper {

    Game findById(Integer id);

    List<Game> findAll();

    List<Game> findByCategoryId(Integer categoryId);

    int insert(Game game);

    int update(Game game);

    int delete(Integer id);

    List<Game> search(@org.apache.ibatis.annotations.Param("keyword") String keyword);
}
