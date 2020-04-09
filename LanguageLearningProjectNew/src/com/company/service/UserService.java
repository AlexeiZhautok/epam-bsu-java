package com.company.service;

import com.company.dao.UserDao;
import com.company.exceptions.IncorrectIdException;
import com.company.model.User;
import com.company.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    Logger log = UserDao.log;

    private void checkIDCorrectness(long inputID) {
        if(inputID <= 0) {
            throw new IncorrectIdException();
        }
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getByID(long inputID) {
        try {
            checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return null;
        }
        return userDao.getById(inputID);
    }

    public void createUser(String login, String password, String email, UserRole role) {
        if (login != null && password != null && email != null) {
            userDao.createUser(login, password, email, role);
            log.info("Пользователь успешно создан");
        } else {
            log.warn("Введены некорректные данные");
            return;
        }
    }

    public void updateByID(long inputID, String newLogin, String newPassword, String newEmail, UserRole newRole) {
        try {
            checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        if(getByID(inputID) != null) {
            if (newLogin != null && newPassword != null && newEmail != null) {
                userDao.updateById(inputID, new User(inputID, newLogin, newPassword, newEmail, newRole));
                log.info("Пользователь успешно обновлён");
            } else {
                log.warn("Новый пользователь имеет некорректные данные");
                return;
            }
        } else {
            log.warn("Пользователя с введённым ID не существует");
            return;
        }
    }

    public void deleteByID(long inputID) {
        try {
            checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        if(getByID(inputID) != null) {
            userDao.deleteById(inputID);
            log.info("Пользователь успешно удалён");
        } else {
            log.info("Пользователя с введённым ID не существует");
            return;
        }
    }
}
