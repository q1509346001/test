package com.no1.cz.service.impl;

import com.no1.cz.mapper.AdminMapper;
import com.no1.cz.pojo.Admin;

import com.no1.cz.pojo.User;
import com.no1.cz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    public AdminMapper adminMapper;

    @Override
    @Transactional
    //登录
    public Admin findByName(String username, String password) {
        Admin admin = null;
        Example example = new Example(Admin.class);
        example.and().andEqualTo("userName",username);
        List<Admin> userList = adminMapper.selectByExample(example);
        if(userList.size() > 0)
            admin =  userList.get(0);
        else
            throw new RuntimeException("用户名或密码错误！");
        if(admin != null){
            if(password.equals(admin.getPassword())){
                return admin;
            }else {
                throw new RuntimeException("用户名或密码错误！");
            }
        }else{
            throw new RuntimeException("用户名或密码错误！");
        }
    }


    @Override
    @Transactional
    public int updateAdminById(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }
}
