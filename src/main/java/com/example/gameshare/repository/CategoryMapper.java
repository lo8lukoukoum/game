package com.example.gameshare.repository;

import com.example.gameshare.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    Category findById(Integer id);

    List<Category> findAll();

    int insert(Category category);

    int update(Category category);

    int delete(Integer id);
}
