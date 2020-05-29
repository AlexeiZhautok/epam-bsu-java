package com.example.Epam_Lab5.contoller;

import com.example.Epam_Lab5.dao.UserDao;
import com.example.Epam_Lab5.model.Role;
import com.example.Epam_Lab5.model.User;
import com.example.Epam_Lab5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String main(Map<String,Object> model){
       Iterable<User> users =  userService.findAll();
       model.put("users", users);
       return "user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = false, defaultValue ="") String login,
                         Model model){
        userService.deleteUserByUserName(login);
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("login", login);
        return "user";
    }

    @GetMapping("/find")
    public String find(@RequestParam(required = false, defaultValue ="") String login,
                       Model model){
        model.addAttribute("users", userService.find(login));
        model.addAttribute("login", login);
        return "user";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "editor";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ){
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userService.save(user);
        return "redirect:/user";
    }

}
