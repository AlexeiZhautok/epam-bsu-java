package com.company.model;

public class Student extends User {

    public Student(long id, String login, String password, String email) {
        super(id, login, password, email, UserRole.STUDENT);
    }
}
