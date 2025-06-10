package com.example.gameshare.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Game {

    private Integer id;
    private String name; // Was 游戏名称
    private String description; // Was 描述
    private LocalDate releaseDate; // Was 发行日期
    private String imageUrl; // Was 图片链接
    private Integer categoryId; // Was 分类id
    private LocalDateTime createdAt; // Was 创建时间
    private LocalDateTime updatedAt; // Was 更新时间
    private Category category; // Was 游戏分类 (Joined object)

    // Constructors
    public Game() {
    }

    public Game(String name, String description, LocalDate releaseDate, String imageUrl, Integer categoryId) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryId=" + categoryId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", category=" + (category != null ? category.getName() : "null") +
                '}';
    }
}
