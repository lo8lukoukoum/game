package com.example.gameshare.model;

import java.time.LocalDateTime;

public class Comment {

    private Integer id;
    private String content; // Was 评论内容
    private Integer userId; // Was 用户id
    private Integer gameId; // Was 游戏id
    private LocalDateTime createdAt; // Was 创建时间
    private LocalDateTime updatedAt; // Was 更新时间
    private User user; // Was 用户 (Joined object)

    // Constructors
    public Comment() {
    }

    public Comment(String content, Integer userId, Integer gameId) {
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", gameId=" + gameId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + (user != null ? user.getUsername() : "null") +
                '}';
    }
}
