package com.controller;

import com.config.LiMingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author ming.li
 * @date 2023/2/16 19:17
 */
@RestController
public class TestController {
    @Value("${tom.port:90}")
    private String port;

    @Autowired
    private LiMingConfig liMingConfig;

    @Value("${sss}")
    private String sss;

    @RequestMapping("/hello")
    public String hello(){
        return port;
    }

    @RequestMapping("/hello1")
    public String hello1(){
        return sss;
    }


    @RequestMapping("/hello2")
    public String hello2(){
        return liMingConfig.getName()+":"+liMingConfig.getAge()+":"+liMingConfig.getSex();
    }

    @RequestMapping("/hello3")
    @ResponseBody
    public String hello3(@PathVariable("id") String id){
        return id;
    }

    @PostMapping("hello4/{id}")
    @ResponseBody
    public String hello4(@PathVariable("id")String id){
        return"usershow:id="+id;
    }
}
