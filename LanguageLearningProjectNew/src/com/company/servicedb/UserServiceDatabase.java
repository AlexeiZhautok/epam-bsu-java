package com.company.servicedb;

import com.company.dbdao.DatabaseDao;
import com.company.dbdao.DatabaseUserDao;
import com.company.exceptions.IncorrectEmailException;
import com.company.exceptions.IncorrectIdException;
import com.company.exceptions.IncorrectRoleException;
import com.company.model.User;
import com.company.model.UserRole;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceDatabase {
    Logger log = DatabaseDao.log;
    DatabaseUserDao databaseUserDao = new DatabaseUserDao();

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

    public List<User> getAll() {
        return databaseUserDao.getAll();
    }

    public void createUser(String login, String password, String inputEmail, String inputRole) {
        UserRole role;
        try {
            role = checkRoleCorrectness(inputRole);
        } catch (IncorrectRoleException e) {
            log.warn("Введена неправильная роль");
            return;
        }
        if(databaseUserDao.getByLogin(login) != null) {
            log.warn("Пользователь с таким логином уже существует");
            return;
        }
        if (checkFieldsEmptiness(login, password, inputEmail)) {
            databaseUserDao.insert(new User(databaseUserDao.getLastID() + 1, login, password, inputEmail, role));
            log.info("Пользователь успешно создан");
        } else {
            log.warn("Введены некорректные данные");
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
        if(databaseUserDao.getByID(ID) != null) {
            databaseUserDao.deleteByID(ID);
            log.info("Пользователь успешно удалён");
        } else {
            log.info("Пользователя с введённым ID не существует");
            return;
        }
    }

    public User getByID(String inputID) {
        long ID = 0;
        try {
            ID = checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return null;
        }
        return databaseUserDao.getByID(ID);
    }
}
