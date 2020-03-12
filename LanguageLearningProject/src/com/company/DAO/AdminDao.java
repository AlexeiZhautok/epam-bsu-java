package com.company.DAO;

import com.company.model.Admin;
import com.company.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AdminDao {
    public void createAdmin(Admin admin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Admins/AdminDatabase.txt", true))) {
            writer.write(admin.toStringFileFormat());
            writer.newLine();
        } catch (IOException ignored) {
        }
    }

    public ArrayList<Admin> readAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Admins/AdminDatabase.txt"))) {
            ArrayList<Admin> admins = new ArrayList<>();
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tempLine, "-");
                long id = Long.parseLong(st.nextToken());
                String login = st.nextToken();
                String password = st.nextToken();
                String email = st.nextToken();
                admins.add(new Admin(id, login, password, email));
            }
            return admins;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
}

    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Admins/AdminDatabase.txt"))) {
            writer.write("");
        } catch (IOException ignored) {
        }
    }

    public void deleteById(long inputId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Admins/AdminDatabase.txt"))) {
            ArrayList<Admin> admins = new ArrayList<>();
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tempLine, "-");
                long id = Long.parseLong(st.nextToken());
                String login = st.nextToken();
                String password = st.nextToken();
                String email = st.nextToken();
                admins.add(new Admin(id, login, password, email));
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Admins/AdminDatabase.txt"));
            for (Admin adminIter : admins) {
                if (adminIter.getId() != inputId) {
                    createAdmin(adminIter);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Admin GetById(long inputId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Admins/AdminDatabase.txt"))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tempLine, "-");
                long id = Long.parseLong(st.nextToken());
                String login = st.nextToken();
                String password = st.nextToken();
                String email = st.nextToken();
                if (id == inputId) {
                    return new Admin(id, login, password, email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        //throw new ...Exception();
    }

    public void UpdateById (long inputId, Admin newAdmin) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Admins/AdminDatabase.txt"))) {
            ArrayList<Admin> admins = new ArrayList<>();
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tempLine, "-");
                long id = Long.parseLong(st.nextToken());
                String login = st.nextToken();
                String password = st.nextToken();
                String email = st.nextToken();
                if((id == inputId)) {
                    admins.add(new Admin(newAdmin.getId(), newAdmin.getLogin(), newAdmin.getPassword(), newAdmin.getEmail()));
                } else {
                    admins.add(new Admin(id, login, password, email));
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("Database/Admins/AdminDatabase.txt"));
            //Teacher teacher;
            for (Admin adminIter : admins) {
                // teacher = teacherIter;
                if (adminIter.getId() != inputId) {
                    createAdmin(adminIter);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
