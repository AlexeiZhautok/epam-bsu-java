package com.company.service;

import com.company.dao.CourseDao;
import com.company.dao.DaoUtility;
import com.company.dao.UserDao;
import com.company.exceptions.IncorrectIdException;
import com.company.model.Course;
import com.company.model.User;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CourseService {
    private CourseDao courseDao = DaoUtility.courseDao;
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

    public void addUserToCourse (String courseID, String userID) {
        long longCourseID;
        try {
            longCourseID = checkIDCorrectness(courseID);
        } catch(IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        User userFound = userService.getByID(userID);
        if(userFound != null) {
            Course courseFound = courseDao.getByID(longCourseID);
            if(courseFound != null) {
                for(long id : courseFound.getUsers()) {
                    if(id == userFound.getId()) {
                        log.warn("Пользователь уже записан на курс");
                        return;
                    }
                }
                courseDao.addUserByID(longCourseID, userFound.getId());
                log.info("Пользователь успешно добавлен");
            } else {
                log.warn("Курса с введённым ID не существует");
                return;
            }
        } else {
            log.warn("Пользователя с введённым ID не существует - добавление в курс не произошло");
            return;
        }
    }

    public void updateInfoByID(String courseID, String name, String organization) {
        long longCourseID;
        try {
            longCourseID = checkIDCorrectness(courseID);
        } catch(IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        if(courseDao.getByID(longCourseID) != null) {
            if(courseDao.getByName(name) != null) {
                log.warn("Курс с введённым именем уже существует");
                return;
            } else {
                courseDao.updateInfoByID(longCourseID, name, organization);
            }
        } else {
            log.warn("Курса с введённым ID не существует");
        }
    }

    public void deleteByID(String courseID) {
        long longCourseID;
        try {
            longCourseID = checkIDCorrectness(courseID);
        } catch(IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        if(courseDao.getByID(longCourseID) != null) {
            courseDao.deleteByID(longCourseID);
            log.info("Курс успешно удалён");
        } else {
            log.warn("Курса с введённым ID не существует");
            return;
        }
    }

    public void removeUserByID(String courseID, String userID) {
        long longCourseID;
        try {
            longCourseID = checkIDCorrectness(courseID);
        } catch(IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        User userFound = userService.getByID(userID);
        if(userFound != null) {
            if(courseDao.getByID(longCourseID) != null) {
                courseDao.removeUserByID(longCourseID, userFound.getId());
            } else {
                log.warn("Курса с введённым ID не существует");
                return;
            }
        } else {
            log.warn("Пользователя с введённым ID не существует");
            return;
        }
    }
}
