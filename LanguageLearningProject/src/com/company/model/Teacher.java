package com.company.model;

public class Teacher extends User {

    public Teacher(long id, String login, String password, String email) {
        super(id, login, password, email, UserRole.TEACHER);
    }
}
