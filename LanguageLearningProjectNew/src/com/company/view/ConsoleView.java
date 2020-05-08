package com.company.view;

import com.company.service.CourseService;
import com.company.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ConsoleView {

    public void showInterface() {
        Logger.getLogger("com.zaxxer.hikari.pool.PoolBase").setLevel(Level.INFO);
        Logger.getLogger("com.zaxxer.hikari.pool.HikariPool").setLevel(Level.INFO);
        Logger.getLogger("com.zaxxer.hikari.HikariDataSource").setLevel(Level.INFO);
        Logger.getLogger("com.zaxxer.hikari.HikariConfig").setLevel(Level.INFO);
        Logger.getLogger("com.zaxxer.hikari.util.DriverDataSource").setLevel(Level.INFO);
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        CourseService courseService = new CourseService();
        UserView userView = new UserView();
        CourseView courseView = new CourseView();
        boolean consoleFlag = true;
        while(consoleFlag) {
            System.out.println("Choose what objects to work with:");
            System.out.println("1. Users");
            System.out.println("2. Courses");
            System.out.println("3. Users(db)");
            System.out.println("4. Courses(db)");
            System.out.println("0. Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    userView.showInterface(false);
                    break;
                case "2":
                    courseView.showInterface(false);
                    break;
                case "3":
                    userView.showInterface(true);
                    break;
                case "4":
                    courseView.showInterface(true);
                    break;
                default:
                    consoleFlag = false;
                    break;
            }
        }
    }
}
