package com.company.dao;

import com.company.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CourseDao {
    public static String delim = "-";
    private String DB_Destination = "database/courses/courseDatabase.txt";

    public static Logger log = LogManager.getLogger();

    private long getLastID() {
        long toReturn = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_Destination))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                toReturn = parseCourseInfo(tempLine).getId();
                reader.readLine();
            }
        } catch (IOException e) {
            log.fatal(e);
        }
        return toReturn;
    }

    public Course getByID(long inputID) {
        List<Course> courseList = getAll();
        for(Course courseIter : courseList) {
            if(courseIter.getId() == inputID) {
                return courseIter;
            }
        }
        return null;
    }

    public void updateInfoByID(long inputID, String name, String organization) {
        List<Course> courseList = getAll();
        deleteAll();
        for(Course courseIter : courseList) {
            if(courseIter.getId() == inputID) {
                courseIter.setName(name);
                courseIter.setOrganization(organization);
            }
        recreateCourse(courseIter);
        }
    }

    public Course parseCourseInfo(String input) {
        StringTokenizer st = new StringTokenizer(input, delim);
        long ID = Long.parseLong(st.nextToken());
        String name = st.nextToken();
        String organization = st.nextToken();
        return new Course(ID, name, organization);
    }

    public ArrayList<Long> parseCourseUsers(String input) {
        ArrayList<Long> toReturn = new ArrayList<>();
        if(!input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input, delim);
            while(st.hasMoreTokens()) {
                toReturn.add(Long.parseLong(st.nextToken()));
            }
        }
        return toReturn;
    }

    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination))) {
            writer.write("");
        } catch (IOException ignored) {
        }
    }

    public void createNewCourse(String name, String organization) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination, true))) {
            long ID = getLastID() + 1;
            writer.write(new Course(ID, name, organization).toStringFileFormat());
            writer.newLine();
        } catch (IOException e) {
            log.fatal(e);
        }
    }

    public void recreateCourse(Course course) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination, true))) {
            course.setId(getLastID() + 1);
            writer.write(course.toStringFileFormat());
            writer.newLine();
        } catch (IOException e) {
            log.fatal(e);
        }
    }

    public void addUserByID(long courseID, long userID) {
        List<Course> courseList = getAll();
        deleteAll();
        for(Course courseIter : courseList) {
            if(courseIter.getId() == courseID) {
                courseIter.addUser(userID);
            }
            recreateCourse(courseIter);
        }
    }

    public List<Course> getAll() {
        List<Course> toReturn = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_Destination))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                Course temp = parseCourseInfo(tempLine);
                tempLine = reader.readLine();
                ArrayList<Long> users = parseCourseUsers(tempLine);
                temp.setUsers(users);
                toReturn.add(temp);
            }
        } catch (IOException e) {
            log.fatal(e);
        }
        return toReturn;
    }

    public Course getByName(String name) {
        List<Course> courseList = getAll();
        for(Course courseIter : courseList) {
            if(courseIter.getName().equals(name)) {
                return courseIter;
            }
        }
        return null;
    }
}
