package com.company.service;

import com.company.dao.UserDao;
import com.company.exceptions.IncorrectIdException;
import com.company.exceptions.IncorrectRoleException;
import com.company.model.User;
import com.company.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    Logger log = UserDao.log;

    private UserRole checkRoleCorrectness(String inputRole) {
        UserRole role;
        try {
            role = UserRole.valueOf(inputRole);
        } catch (Exception e) {
            throw new IncorrectRoleException();
        }
        return role;
    }

    private boolean checkFieldsEmptiness(String login, String password, String email) {
        return (!login.isEmpty() && !password.isEmpty() && !email.isEmpty());
    }

    private long checkIDCorrectness(String inputID) {
        long ID = 0;
        try {
            ID = Long.valueOf(inputID);
        } catch (NumberFormatException e) {
            throw new IncorrectIdException();
        }
        if(ID <= 0) {
            throw new IncorrectIdException();
        }
        return ID;
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getByID(String inputID) {
        long ID = 0;
        try {
            ID = checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return null;
        }
        return userDao.getById(ID);
    }

    public void createUser(String login, String password, String email, String inputRole) {
        UserRole role;
        try {
            role = checkRoleCorrectness(inputRole);
        } catch (IncorrectRoleException e) {
            log.warn("Введена неправильная роль");
            return;
        }
        if (checkFieldsEmptiness(login, password, email)) {
            if(userDao.getByLogin(login) != null) {
                log.warn("Пользователь с введённым логином уже существует");
                return;
            }
            userDao.createUser(login, password, email, role);
            log.info("Пользователь успешно создан");
        } else {
            log.warn("Введены некорректные данные");
            return;
        }
    }

    public void updateByID(String inputID, String newLogin, String newPassword, String newEmail, String inputNewRole) {
        long ID = 0;
        UserRole newRole;
        try {
            newRole = checkRoleCorrectness(inputNewRole);
        } catch (IncorrectRoleException e) {
            log.warn("Введена неправильная роль");
            return;
        }
        try {
            ID = checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        if(getByID(inputID) != null) {
            if (checkFieldsEmptiness(newLogin, newPassword, newEmail)) {
                userDao.updateById(ID, new User(ID, newLogin, newPassword, newEmail, newRole));
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

    public void deleteByID(String inputID) {
        long ID = 0;
        try {
            ID = checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        if(getByID(inputID) != null) {
            userDao.deleteById(ID);
            log.info("Пользователь успешно удалён");
        } else {
            log.info("Пользователя с введённым ID не существует");
            return;
        }
    }
}
