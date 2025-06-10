package com.example.gameshare.model;

import java.time.LocalDateTime;

public class Rating {

    private Integer id;
    private Integer score; // Was 分数
    private Integer userId; // Was 用户id
    private Integer gameId; // Was 游戏id
    private LocalDateTime createdAt; // Was 创建时间
    private LocalDateTime updatedAt; // Was 更新时间

    // Constructors
    public Rating() {
    }

    public Rating(Integer score, Integer userId, Integer gameId) {
        this.score = score;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", score=" + score +
                ", userId=" + userId +
                ", gameId=" + gameId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
