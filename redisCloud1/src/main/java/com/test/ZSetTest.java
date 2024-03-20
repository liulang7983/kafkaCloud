package com.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ming.li
 * @date 2023/2/23 15:46
 */
public class ZSetTest {
    private static Jedis jedis= null;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        // timeout，这里既是连接超时又是读写超时，从Jedis 2.8开始有区分connectionTimeout和soTimeout的构造函数
        jedis = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000, null).getResource();
    }
    //单个插入zset
    @Test
    public void zadd(){
        Long sadd = jedis.zadd("zset",2,"张三");
        System.out.println(sadd);
    }
    //多个插入zset
    @Test
    public void zadds(){
        Map<String, Double> map=new HashMap<>();
        map.put("李四",4d);
        map.put("王五",5d);
        Long sadd = jedis.zadd("zset",map);
        System.out.println(sadd);
    }
}
