package com.no1.cz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true
)
@Table(name = "tb_user")
public class User {
//    id	bigint
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
//    user_name	varchar
    private String userName;
//    password	varchar
    private String password;
//    name	varchar
    private String name;
//    phone	varchar
    private String phone;
//    sex	tinyint
    private String sex;
//    note	varchar
/*    private String role;
//    created	datetime
    private Date created;
//    updated	datetime
    private Date updated;
//    dbname	varchar
    private String dbname;*/
}
