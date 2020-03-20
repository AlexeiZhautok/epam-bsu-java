package com.company.service;

import com.company.DAO.StudentDao;
import com.company.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class StudentService {
    Logger log = LogManager.getLogger();
    private StudentDao studentDao = new StudentDao();

    public void DeleteAll() {
        studentDao.deleteAll();
        log.info("Deleted all students");
    }

    public ArrayList<Student> ReadAll(){
        var temp = studentDao.readAll();
        int size = temp.size();
        log.info("returned" + size + " students");
        return temp;

    }
}
