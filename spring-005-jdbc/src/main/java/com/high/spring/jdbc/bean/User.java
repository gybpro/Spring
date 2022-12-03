package com.high.spring.jdbc.bean;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class User {
    private Integer id;
    // 自动映射属性名与列名一致或均为映射经典命名即可匹配
    private String username;
    private String password;
    // private String passWord;

    public User() {
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
