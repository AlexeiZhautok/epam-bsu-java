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

    public void deleteById(long inputId) {
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
            BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Teachers/TeacherDatabase.txt"));
            //Teacher teacher;
            for (Teacher teacherIter : teachers) {
               // teacher = teacherIter;
                if (teacherIter.getId() != inputId) {
                    createTeacher(teacherIter);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Teacher GetById(long inputId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Teachers/TeacherDatabase.txt"))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tempLine, "-");
                long id = Long.parseLong(st.nextToken());
                String login = st.nextToken();
                String password = st.nextToken();
                String email = st.nextToken();
                if (id == inputId) {
                    return new Teacher(id, login, password, email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        //throw new ...Exception();
    }

    public void UpdateById (long inputId, Teacher newTeacher) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Teachers/TeacherDatabase.txt"))) {
            ArrayList<Teacher> teachers = new ArrayList<>();
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tempLine, "-");
                long id = Long.parseLong(st.nextToken());
                String login = st.nextToken();
                String password = st.nextToken();
                String email = st.nextToken();
                if((id == inputId)) {
                    teachers.add(new Teacher(newTeacher.getId(), newTeacher.getLogin(), newTeacher.getPassword(), newTeacher.getEmail()));
                } else {
                    teachers.add(new Teacher(id, login, password, email));
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Teachers/TeacherDatabase.txt"));
            //Teacher teacher;
            for (Teacher teacherIter : teachers) {
                // teacher = teacherIter;
                if (teacherIter.getId() != inputId) {
                    createTeacher(teacherIter);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}