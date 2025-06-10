package com.example.gameshare.service;

import com.example.gameshare.model.Role;
import com.example.gameshare.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    User login(String username, String password);

    User findById(Integer id);

    User findByUsername(String username);

    User updateUser(User user);

    void deleteUser(Integer id);

    List<User> findAllUsers();

    void assignRoleToUser(Integer userId, Integer roleId);

    List<Role> findUserRoles(Integer userId);

    void clearUserRoles(Integer userId);
}
