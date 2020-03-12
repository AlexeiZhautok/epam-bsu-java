package com.company.model;

public class Admin extends User {
        public Admin(long id, String login, String password, String email) {
            super(id, login, password, email, UserRole.ADMIN);
        }
}
