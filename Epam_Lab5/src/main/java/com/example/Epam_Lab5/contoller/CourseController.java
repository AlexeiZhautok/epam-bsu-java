package com.example.Epam_Lab5.contoller;

import com.example.Epam_Lab5.dao.CourseDao;
import com.example.Epam_Lab5.dao.CourseParticipantsDao;
import com.example.Epam_Lab5.dao.UserDao;
import com.example.Epam_Lab5.model.Course;
import com.example.Epam_Lab5.model.CourseParticipants;
import com.example.Epam_Lab5.model.User;
import com.example.Epam_Lab5.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseParticipantsDao courseParticipantsDao;

    @GetMapping
    public String main(Map<String,Object> model){
        Iterable<Course> courses =  courseDao.findAll();
        Iterable<CourseParticipants> courseParticipants = courseParticipantsDao.findAll();
        model.put("courseParticipants", courseParticipants);
        model.put("courses",  courses);

        return "course";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = false, defaultValue = "") String name,
                         Model model) {
        if(name != null && !name.isEmpty()) {
            Course course = courseDao.findByName(name);
            if(course != null) {
                courseDao.delete(course);
            }
        }
        Iterable<Course> courses = courseDao.findAll();
        Iterable<CourseParticipants> courseParticipants = courseParticipantsDao.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("courseParticipants", courseParticipants);
        return "course";
    }

    @PostMapping("/addCourse")
    public String add(@RequestParam String name,
                      @RequestParam String organization,
                      Map<String, Object> model
    )
    {

        if(name!=null && organization !=null && !name.isEmpty() && !organization.isEmpty()) {
            Course check = courseDao.findByName(name);
            if(check == null) {
                Course course = new Course();
                course.setName(name);
                course.setOrganization(organization);
                courseDao.save(course);
            }
        }
        Iterable<Course> courses = courseDao.findAll();
        Iterable<CourseParticipants> courseParticipants = courseParticipantsDao.findAll();
        model.put("courses", courses);
        model.put("courseParticipants", courseParticipants);

        return "course";
    }

    @GetMapping("/addUserOnCourse")
    public String addUserOnCourse(@RequestParam String name,
                                  @RequestParam String login,
                                  Map<String, Object> model
    ){
        Course courseToUpdate = courseDao.findByName(name);
        if(courseToUpdate != null) {
            if(login != null && !login.isEmpty() && name != null && !name.isEmpty()) {
                User user = userDao.findByLogin(login);
                if(user != null && courseParticipantsDao.findByUserIdAndAndCourseId(user.getId(), courseToUpdate.getId()) == null) {
                    CourseParticipants courseParticipant = new CourseParticipants();
                    courseParticipant.setCourseId(courseToUpdate.getId());
                    courseParticipant.setUserId(user.getId());
                    courseParticipantsDao.save(courseParticipant);
                }
            }
        }

        Iterable <CourseParticipants> courseParticipants =  courseParticipantsDao.findAll();
        Iterable<Course> courses = courseDao.findAll();
        model.put("courses",courses);
        model.put("courseParticipants", courseParticipants);
        return "course";
    }

    @GetMapping("/removeUserFromCourse")
    public String removeUserFromACourse(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false) Long id,
                                        Map<String, Object> model) {
        if(name != null && !name.isEmpty() && id != null) {
            if(userDao.findUserById(id) != null && courseDao.findByName(name) != null) {
                Course tempCourse = courseDao.findByName(name);
                CourseParticipants temp = courseParticipantsDao.findByUserIdAndAndCourseId(id, tempCourse.getId());
                if(temp != null) {
                    courseParticipantsDao.delete(temp);
                }
            }
        }
        Iterable <CourseParticipants> courseParticipants =  courseParticipantsDao.findAll();
        Iterable<Course> courses = courseDao.findAll();
        model.put("courses",courses);
        model.put("courseParticipants", courseParticipants);
        return "course";
    }
}