package com.example.gameshare.repository;

import com.example.gameshare.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User findById(Integer id);

    User findByUsername(String username);

    int insert(User user);

    int update(User user);

    int delete(Integer id);

    List<User> findAll();

    void insertUserRole(@org.apache.ibatis.annotations.Param("userId") Integer userId, @org.apache.ibatis.annotations.Param("roleId") Integer roleId);

    List<com.example.gameshare.model.Role> findRolesByUserId(Integer userId);

    void deleteUserRolesByUserId(Integer userId);
}
