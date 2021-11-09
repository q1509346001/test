package com.no1.cz.mapper;

import com.no1.cz.pojo.Goods;
import com.no1.cz.pojo.MyPay;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface GoodsMapper extends Mapper<Goods> {
    @Select("select count(1) goodsNum, gid as goodsId, pay_name goodsName FROM pay GROUP BY gid, pay_name ORDER BY goodsNum desc LIMIT 10")
    public List<Goods> HotGoods();
}
