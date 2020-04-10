package com.company.service;

import com.company.dao.CourseDao;
import com.company.dao.UserDao;
import com.company.exceptions.IncorrectIdException;
import com.company.model.Course;
import com.company.model.User;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CourseService {
    private CourseDao courseDao = new CourseDao();
    Logger log = CourseDao.log;
    UserService userService = ServiceUtility.userService;

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

    private boolean checkFieldsEmptiness(String name, String organization) {
        return (!name.isEmpty() && !organization.isEmpty());
    }

    public void deleteAll() {
        courseDao.deleteAll();
    }

    public List<Course> getAll() {
        return courseDao.getAll();
    }

    public Course getByID(String inputID) {
        long ID = 0;
        try {
            ID = checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return null;
        }
        return courseDao.getByID(ID);
    }

    public void createNewCourse(String name, String organization) {
        if(courseDao.getByName(name) != null) {
            log.warn("Курс с таким именем уже существует");
            return;
        }
        if(checkFieldsEmptiness(name, organization)) {
            courseDao.createNewCourse(name, organization);
            log.info("Курс успешно создан");
        } else {
            log.warn("Введены некорректные данные");
            return;
        }
    }

    public void addUserToCourse (long courseID, long userID) {
        if(userService.getByID(Long.toString(userID)) != null) {
            if(courseDao.getByID(courseID) != null) {
                courseDao.addUserByID(courseID, userID);
                log.info("Пользователь успешно добавлен");
            } else {
                log.warn("Курса с введённым ID не существует");
                return;
            }
        } else {
            log.warn("Пользователя с введённым ID не существует - добавление в курс не произошло");
        }
    }

    public void updateInfoByID(long courseID, String name, String organization) {

    }
}
