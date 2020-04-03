package com.company.model;

import com.company.dao.UserDao;

import java.util.Objects;

public class User {
    //Сюда биографию
    private long id;
    private String login;
    private String password;
    private String email;
    private UserRole role;

    public User(long id, String login, String password, String email, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }
//    public void setLogin(String login) {
//        this.login = login;
//    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role);
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Login: " + login + "\n" +
                "Password: " + password + "\n" +
                "E-Mail: " + email + "\n" +
                "Role: " + role + "\n";
    }

    public String toStringFileFormat() {
        String delim = UserDao.delim;
        return id + delim +
                login + delim +
                password + delim +
                email + delim +
                role;
    }
}
