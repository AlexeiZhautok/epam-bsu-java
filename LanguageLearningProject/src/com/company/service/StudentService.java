package com.company.service;

import com.company.DAO.StudentDao;
import com.company.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class StudentService {
    Logger log = LogManager.getLogger();
    private StudentDao studentDao = new StudentDao();

    public void DeleteAll()
    {
        studentDao.deleteAll();
    }

    public ArrayList<Student> ReadAll(){
        return studentDao.readAll();
    }
}
