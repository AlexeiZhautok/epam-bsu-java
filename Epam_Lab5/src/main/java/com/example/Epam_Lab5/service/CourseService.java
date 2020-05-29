package com.example.Epam_Lab5.service;

import com.example.Epam_Lab5.dao.CourseDao;
import com.example.Epam_Lab5.model.Course;
import com.example.Epam_Lab5.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CourseService {

    private CourseDao courseDao;

    @Autowired
    private UserService userService;

    @Autowired
    public CourseService(@Qualifier("CourseDao") CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Iterable<Course> findAll() {
        return courseDao.findAll();
    }

    public void delete(String name) {
        if(name != null && !name.isEmpty() && courseDao.findByName(name) != null) {
            Course course = courseDao.findByName(name);
            for (int i = 0; i < course.getUserSet().size(); i++){
                course.getUserSet().get(i).deleteCourse(course);
            }
            courseDao.delete(course);
        }
    }

    public String add(String name, String organization) {
        if(name!=null && organization !=null && !name.isEmpty() && !organization.isEmpty()) {
            Course check = courseDao.findByName(name);
            if(check == null) {
                Course course = new Course();
                course.setName(name);
                course.setOrganization(organization);
                courseDao.save(course);
                return "";
            }else {
                return "Already Exists";
            }
        }else {
            return "Incorrect data";
        }
    }

    public String addUserOnCourse(String name, String login) {
        if(name != null && !name.isEmpty() && login != null && !login.isEmpty()  && courseDao.findByName(name)!=null && userService.findByUsername(login) !=null ) {
            Course courseToUpdate = courseDao.findByName(name);
            User user = userService.findByUsername(login);
            int flag = 0;
            for(int i = 0; i < courseToUpdate.getUserSet().size(); i ++){
                if (courseToUpdate.getUserSet().get(i).getId().equals(user.getId())) {
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) {
                courseToUpdate.addUser(user);
                user.addCourse(courseToUpdate);
                courseDao.save(courseToUpdate);
                userService.save(user);
                return "";
            }else {
                return "User already added";
            }
        } else {
            return "Data is incorrect";
        }
    }

    public String removeUserFromACourse(String name, Long id) {
        if(name != null && !name.isEmpty() && id != null) {
            if(userService.findUserById(id) != null && courseDao.findByName(name) != null) {
                User user = userService.findUserById(id);
                Course course = courseDao.findByName(name);
                user.deleteCourse(course);
                course.deleteUser(user);
                userService.save(user);
                courseDao.save(course);
                return  "";
            } else {
                return  "Data is incorrect or user already removed";
            }
        } else {
            return "Data is incorrect";
        }
    }

    public String subscribeOnCourse(Long courseId) {
        Course courseToUpdate = courseDao.findCourseById(courseId);
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        int flag = 0;
        for (int i = 0 ; i < courseToUpdate.getUserSet().size(); i++){
            if (courseToUpdate.getUserSet().get(i).getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            courseToUpdate.addUser(user);
            user.addCourse(courseToUpdate);
            courseDao.save(courseToUpdate);
            userService.save(user);
            return  "You have enrolled";
        }else {
            return  "";
        }
    }

    public String unsubscribeOfCourse(Long courseId) {
        Course courseToUpdate = courseDao.findCourseById(courseId);
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        int flag = 0;
        for (int i = 0 ; i < courseToUpdate.getUserSet().size(); i++){
            if (courseToUpdate.getUserSet().get(i).getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                flag = 1;
                break;
            }
        }
        if(flag == 1) {
            user.deleteCourse(courseToUpdate);
            courseToUpdate.deleteUser(user);
            userService.save(user);
            courseDao.save(courseToUpdate);
            return  "You have unrolled";
        }else {
            return  "";
        }
    }
}
