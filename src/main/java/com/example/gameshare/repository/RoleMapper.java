package com.example.gameshare.repository;

import com.example.gameshare.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    Role findById(Integer id);

    Role findByName(String roleName);

    int insert(Role role);

    java.util.List<Role> findAll();
}
