package com.example.Epam_Lab5.dao;

import com.example.Epam_Lab5.model.CourseParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseParticipantsDao extends JpaRepository<CourseParticipants, Long> {
    List<CourseParticipants> findByUserId(long id);
    CourseParticipants findByUserIdAndAndCourseId(long uid, long cid);
}
