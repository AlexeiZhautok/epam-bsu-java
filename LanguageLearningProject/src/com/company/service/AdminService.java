package com.company.service;

import com.company.DAO.AdminDao;
import com.company.model.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class AdminService {
    Logger log = LogManager.getLogger();
    private AdminDao adminDao = new AdminDao();

    public void DeleteAll() {
        adminDao.deleteAll();
        log.info("Deleted all admins");
    }

    public ArrayList<Admin> ReadAll() {
        var temp = adminDao.readAll();
        int size = temp.size();
        log.info("returned" + size + " admins");
        return temp;
    }


}