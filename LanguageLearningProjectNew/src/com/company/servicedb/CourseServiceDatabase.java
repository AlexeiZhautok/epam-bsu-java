package com.company.servicedb;

import com.company.dao.CourseDao;
import com.company.dbdao.DatabaseCourseDao;
import com.company.dbdao.DatabaseUserDao;
import com.company.exceptions.IncorrectIdException;
import com.company.model.Course;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CourseServiceDatabase {
    DatabaseCourseDao databaseCourseDao = new DatabaseCourseDao();
    Logger log = DatabaseCourseDao.log;

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

//    public void createNewCourse(String name, String organization) {
//        if(courseDao.getByName(name) != null) {
//            log.warn("Курс с таким именем уже существует");
//            return;
//        }
//        if(checkFieldsEmptiness(name, organization)) {
//            courseDao.createNewCourse(name, organization);
//            log.info("Курс успешно создан");
//        } else {
//            log.warn("Введены некорректные данные");
//            return;
//        }
//    }
}
