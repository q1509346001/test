package com.no1.cz.service.impl;

import com.alipay.api.domain.OrderItem;
import com.no1.cz.mapper.GoodsMapper;
import com.no1.cz.pojo.Goods;
import com.no1.cz.pojo.MyPay;
import com.no1.cz.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Goods> findAll() {
        try {
            return this.goodsMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Goods findById(Integer goodsId) {
        try {
            return this.goodsMapper.selectByPrimaryKey(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void add(Goods goods) {
        try {
            this.goodsMapper.insertSelective(goods);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateGoodsByImg(Goods goodsImg) {
        try {

            Example example = new Example(Goods.class);

            example.createCriteria().andEqualTo("goodsId",goodsImg.getGoodsId());
            this.goodsMapper.updateByExampleSelective(goodsImg,example);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateGoodsById(Goods goods) {
        try {
            Example example = new Example(Goods.class);

            example.createCriteria().andEqualTo("goodsId",goods.getGoodsId());
            this.goodsMapper.updateByExampleSelective(goods,example);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //通过餐品名称查询所有订单
    @Override
    public List<Goods> findAllName(MyPay myPay) {
        try {
            Example example = new Example(MyPay.class);
            example.createCriteria().andEqualTo("payName",myPay.getPayName()).andLike("name", "%d%");
            return goodsMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void modify(Goods goods) {
        try {
            this.goodsMapper.updateByPrimaryKeySelective(goods);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void deleteById(Integer goodsId) {
        try {
            this.goodsMapper.deleteByPrimaryKey(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> findByHotGoods() {
           return goodsMapper.HotGoods();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<Goods> findByName(String goodsName){

        try {
            Goods goods = new Goods();
            goods.setGoodsName(goodsName);
            List<Goods> select = goodsMapper.select(goods);
            return select;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
