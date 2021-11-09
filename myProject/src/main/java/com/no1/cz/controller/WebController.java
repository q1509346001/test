package com.no1.cz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
@Slf4j
public class WebController {

    @GetMapping("/demo1")
    @ResponseBody
    public String demo1(@RequestParam("id") Integer id, @RequestParam("name") String name){
        log.info("接收到前台传来的值是id--> " + id + ";name--> " + name);
        return "id: " + id + "; name: " + name;
    }

    @RequestMapping("/demo2/{id}")
    @ResponseBody
    public String demo2(@PathVariable("id") String id){
        return "succ";
    }
}
