package com.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2024/3/20 15:40
 * @Version 1.0
 */
public class HashTest {
    private static Jedis jedis= null;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        // timeout，这里既是连接超时又是读写超时，从Jedis 2.8开始有区分connectionTimeout和soTimeout的构造函数
        jedis = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000, null).getResource();
    }
    //单个插入
    @Test
    public void hset(){
        Long hash = jedis.hset("hash", "张三", "李四");
        System.out.println(hash);
    }
    //多个插入
    @Test
    public void hmset(){
        Map map = new HashMap<>();
        map.put("3","张三");
        map.put("4","李四");
        String hash = jedis.hmset("hash", map);
        System.out.println(hash);
    }
    //取出头部的并删除
    @Test
    public void lpop(){
        String list = jedis.lpop("list");
        System.out.println(list);
    }
    //取出尾部的并删除
    @Test
    public void rpop(){
        String list = jedis.rpop("list");
        System.out.println(list);
    }
}
