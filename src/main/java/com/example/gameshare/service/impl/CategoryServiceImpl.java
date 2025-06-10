package com.example.gameshare.service.impl;

import com.example.gameshare.model.Category;
import com.example.gameshare.repository.CategoryMapper;
import com.example.gameshare.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category createCategory(Category category) {
        category.set创建时间(LocalDateTime.now());
        category.set更新时间(LocalDateTime.now());
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryMapper.findAll();
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public Category updateCategory(Category category) {
        category.set更新时间(LocalDateTime.now());
        int updatedCount = categoryMapper.update(category);
        if (updatedCount > 0) {
            return categoryMapper.findById(category.getId());
        }
        // Or throw an exception if not found / not updated
        return null;
    }

    @Override
    public void deleteCategory(Integer id) {
        // Consider implications: what happens to games in this category?
        // For now, simple deletion.
        categoryMapper.delete(id);
    }
}
