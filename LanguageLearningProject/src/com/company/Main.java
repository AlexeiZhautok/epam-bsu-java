package com.company;

import com.company.DAO.TeacherDao;
import com.company.model.Teacher;

public class Main {
    public static void main(String[] args) {
        Teacher MaryaIvanovna = new Teacher(1, "MI", "12345", "MI@sasi.com");
        Teacher MaryaIvanovna1 = new Teacher(2, "MI", "12345", "MI@sasi.com");
        Teacher MaryaIvanovna2 = new Teacher(3, "MI", "12345", "MI@sasi.com");
        TeacherDao kto = new TeacherDao();
        kto.createTeacher(MaryaIvanovna);
        kto.createTeacher(MaryaIvanovna1);
        kto.createTeacher(MaryaIvanovna2);
        kto.UpdateById(2, new Teacher(228, "kto" ,"ktou", "kto@mail.ru"));
    }
}

