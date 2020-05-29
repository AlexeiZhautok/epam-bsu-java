package com.example.Epam_Lab5.dao;

import com.example.Epam_Lab5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findUserById(long id);
}

