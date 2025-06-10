package com.example.gameshare.service.impl;

import com.example.gameshare.model.Game;
import com.example.gameshare.repository.GameMapper;
import com.example.gameshare.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameMapper gameMapper;

    @Autowired
    public GameServiceImpl(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    @Override
    public Game createGame(Game game) {
        game.set创建时间(LocalDateTime.now());
        game.set更新时间(LocalDateTime.now());
        gameMapper.insert(game);
        return game;
    }

    @Override
    public List<Game> findAllGames() {
        return gameMapper.findAll();
    }

    @Override
    public Game findGameById(Integer id) {
        return gameMapper.findById(id);
    }

    @Override
    public List<Game> findGamesByCategoryId(Integer categoryId) {
        return gameMapper.findByCategoryId(categoryId);
    }

    @Override
    public Game updateGame(Game game) {
        game.set更新时间(LocalDateTime.now());
        int updatedCount = gameMapper.update(game);
        if (updatedCount > 0) {
            return gameMapper.findById(game.getId());
        }
        return null; // Or throw exception
    }

    @Override
    public void deleteGame(Integer id) {
        // Consider related entities: comments, ratings, collections
        // For now, simple deletion. These might need to be handled by DB cascades or explicitly here.
        gameMapper.delete(id);
    }

    @Override
    public List<Game> searchGames(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAllGames(); // Or return empty list
        }
        return gameMapper.search(keyword);
    }
}
