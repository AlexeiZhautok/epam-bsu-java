package com.example.Epam_Lab5.contoller;

import com.example.Epam_Lab5.dao.CourseDao;
import com.example.Epam_Lab5.model.Course;
import com.example.Epam_Lab5.service.CourseService;
import com.example.Epam_Lab5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    UserService userService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String main(Map<String,Object> model){
        Iterable<Course> courses =  courseService.findAll();
        model.put("courses",  courses);

        return "course";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = false, defaultValue = "") String name,
                         Model model) {
        courseService.delete(name);
        Iterable<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "course";
    }

    @PostMapping("/addCourse")
    public String add(@RequestParam String name,
                      @RequestParam String organization,
                      Map<String, Object> model
    )
    {

        model.put("message", courseService.add(name,organization));
        Iterable<Course> courses = courseService.findAll();
        model.put("courses", courses);

        return "course";
    }

    @GetMapping("/addUserOnCourse")
    public String addUserOnCourse(@RequestParam String name,
                                  @RequestParam String login,
                                  Map<String, Object> model
    ){
        model.put("message", courseService.addUserOnCourse(name,login) );
        Iterable<Course> courses = courseService.findAll();
        model.put("courses",courses);
        return "course";
    }

    @GetMapping("/removeUserFromCourse")
   public String removeUserFromACourse(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false) Long id,
                                        Map<String, Object> model) {
        model.put("message", courseService.removeUserFromACourse(name,id));
        Iterable<Course> courses = courseService.findAll();
        model.put("courses",courses);
        return "course";
    }

    @GetMapping("/subscribe")
    public String subscribeOnCourse(@RequestParam Long courseId, Map<String, Object> model){
        model.put("message",courseService.subscribeOnCourse(courseId));
        Iterable<Course> courses = courseService.findAll();
        model.put("courses",courses);
        return "course";
    }

    @GetMapping("/unsubscribe")
    public String unsubscribeOfCourse(@RequestParam Long courseId, Map<String, Object> model){
        model.put("message", courseService.unsubscribeOfCourse(courseId));
        Iterable<Course> courses = courseService.findAll();
        model.put("courses",courses);
        return "course";
    }
}
