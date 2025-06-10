package com.example.gameshare.service.impl;

import com.example.gameshare.model.Role;
import com.example.gameshare.model.User;
import com.example.gameshare.repository.RoleMapper;
import com.example.gameshare.repository.UserMapper;
import com.example.gameshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Added for @Transactional

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    // Assuming a default role name exists, e.g., "普通用户"
    private static final String DEFAULT_ROLE_NAME = "普通用户";

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Transactional
    @Override
    public User register(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在: " + user.getUsername());
        }
        // Save password in plain text as per requirement
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.insert(user); // Inserts user and sets the ID

        // Assign default role
        Role defaultRole = roleMapper.findByName(DEFAULT_ROLE_NAME);
        if (defaultRole == null) {
            // Optionally create the role if it doesn't exist, or throw an error
            // For now, let's assume it must exist or was created by RoleService
            throw new RuntimeException("默认角色 '" + DEFAULT_ROLE_NAME + "' 未找到。请先创建该角色。");
        }
        userMapper.insertUserRole(user.getId(), defaultRole.getId());
        user.setRoles(List.of(defaultRole)); // Set the role in the user object
        return user;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) { // Plain text password comparison
            // User roles are loaded by findByUsername via UserResultMap
            return user;
        }
        return null; // Or throw AuthenticationException
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        int updatedCount = userMapper.update(user);
        if (updatedCount > 0) {
            return userMapper.findById(user.getId());
        }
        return null; // Or throw exception
    }

    @Override
    public void deleteUser(Integer id) {
        // Consider deleting from 用户角色 first if needed, or rely on DB cascade if set
        // For now, assuming UserMapper.delete handles or cascades user_roles deletion, or it's handled separately
        userMapper.delete(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public void assignRoleToUser(Integer userId, Integer roleId) {
        User user = userMapper.findById(userId);
        Role role = roleMapper.findById(roleId);
        if (user == null || role == null) {
            throw new RuntimeException("用户或角色未找到。");
        }
        // Check if user already has this role to avoid duplicates if necessary
        List<Role> currentRoles = userMapper.findRolesByUserId(userId);
        if (currentRoles.stream().noneMatch(r -> r.getId().equals(roleId))) {
            userMapper.insertUserRole(userId, roleId);
        }
    }

    @Override
    public List<Role> findUserRoles(Integer userId) {
        User user = userMapper.findById(userId);
        if (user != null && user.getRoles() != null) {
            return user.getRoles();
        }
        // Fallback or alternative way if roles are not eagerly fetched by findById or if separate fetch is desired
        return userMapper.findRolesByUserId(userId);
    }

    @Override
    @Transactional
    public void clearUserRoles(Integer userId) {
        userMapper.deleteUserRolesByUserId(userId);
    }
}
