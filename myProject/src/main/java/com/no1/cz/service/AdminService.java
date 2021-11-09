package com.no1.cz.service;

import com.no1.cz.pojo.Admin;
import com.no1.cz.pojo.User;

import java.util.List;

public interface AdminService {
    //List<Admin> findAll();

    Admin findByName(String username, String password);

    int updateAdminById(Admin admin);
}
