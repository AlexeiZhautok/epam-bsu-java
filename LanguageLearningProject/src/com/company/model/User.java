package com.company.model;

import java.lang.String;
import java.util.Objects;
import javax.xml.crypto.Data;

public class User {
    private long id;
    private String login;
    private String password;
    private String email;
    private UserRole role;
    private Description description;

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

    public Description getDescription() {
        return description;
    }
    public void setDescription(Data birthday, String name, String surname, String description, String[] interesting) {
        this.description = new Description(birthday, name, surname,description,  interesting);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return  Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, role);
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" + "Login: " + login + "\n" + "Password: " + password +"\n" + "E-Mail: " + email + "\n" + "Role" + role + "\n";
    }

    public String toStringFileFormat() {
        String filler = "-";
        return id + filler + login + filler + password + filler + email;
    }
}
