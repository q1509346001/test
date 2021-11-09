package com.no1.cz.service;

import com.no1.cz.pojo.Goods;
import com.no1.cz.pojo.MyPay;

import java.util.List;

public interface GoodsService {
    List<Goods> findAll();
    //通过餐品名模糊查询
    List<Goods> findAllName(MyPay myPay);

    Goods findById(Integer goodsId);

    void add(Goods goods);
    //修改图片
    void updateGoodsByImg(Goods goodsImg);
    //修改除了图片的所有字段
    void updateGoodsById(Goods goods);
    //修改状态
    void modify(Goods goods);

    List<Goods> findByName(String goods);

    void deleteById(Integer goodsId);

    List<Goods> findByHotGoods();
}
