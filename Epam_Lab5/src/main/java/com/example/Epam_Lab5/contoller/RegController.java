package com.example.Epam_Lab5.contoller;

import com.example.Epam_Lab5.dao.UserDao;
import com.example.Epam_Lab5.model.User;
import com.example.Epam_Lab5.model.Role;
import com.example.Epam_Lab5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String register(User user,
                           @RequestParam(required = false, defaultValue = "") String passwordSecond,
                           Model model){
        if(user.getPassword().equals(passwordSecond)) {
            User userFromDb = userService.findByUsername(user.getUsername());
            if (userFromDb != null) {
                model.addAttribute("message", "User already Exist");
                return "registration";
            }
            user.setActive(true);
            user.setRoles(Collections.singletonList(Role.USER));
            userService.save(user);
            return "redirect:/login";
        }else {
            model.addAttribute("message", "Passwords don't match");
            return "registration";
        }
    }
}
