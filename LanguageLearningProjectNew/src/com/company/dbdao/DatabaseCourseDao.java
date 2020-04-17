package com.company.dbdao;

import com.company.model.Course;
import com.company.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseCourseDao extends DatabaseDao<Course> {
    {
        setTable("Courses");
    }

    public Course parseModel(ResultSet response) {
        try {
            long id = response.getLong("course_id");
            String name = response.getString("course_name").trim();
            String organization = response.getString("course_organization").trim();
            return new Course(id, name, organization);
        } catch (SQLException e) {
            log.fatal(e);
        }
        return null;
    }

    public String makeUpdateByIDQuery(Course course) {
        String query = "UPDATE COURSES ";
        query += "SET COURSE_NAME = " + "'" + course.getName() + "'" + ",";
        query += "COURSE_ORGANIZATION = " + "'" + course.getOrganization() + "'" + " ";
        query += "WHERE COURSE_ID = " + course.getId();
        return query;
    }

    public String makeInsertQuery(Course course) {
        String query = "INSERT INTO COURSES ";
        query += "(COURSE_ID, COURSE_NAME, COURSE_ORGANIZATION)";
        query += "VALUES ";
        query += "(";
        query += course.getId() + ", ";
        query += "'" + course.getName() + "'" + ", ";
        query += "'" + course.getOrganization() + "'";
        query += ")";
        return query;
    }

    public String makeDeleteByIDQuery(long id) {
        String query = "DELETE FROM COURSES WHERE COURSE_ID = " + id;
        return query;
    }

    public String makeGetByIDQuery(long id) {
        return "SELECT * FROM COURSES WHERE COURSE_ID = " + id;
    }

    public String makeGetLastIDQuery() {
        String query = "SELECT MAX(COURSE_ID) FROM COURSES";
        return query;
    }
}
