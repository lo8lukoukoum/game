package com.example.gameshare.model;

import java.time.LocalDateTime;

public class Collection {

    private Integer id;
    private Integer userId; // Was 用户id
    private Integer gameId; // Was 游戏id
    private LocalDateTime collectedAt; // Was 收藏时间
    private User user; // Was 用户 (Joined object)
    private Game game; // Was 游戏 (Joined object)

    // Constructors
    public Collection() {
    }

    public Collection(Integer userId, Integer gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(LocalDateTime collectedAt) {
        this.collectedAt = collectedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", userId=" + userId +
                ", gameId=" + gameId +
                ", collectedAt=" + collectedAt +
                ", user=" + (user != null ? user.getUsername() : "null") +
                ", game=" + (game != null ? game.getName() : "null") +
                '}';
    }
}
