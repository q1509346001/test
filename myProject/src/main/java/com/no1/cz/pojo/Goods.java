package com.no1.cz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Table(name = "goods")//表的名字为goods
public class Goods {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer goodsId;
    private String goodsName;
    private Double goodsPrice;
    private Integer goodsNum;
    private String goodsImg;
    private Integer goodsType;
}
