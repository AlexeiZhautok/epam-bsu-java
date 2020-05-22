package com.example.Epam_Lab5.dao;

import com.example.Epam_Lab5.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Long> {
    Course findByName(String name);
    Course findCourseById(long id);
}
