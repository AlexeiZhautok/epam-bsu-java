package com.example.Epam_Lab5.service;

import com.example.Epam_Lab5.dao.UserDao;
import com.example.Epam_Lab5.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findByUsername(s);
    }

    public Iterable<User> findAll() {
        return userDao.findAll();
    }

    public User findByUsername(String login) {
        return userDao.findByUsername(login);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    public void deleteUserByUserName(String login) {
        if(login != null && !login.isEmpty() && this.findByUsername(login) != null){
            User user = this.findByUsername(login);
            for (int i = 0 ; i < user.getCourseSet().size(); i++){
                user.getCourseSet().get(i).deleteUser(user);
            }
            this.delete(user);
        }
    }

    public Object find(String login) {
        if(login != null && !login.isEmpty() && this.findByUsername(login)!=null){
            User user = this.findByUsername(login);
            List<User> userAsList = new ArrayList<>();
            userAsList.add(user);
            return userAsList;
        }else{
            Iterable<User> users = this.findAll();
            return users;
        }
    }
}
