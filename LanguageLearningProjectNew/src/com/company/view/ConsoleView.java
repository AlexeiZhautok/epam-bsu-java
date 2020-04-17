package com.company.view;

import com.company.service.CourseService;
import com.company.service.ServiceUtility;
import com.company.service.UserService;

import java.util.Scanner;

public class ConsoleView {
    UserService userService = ServiceUtility.userService;
    CourseService courseService = ServiceUtility.courseService;
    Scanner scanner = ViewUtility.scanner;

    public void showInterface() {
        boolean consoleFlag = true;
        while(consoleFlag) {
            System.out.println("Choose what objects to work with:");
            System.out.println("1. Users");
            System.out.println("2. Courses");
            System.out.println("0. Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    UserView userView = new UserView();
                    userView.showInterface();
                    break;
                case "2":
                    CourseView courseView = new CourseView();
                    courseView.showInterface();
                    break;
                default:
                    consoleFlag = false;
                    break;
            }
        }
    }
}
