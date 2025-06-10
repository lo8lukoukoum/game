package com.example.gameshare.model;

import java.time.LocalDateTime;

public class Rating {

    private Integer id;
    private Integer 分数; // score (e.g., 1-5)
    private Integer 用户id; // user_id
    private Integer 游戏id; // game_id
    private LocalDateTime 创建时间; // create_time
    private LocalDateTime 更新时间; // update_time

    // Constructors
    public Rating() {
    }

    public Rating(Integer 分数, Integer 用户id, Integer 游戏id) {
        this.分数 = 分数;
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

    public Integer get分数() {
        return 分数;
    }

    public void set分数(Integer 分数) {
        this.分数 = 分数;
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

    public LocalDateTime get创建时间() {
        return 创建时间;
    }

    public void set创建时间(LocalDateTime 创建时间) {
        this.创建时间 = 创建时间;
    }

    public LocalDateTime get更新时间() {
        return 更新时间;
    }

    public void set更新时间(LocalDateTime 更新时间) {
        this.更新时间 = 更新时间;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", 分数=" + 分数 +
                ", 用户id=" + 用户id +
                ", 游戏id=" + 游戏id +
                ", 创建时间=" + 创建时间 +
                ", 更新时间=" + 更新时间 +
                '}';
    }
}
