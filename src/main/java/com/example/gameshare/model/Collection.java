package com.example.gameshare.model;

import java.time.LocalDateTime;

public class Collection {

    private Integer id;
    private Integer 用户id; // user_id
    private Integer 游戏id; // game_id
    private LocalDateTime 收藏时间; // collection_time
    private User 用户; // User object for join
    private Game 游戏; // Game object for join

    // Constructors
    public Collection() {
    }

    public Collection(Integer 用户id, Integer 游戏id) {
        this.用户id = 用户id;
        this.游戏id = 游戏id;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer get用户id() {
        return 用户id;
    }

    public void set用户id(Integer 用户id) {
        this.用户id = 用户id;
    }

    public Integer get游戏id() {
        return 游戏id;
    }

    public void set游戏id(Integer 游戏id) {
        this.游戏id = 游戏id;
    }

    public LocalDateTime get收藏时间() {
        return 收藏时间;
    }

    public void set收藏时间(LocalDateTime 收藏时间) {
        this.收藏时间 = 收藏时间;
    }

    public User get用户() {
        return 用户;
    }

    public void set用户(User 用户) {
        this.用户 = 用户;
    }

    public Game get游戏() {
        return 游戏;
    }

    public void set游戏(Game 游戏) {
        this.游戏 = 游戏;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", 用户id=" + 用户id +
                ", 游戏id=" + 游戏id +
                ", 收藏时间=" + 收藏时间 +
                ", 用户=" + (用户 != null ? 用户.get用户名() : "null") +
                ", 游戏=" + (游戏 != null ? 游戏.get游戏名称() : "null") +
                '}';
    }
}
