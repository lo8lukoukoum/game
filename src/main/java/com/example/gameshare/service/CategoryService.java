package com.example.gameshare.service;

import com.example.gameshare.model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> findAllCategories();

    Category findCategoryById(Integer id);

    Category updateCategory(Category category);

    void deleteCategory(Integer id);
}
