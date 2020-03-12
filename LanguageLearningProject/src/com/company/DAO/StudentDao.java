package com.company.DAO;

import com.company.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StudentDao {
    public void createStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Students/StudentDatabase.txt", true))) {
            writer.write(student.toStringFileFormat());
            writer.newLine();
        } catch (IOException ignored) {
        }
    }

    public ArrayList<Student> readAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Students/StudentDatabase.txt"))) {
            ArrayList<Student> students = new ArrayList<>();
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tempLine, "-");
                long id = Long.parseLong(st.nextToken());
                String login = st.nextToken();
                String password = st.nextToken();
                String email = st.nextToken();
                students.add(new Student(id, login, password, email));
            }
            return students;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Students/StudentDatabase.txt"))) {
            writer.write("");
        } catch (IOException ignored) {
        }
    }
}
