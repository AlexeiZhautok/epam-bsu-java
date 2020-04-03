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
            int option = scanner.nextInt();
            if(option == 0) {
                break;
            }
            switch (option) {
                case 1:
                    while(true) {
                        System.out.println("User options: ");
                        System.out.println("1. Get all users");
                        System.out.println("2. Delete all users");
                        System.out.println("3. Create new user");
                        System.out.println("4. Get user by ID");
                        System.out.println("5. Update user by ID");
                        System.out.println("6. Delete user by ID");
                        System.out.println("0. Exit");
                        option = scanner.nextInt();
                        if(option == 0) {
                            break;
                        }
                        switch (option) {
                            case 1:
                                List<User> users = userService.getAll();
                                for(User iter : users) {
                                    System.out.println(iter.toString());
                                }
                                break;
                            case 2:
                                userService.deleteAll();
                                break;
                            case 3:
                                System.out.println("Enter new user's information");
                                System.out.println("ID: ");
                                long id = scanner.nextLong();
                                scanner.nextLine();
                                System.out.println("Login: ");
                                String login = scanner.nextLine();
                                System.out.println("Password: ");
                                String password = scanner.nextLine();
                                System.out.println("Email: ");
                                String email = scanner.nextLine();
                                System.out.println("Role: ");
                                UserRole role = UserRole.valueOf(scanner.nextLine());
                                userService.createUser(id, login, password, email, role);
                                break;
                            case 4:
                                System.out.println("Enter ID: ");
                                id = scanner.nextLong();
                                User tempUser = userService.getByID(id);
                                if(tempUser != null) {
                                    System.out.println(tempUser.toString());
                                } else {
                                    System.out.println("User not found");
                                }
                                break;
                            case 5:
                                System.out.println("Enter needed user's ID: ");
                                long idToUpdate = scanner.nextLong();
                                System.out.println("Enter new user information");
                                System.out.println("ID: ");
                                id = scanner.nextLong();
                                scanner.nextLine();
                                System.out.println("Login: ");
                                login = scanner.nextLine();
                                System.out.println("Password: ");
                                password = scanner.nextLine();
                                System.out.println("Email: ");
                                email = scanner.nextLine();
                                System.out.println("Role: ");
                                role = UserRole.valueOf(scanner.nextLine());
                                userService.updateByID(idToUpdate, id, login, password, email, role);
                                break;
                            case 6:
                                System.out.println("Enter needed user's ID: ");
                                id = scanner.nextLong();
                                userService.deleteByID(id);
                                break;
                        }
                    }
                    break;
            }
        }
    }
}
