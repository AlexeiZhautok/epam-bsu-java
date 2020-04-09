package com.company.view;

import com.company.model.User;
import com.company.model.UserRole;
import com.company.service.UserService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public void showInterface() {
        while(true) {
            System.out.println("Choose what objects to work with:");
            System.out.println("1. Users");
            System.out.println("0. Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    UserInterface userInterface = new UserInterface();
                    userInterface.showInterface(scanner, userService);
                default:
                    break;
            }
        }
    }
}
