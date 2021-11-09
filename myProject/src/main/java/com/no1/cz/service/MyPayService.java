package com.no1.cz.service;

import com.no1.cz.pojo.MyPay;

import java.util.List;

public interface MyPayService {
    List<MyPay> findAll();

    MyPay findById(int id);

    List<MyPay> findByName(String username);

    List<MyPay> findBygoodsName(String goodsName);

    void insertOne(MyPay myPay);
}
