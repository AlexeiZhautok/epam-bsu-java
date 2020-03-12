package com.company.service;

import com.company.DAO.AdminDao;
import com.company.model.Admin;

import java.util.ArrayList;

public class AdminService {

    private AdminDao adminDao = new AdminDao();

    public void DeleteAll()
    {
        adminDao.deleteAll();
    }

    public ArrayList<Admin> ReadAll(){
        return adminDao.readAll();
    }


}
