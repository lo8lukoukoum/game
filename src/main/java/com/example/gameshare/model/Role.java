package com.example.gameshare.model;

public class Role {

    private Integer id;
    private String 角色名称; // role name

    // Constructors
    public Role() {
    }

    public Role(String 角色名称) {
        this.角色名称 = 角色名称;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String get角色名称() {
        return 角色名称;
    }

    public void set角色名称(String 角色名称) {
        this.角色名称 = 角色名称;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", 角色名称='" + 角色名称 + '\'' +
                '}';
    }
}
