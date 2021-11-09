package com.no1.cz.service.impl;

import com.no1.cz.mapper.UserMapper;
import com.no1.cz.pojo.User;
import com.no1.cz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<User> findAll() {
        try {
            return userMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User findById(Long id) {
        try {
            return userMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void insertOne(User user) {
        try {
            userMapper.insertSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    @Transactional
    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateUserById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public User findByUser(String username, String password) {
        User user = null;
        Example example = new Example(User.class);
        example.and().andEqualTo("userName",username);
        List<User> userList = userMapper.selectByExample(example);
        if(userList.size() > 0)
            user =  userList.get(0);
        else
            throw new RuntimeException("用户名或密码错误！");
        if(user != null){
            if(password.equals(user.getPassword())){
                return user;
            }else {
                throw new RuntimeException("用户名或密码错误！");
            }
        }else{
            throw new RuntimeException("用户名或密码错误！");
        }
    }

    @Override
    public String findByName(String name) {
        return name;
    }


}
