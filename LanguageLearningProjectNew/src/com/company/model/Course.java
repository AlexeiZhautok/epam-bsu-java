package com.company.model;

import com.company.dao.CourseDao;
import com.company.dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    long id;
    private String name;
    private String organization;
    public ArrayList<User> users = new ArrayList<>();


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }

    public ArrayList<User> getUsers() { return users; }

    public Course(long id, String name, String organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                Objects.equals(name, course.name) &&
                Objects.equals(organization, course.organization) &&
                Objects.equals(users, course.users);
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Organization: " + organization + "\n" +
                "Users: " + users.toString() + "\n";
    }

    public String toStringFileFormat() {
        String delim = CourseDao.delim;
        return id + delim +
                name + delim +
                organization + delim +
                users;
    }
}
