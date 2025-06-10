package com.example.gameshare.model;

import java.time.LocalDateTime;
import java.util.List;

public class User {

    private Integer id;
    private String 用户名; // username
    private String 密码; // password
    private String 邮箱; // email
    private LocalDateTime 创建时间; // create_time
    private LocalDateTime 更新时间; // update_time
    private List<Role> 角色列表; // list of roles

    // Constructors
    public User() {
    }

    public User(String 用户名, String 密码, String 邮箱) {
        this.用户名 = 用户名;
        this.密码 = 密码;
        this.邮箱 = 邮箱;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String get用户名() {
        return 用户名;
    }

    public void set用户名(String 用户名) {
        this.用户名 = 用户名;
    }

    public String get密码() {
        return 密码;
    }

    public void set密码(String 密码) {
        this.密码 = 密码;
    }

    public String get邮箱() {
        return 邮箱;
    }

    public void set邮箱(String 邮箱) {
        this.邮箱 = 邮箱;
    }

    public LocalDateTime get创建时间() {
        return 创建时间;
    }

    public void set创建时间(LocalDateTime 创建时间) {
        this.创建时间 = 创建时间;
    }

    public LocalDateTime get更新时间() {
        return 更新时间;
    }

    public void set更新时间(LocalDateTime 更新时间) {
        this.更新时间 = 更新时间;
    }

    public List<Role> get角色列表() {
        return 角色列表;
    }

    public void set角色列表(List<Role> 角色列表) {
        this.角色列表 = 角色列表;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", 用户名='" + 用户名 + '\'' +
                ", 密码='" + "[PROTECTED]" + '\'' +
                ", 邮箱='" + 邮箱 + '\'' +
                ", 创建时间=" + 创建时间 +
                ", 更新时间=" + 更新时间 +
                ", 角色列表=" + 角色列表 +
                '}';
    }
}
