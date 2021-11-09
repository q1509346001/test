package com.no1.cz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;


@Controller
@RequestMapping("/")
@Slf4j
public class HelloController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() throws Exception {
//        log.info("hello 被访问了");
//        throw new Exception("这个就是测试用的异常");
        System.out.println("dataSource: " + dataSource);
        return "hello, spring boot!";
    }

    @GetMapping("/index")
    public String index(){
        log.info("进到首页....");
//        System.out.println(5/0);
        return "index";
    }

}
