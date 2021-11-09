package com.no1.cz.service.impl;

import com.no1.cz.mapper.PayMapper;
import com.no1.cz.pojo.MyPay;
import com.no1.cz.service.MyPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyPayServiceImpl implements MyPayService {
    @Autowired
    private PayMapper payMapper;

    @Override
    @Transactional
    public List<MyPay> findAll() {
        try {
            return payMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public MyPay findById(int id) {
        try {
            return payMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public List<MyPay> findByName(String username) {
        MyPay myPay = new MyPay();
        myPay.setUserName(username);
        List<MyPay> select = payMapper.select(myPay);
        return select;
        //return payMapper.selectByPrimaryKey(username);
    }
    @Override
    @Transactional
    public List<MyPay> findBygoodsName(String goodsName) {
        MyPay myPay = new MyPay();
        myPay.setPayName(goodsName);
        List<MyPay> select = payMapper.select(myPay);
        return select;
        //return payMapper.selectByPrimaryKey(username);
    }
    @Override
    @Transactional
    public void insertOne(MyPay myPay) {
        try {
            payMapper.insertSelective(myPay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
