package com.example.gameshare.service.impl;

import com.example.gameshare.model.Role;
import com.example.gameshare.repository.RoleMapper;
import com.example.gameshare.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Role createRole(Role role) {
        if (roleMapper.findByName(role.get角色名称()) != null) {
            throw new RuntimeException("角色名称已存在: " + role.get角色名称());
        }
        roleMapper.insert(role);
        return role;
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleMapper.findByName(roleName);
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public java.util.List<com.example.gameshare.model.Role> findAllRoles() {
        return roleMapper.findAll();
    }
}
