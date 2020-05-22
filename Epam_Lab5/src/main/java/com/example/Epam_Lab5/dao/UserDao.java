package com.example.Epam_Lab5.dao;

import com.example.Epam_Lab5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByLogin(String name);
    User findUserById(long id);
}

