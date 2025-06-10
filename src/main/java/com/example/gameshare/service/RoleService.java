package com.example.gameshare.service;

import com.example.gameshare.model.Role;

public interface RoleService {

    Role createRole(Role role);

    Role findRoleByName(String roleName);

    Role findRoleById(Integer id);

    java.util.List<com.example.gameshare.model.Role> findAllRoles();
}
