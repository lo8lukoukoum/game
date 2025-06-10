package com.example.gameshare.service;

import com.example.gameshare.model.Game;

import java.util.List;

public interface GameService {

    Game createGame(Game game);

    List<Game> findAllGames();

    Game findGameById(Integer id);

    List<Game> findGamesByCategoryId(Integer categoryId);

    Game updateGame(Game game);

    void deleteGame(Integer id);

    List<Game> searchGames(String keyword);
}
