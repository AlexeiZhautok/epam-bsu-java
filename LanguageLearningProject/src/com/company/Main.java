package com.company;

import com.company.DAO.TeacherDao;
import com.company.model.Teacher;

public class Main {
    public static void main(String[] args) {
        Teacher MaryaIvanovna = new Teacher(1, "MI", "12345", "MI@sasi.com");
        TeacherDao kto = new TeacherDao();
        kto.createTeacher(MaryaIvanovna);
        kto.deleteAll();
    }
}

