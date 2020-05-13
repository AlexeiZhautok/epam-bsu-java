package com.example.Epam_Lab5.contoller;

import com.example.Epam_Lab5.dao.CourseParticipantsDao;
import com.example.Epam_Lab5.dao.UserDao;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    CourseParticipantsDao courseParticipantsDao;

    @GetMapping
    public String main(Map<String,Object> model){
       Iterable<User> users =  userDao.findAll();
       model.put("users", users);
       return "user";
    }

    @PostMapping("/add")
    public String add(@RequestParam String login,
                      @RequestParam String password,
                      @RequestParam String email,
                      @RequestParam String role,
                      Map<String, Object> model
    )
    {

        if(login!=null && password !=null && email !=null && role != null && (role.equals("ADMIN") || role.equals("TEACHER") || role.equals("STUDENT") || role.equals("GUEST"))) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(UserRole.valueOf(role));
            userDao.save(user);
        }
        Iterable<User> users = userDao.findAll();
        model.put("users", users);
        return "user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = false, defaultValue ="") String login,
                         Model model){
        if(login != null && !login.isEmpty()){
            User user = userDao.findByLogin(login);
            if(user != null) {
                userDao.delete(user);
                List<CourseParticipants> list = courseParticipantsDao.findByUserId(user.getId());
                for(var i : list) {
                    courseParticipantsDao.delete(i);
                }
            }
        }
        Iterable<User> users = userDao.findAll();
        model.addAttribute("users", users);
        model.addAttribute("login", login);
        return "user";
    }

    @GetMapping("/find")
    public String find(@RequestParam(required = false, defaultValue ="") String login,
                       Model model){
        if(login != null && !login.isEmpty()){
            User user = userDao.findByLogin(login);
            List<User> userAsList = new ArrayList<>();
            if(user != null) {
                userAsList.add(user);
            }
            model.addAttribute("users", userAsList);
        }else{
            Iterable<User> users = userDao.findAll();
            model.addAttribute("users", users);
        }
        model.addAttribute("login", login);
        return "user";
    }


}
