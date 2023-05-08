package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/3/3 14:54
 */
@RestController
@RequestMapping("/string")
public class StringController {
    @Autowired
    public StringRedisTemplate template;

    @RequestMapping("/set")
    public String set(@RequestBody Map<String,String> map){
        template.opsForValue().set(map.get("key"),map.get("value"));
        return "ok";
    }

    @RequestMapping("/setnx")
    public String setnx(@RequestBody Map<String,String> map){
        Boolean aBoolean = template.opsForValue().setIfAbsent(map.get("key"), map.get("value"));
        return ""+aBoolean;
    }

    @RequestMapping("/setex")
    public String setex(@RequestBody Map<String,String> map){
        template.opsForValue().set(map.get("key"), map.get("value"),20, TimeUnit.SECONDS);
        return "setex";
    }
    @RequestMapping("/strlen")
    public String strlen(@RequestBody Map<String,String> map){
        Long key1 = template.opsForValue().size("key1");
        return ""+key1;
    }
}
