package com.example.Epam_Lab5.dao;

import com.example.Epam_Lab5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {
    User findByLogin(String name);
    User findUserById(long id);
}

