package com.company.service;

import com.company.DAO.TeacherDao;
import com.company.model.Teacher;

import java.util.ArrayList;

public class TeacherService {

    private TeacherDao teacherDao = new TeacherDao();

    public void DeleteAll() { teacherDao.deleteAll(); };

    public ArrayList<Teacher> ReadAll() { return teacherDao.readAll(); };
}
