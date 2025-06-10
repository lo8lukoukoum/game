package com.example.gameshare.model;

import java.time.LocalDateTime;

public class Comment {

    private Integer id;
    private String 评论内容; // content
    private Integer 用户id; // user_id
    private Integer 游戏id; // game_id
    private LocalDateTime 创建时间; // create_time
    private LocalDateTime 更新时间; // update_time
    private User 用户; // User object for join

    // Constructors
    public Comment() {
    }

    public Comment(String 评论内容, Integer 用户id, Integer 游戏id) {
        this.评论内容 = 评论内容;
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

    public String get评论内容() {
        return 评论内容;
    }

    public void set评论内容(String 评论内容) {
        this.评论内容 = 评论内容;
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

    public User get用户() {
        return 用户;
    }

    public void set用户(User 用户) {
        this.用户 = 用户;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", 评论内容='" + 评论内容 + '\'' +
                ", 用户id=" + 用户id +
                ", 游戏id=" + 游戏id +
                ", 创建时间=" + 创建时间 +
                ", 更新时间=" + 更新时间 +
                ", 用户=" + (用户 != null ? 用户.get用户名() : "null") +
                '}';
    }
}
