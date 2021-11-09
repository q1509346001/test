package com.no1.cz.mapper;


import com.no1.cz.pojo.MyPay;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PayMapper extends Mapper<MyPay> {
}
