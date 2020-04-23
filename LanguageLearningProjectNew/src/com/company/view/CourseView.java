package com.company.view;

import com.company.model.Course;
import com.company.service.CourseService;
import com.company.servicedb.CourseServiceDatabase;

import java.util.List;
import java.util.Scanner;

public class CourseView {
    public void showInterface(boolean db) {
        boolean courseFlag = true;
        Scanner scanner = new Scanner(System.in);
        //UserService userService = ServiceUtility.userService;
        CourseService courseService = new CourseService();
        CourseServiceDatabase courseServiceDatabase = new CourseServiceDatabase();

        while(courseFlag) {
            System.out.println("Course options: ");
            System.out.println("1. Get all courses");
            System.out.println("2. Delete all courses");
            System.out.println("3. Create a new course");
            System.out.println("4. Get course by ID");
            System.out.println("5. Update course info by ID");
            System.out.println("6. Delete course by ID");
            System.out.println("7. Add user to a course");
            System.out.println("8. Remove user from a course");
            System.out.println("0. Exit");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    List<Course> allCourses;
                    if(db) {
                        allCourses = courseServiceDatabase.getAll();
                    } else {
                        allCourses = courseService.getAll();
                    }
                    for(Course courseIter : allCourses) {
                        System.out.println(courseIter.toStringFileFormat());
                    }
                    break;
                case "2":
                    if(db) {
                        // Implement!!
                    } else {
                        courseService.deleteAll();
                    }
                    break;
                case "3":
                    System.out.println("Enter new course's information:");
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Organization: ");
                    String organization = scanner.nextLine();
                    if(db) {
                        courseServiceDatabase.createNewCourse(name, organization);
                    } else {
                        courseService.createNewCourse(name, organization);
                    }
                    break;
                case "4":
                    System.out.println("Enter ID: ");
                    String id = scanner.nextLine();
                    Course tempCourse;
                    if(db) {
                        tempCourse = courseServiceDatabase.getByID(id);
                    } else {
                        tempCourse = courseService.getByID(id);
                    }
                    if(tempCourse != null) {
                        System.out.println(tempCourse.toStringFileFormat());
                    } else {
                        System.out.println("Course not found");
                    }
                    break;
                case "5":
                    System.out.println("Enter needed course's ID: ");
                    id = scanner.nextLine();
                    System.out.println("Enter new course information");
                    System.out.println("New name: ");
                    name = scanner.nextLine();
                    System.out.println("New organization: ");
                    organization = scanner.nextLine();
                    if(db) {
                        courseServiceDatabase.updateInfoByID(id, name, organization);
                    } else {
                        courseService.updateInfoByID(id, name, organization);
                    }
                    break;
                case "6":
                    System.out.println("Enter needed course's ID: ");
                    id = scanner.nextLine();
                    if(db) {
                        courseServiceDatabase.deleteByID(id);
                    } else {
                        courseService.deleteByID(id);
                    }
                    break;
                case "7":
                    System.out.println("Enter needed course's ID: ");
                    String courseID = scanner.nextLine();
                    System.out.println("Enter needed user's ID: ");
                    String userID = scanner.nextLine();
                    if(db) {
                        courseServiceDatabase.addUserToCourse(courseID, userID);
                    } else {
                        courseService.addUserToCourse(courseID, userID);
                    }
                    break;
                case "8":
                    System.out.println("Enter needed course's ID: ");
                    courseID = scanner.nextLine();
                    System.out.println("Enter needed user's ID: ");
                    userID = scanner.nextLine();
                    if(db) {
                        courseServiceDatabase.removeUserByID(courseID, userID);
                    } else {
                        courseService.removeUserByID(courseID, userID);
                    }
                    break;
                default:
                    courseFlag = false;
                    break;
            }
        }
    }
}
