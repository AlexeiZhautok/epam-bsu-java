package com.company.service;

import com.company.DAO.TeacherDao;
import com.company.model.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class TeacherService {
    Logger log = LogManager.getLogger();
    private TeacherDao teacherDao = new TeacherDao();

    public void DeleteAll() {
        teacherDao.deleteAll();
        log.info("Deleted all teachers");
    };

    public ArrayList<Teacher> ReadAll() {
        var temp = teacherDao.readAll();
        int size = temp.size();
        log.info("returned" + size + " teachers");
        return temp;
    };
}
