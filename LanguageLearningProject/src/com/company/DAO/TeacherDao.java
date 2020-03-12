package com.company.DAO;

import com.company.model.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TeacherDao {
    public void createTeacher(Teacher teacher) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Teachers/TeacherDatabase.txt", true))) {
            writer.write(teacher.toStringFileFormat());
            writer.newLine();
        } catch (IOException ignored) {
        }
    }

    public ArrayList<Teacher> readAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Teachers/TeacherDatabase.txt"))) {
            ArrayList<Teacher> teachers = new ArrayList<>();
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tempLine, "-");
                long id = Long.parseLong(st.nextToken());
                String login = st.nextToken();
                String password = st.nextToken();
                String email = st.nextToken();
                teachers.add(new Teacher(id, login, password, email));
            }
            return teachers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Teachers/TeacherDatabase.txt"))) {
            writer.write("");
        } catch (IOException ignored) {
        }
    }
}
