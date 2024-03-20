package com.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.junit.After;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ming.li
 * @date 2023/2/23 15:46
 */
public class StringTest {
    private static Jedis jedis= null;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        // timeout，这里既是连接超时又是读写超时，从Jedis 2.8开始有区分connectionTimeout和soTimeout的构造函数
        jedis = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000, null).getResource();
    }
    @Test
    public void set(){
        String set = jedis.set("张三", "张三");
        System.out.println(set);
    }
    @Test
    public void mset(){
        String set = jedis.mset("张三", "张三","李四","李四");
        System.out.println(set);
    }
    @Test
    public void get(){
        String str = jedis.get("张三");
        System.out.println(str);
    }
    @Test
    public void mget(){
        List<String> mget = jedis.mget("张三", "李四","ste");
        System.out.println(mget);
    }

    @Test
    public void del(){
        Long del = jedis.del("张三");
        System.out.println(del);
    }

}
