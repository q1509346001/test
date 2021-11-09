package com.no1.cz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Table(name = "pay")//表的名0.
// 字为pay
public class MyPay {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String tradeNo;
    private String userName;
    private String amount;
    private String payName;
    private String payDesc;
    private String PayDate;
    private Integer gid;
}
