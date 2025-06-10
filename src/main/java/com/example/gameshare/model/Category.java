package com.example.gameshare.model;

import java.time.LocalDateTime;

public class Category {

    private Integer id;
    private String 分类名称; // category name
    private String 描述; // description
    private LocalDateTime 创建时间; // create_time
    private LocalDateTime 更新时间; // update_time

    // Constructors
    public Category() {
    }

    public Category(String 分类名称, String 描述) {
        this.分类名称 = 分类名称;
        this.描述 = 描述;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String get分类名称() {
        return 分类名称;
    }

    public void set分类名称(String 分类名称) {
        this.分类名称 = 分类名称;
    }

    public String get描述() {
        return 描述;
    }

    public void set描述(String 描述) {
        this.描述 = 描述;
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
        return "Category{" +
                "id=" + id +
                ", 分类名称='" + 分类名称 + '\'' +
                ", 描述='" + 描述 + '\'' +
                ", 创建时间=" + 创建时间 +
                ", 更新时间=" + 更新时间 +
                '}';
    }
}
