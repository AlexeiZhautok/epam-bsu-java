package com.company.servicedb;

import com.company.dao.CourseDao;
import com.company.dbdao.DatabaseCourseDao;
import com.company.dbdao.DatabaseUserDao;
import com.company.exceptions.IncorrectIdException;
import com.company.model.Course;
import com.company.model.User;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CourseServiceDatabase {
    private DatabaseCourseDao databaseCourseDao = new DatabaseCourseDao();
    private DatabaseUserDao databaseUserDao = new DatabaseUserDao();
    private Logger log = DatabaseCourseDao.log;

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

    public List<Course> getAll() {
        List<Course> temp = databaseCourseDao.getAll();
        for(var i : temp) {
            databaseCourseDao.getUsers(i);
        }
        return temp;
    }

    public Course getByID(String inputID) {
        long ID = 0;
        try {
            ID = checkIDCorrectness(inputID);
        } catch (IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return null;
        }
        Course temp = databaseCourseDao.getByID(ID);
        databaseCourseDao.getUsers(temp);
        return temp;
    }

    public void createNewCourse(String name, String organization) {
        if(databaseCourseDao.getByName(name) != null) {
            log.warn("Курс с таким именем уже существует");
            return;
        }
        if(checkFieldsEmptiness(name, organization)) {
            databaseCourseDao.insert(new Course(databaseCourseDao.getLastID() + 1, name, organization));
            log.info("Курс успешно создан");
        } else {
            log.warn("Введены некорректные данные");
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
        if(databaseCourseDao.getByID(longCourseID) != null) {
            if(databaseCourseDao.getByName(name) != null) {
                log.warn("Курс с введённым именем уже существует");
                return;
            } else {
                databaseCourseDao.updateByID(new Course(longCourseID, name, organization));
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
        if(databaseCourseDao.getByID(longCourseID) != null) {
            databaseCourseDao.deleteByID(longCourseID);
            log.info("Курс успешно удалён");
        } else {
            log.warn("Курса с введённым ID не существует");
            return;
        }
    }

    public void addUserToCourse (String courseID, String userID) {
        long longCourseID, longUserID;
        try {
            longCourseID = checkIDCorrectness(courseID);
            longUserID = checkIDCorrectness(userID);
        } catch(IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        User userFound = databaseUserDao.getByID(longUserID);
        if(userFound != null) {
            Course courseFound = databaseCourseDao.getByID(longCourseID);
            if(courseFound != null) {
                for(long id : courseFound.getUsers()) {
                    if(id == userFound.getId()) {
                        log.warn("Пользователь уже записан на курс");
                        return;
                    }
                }
                databaseCourseDao.addUser(longCourseID, userFound.getId());
                log.info("Пользователь успешно добавлен");
                databaseCourseDao.getUsers(courseFound);
            } else {
                log.warn("Курса с введённым ID не существует");
                return;
            }
        } else {
            log.warn("Пользователя с введённым ID не существует - добавление в курс не произошло");
            return;
        }
    }

    public void removeUserByID(String courseID, String userID) {
        long longCourseID, longUserID;
        try {
            longCourseID = checkIDCorrectness(courseID);
            longUserID = checkIDCorrectness(userID);
        } catch(IncorrectIdException e) {
            log.warn("Введён неправильный ID");
            return;
        }
        User userFound = databaseUserDao.getByID(longUserID);
        if(userFound != null) {
            Course courseFound = databaseCourseDao.getByID(longCourseID);
            if(courseFound != null) {
                databaseCourseDao.deleteUser(longCourseID, userFound.getId());
                log.info("Пользователь успешно удалён");
                databaseCourseDao.getUsers(courseFound);
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
