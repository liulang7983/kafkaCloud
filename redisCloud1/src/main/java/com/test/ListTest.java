package com.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author ming.li
 * @Date 2024/3/20 15:40
 * @Version 1.0
 */
public class ListTest {
    private static Jedis jedis= null;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        // timeout，这里既是连接超时又是读写超时，从Jedis 2.8开始有区分connectionTimeout和soTimeout的构造函数
        jedis = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000, null).getResource();
    }
    //从头插入
    @Test
    public void lpush(){
        Long lpush = jedis.lpush("list", "张三","李四");
        System.out.println(lpush);
    }
    //尾部插入
    @Test
    public void rpush(){
        Long rpush = jedis.rpush("list", "王五","赵六");
        System.out.println(rpush);
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
