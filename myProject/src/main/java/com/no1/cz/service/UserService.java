package com.no1.cz.service;

import com.no1.cz.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUser(String name,String password);

    String findByName(String name);

    void insertOne(User user);

    int deleteUserById(Integer id);

    int updateUserById(User user);
}
