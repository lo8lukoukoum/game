package com.example.gameshare.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Game {

    private Integer id;
    private String 游戏名称; // game name
    private String 描述; // description
    private LocalDate 发行日期; // release_date
    private String 图片链接; // image_url
    private Integer 分类id; // category_id
    private LocalDateTime 创建时间; // create_time
    private LocalDateTime 更新时间; // update_time
    private Category 游戏分类; // Category object for join

    // Constructors
    public Game() {
    }

    public Game(String 游戏名称, String 描述, LocalDate 发行日期, String 图片链接, Integer 分类id) {
        this.游戏名称 = 游戏名称;
        this.描述 = 描述;
        this.发行日期 = 发行日期;
        this.图片链接 = 图片链接;
        this.分类id = 分类id;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String get游戏名称() {
        return 游戏名称;
    }

    public void set游戏名称(String 游戏名称) {
        this.游戏名称 = 游戏名称;
    }

    public String get描述() {
        return 描述;
    }

    public void set描述(String 描述) {
        this.描述 = 描述;
    }

    public LocalDate get发行日期() {
        return 发行日期;
    }

    public void set发行日期(LocalDate 发行日期) {
        this.发行日期 = 发行日期;
    }

    public String get图片链接() {
        return 图片链接;
    }

    public void set图片链接(String 图片链接) {
        this.图片链接 = 图片链接;
    }

    public Integer get分类id() {
        return 分类id;
    }

    public void set分类id(Integer 分类id) {
        this.分类id = 分类id;
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

    public Category get游戏分类() {
        return 游戏分类;
    }

    public void set游戏分类(Category 游戏分类) {
        this.游戏分类 = 游戏分类;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", 游戏名称='" + 游戏名称 + '\'' +
                ", 描述='" + 描述 + '\'' +
                ", 发行日期=" + 发行日期 +
                ", 图片链接='" + 图片链接 + '\'' +
                ", 分类id=" + 分类id +
                ", 创建时间=" + 创建时间 +
                ", 更新时间=" + 更新时间 +
                ", 游戏分类=" + (游戏分类 != null ? 游戏分类.get分类名称() : "null") +
                '}';
    }
}
