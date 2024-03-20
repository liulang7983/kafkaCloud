package com.redisson;

import com.alibaba.fastjson.JSONObject;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/11/18 17:01
 */
@RestController
public class RedissonController {

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("redisson")
    public JSONObject redisson() throws InterruptedException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        RLock redisson = this.redisson.getLock("redisson");
        redisson.lock();
        System.out.println();
        System.out.println("加锁");
        Thread.sleep(1000);
        redisson.unlock();
        System.out.println("解锁");
        return jsonObject;
    }
    //此时多线程来减，及其容易出错，可能两次的是从6->5
    @RequestMapping("set")
    public JSONObject set() throws InterruptedException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        Integer set = Integer.valueOf(stringRedisTemplate.opsForValue().get("set"));
        if (set>0){
            int i = set - 1;
            System.out.println(i);
            stringRedisTemplate.opsForValue().set("set",i+"");
        }
        return jsonObject;
    }
    //此时加锁后才去减不会出现错误
    @RequestMapping("redissonSub")
    public JSONObject redissonSub() throws InterruptedException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        RLock redisson = this.redisson.getLock("redissonSub");
        redisson.lock();
        Integer set = Integer.valueOf(stringRedisTemplate.opsForValue().get("set"));
        if (set>0){
            int i = set - 1;
            System.out.println(i);
            stringRedisTemplate.opsForValue().set("set",i+"");
        }
        redisson.unlock();
        return jsonObject;
    }
}
