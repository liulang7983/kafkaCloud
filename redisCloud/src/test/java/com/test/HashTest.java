package com.test;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * @author ming.li
 * @date 2023/2/23 16:39
 */
public class HashTest {
    private static JedisPoolConfig config = new JedisPoolConfig();
    private static JedisCluster jedisCluster = null;

    private static Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();


    private void init(){
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 8001));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 8002));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 8003));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 8004));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 8005));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 8006));
        try {
            //connectionTimeout：指的是连接一个url的连接等待时间
            //soTimeout：指的是连接上一个url，获取response的返回等待时间
            jedisCluster = new JedisCluster(jedisClusterNode, 6000, 5000, 10, "zhuge", config);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //添加,相当于新建了一个叫h1的HashMap，往里面存key-value
    @Test
    public void hset(){
        init();
        Long hset = jedisCluster.hset("h1", "hlm2", "liming3");
        System.out.println(hset);
    }
    //根据HashMap的名字和key找到value
    @Test
    public void hget(){
        init();
        String hget = jedisCluster.hget("h1", "hlm2");
        System.out.println(hget);
    }
    //批量插入
    @Test
    public void hmset(){
        init();
        Map<String,String> map=new HashMap<>();
        map.put("hlm3","liming3");
        map.put("hlm4","liming4");
        String hmset = jedisCluster.hmset("h1", map);
        System.out.println(hmset);
    }
    //批量获取多个key的value值
    @Test
    public void hmget(){
        init();
        List<String> hmget = jedisCluster.hmget("h1", "hlm3", "hlm4");
        System.out.println(hmget);

    }
    //获取整个HashMap的key-value
    @Test
    public void hgetAll(){
        init();
        Map<String, String> h1 = jedisCluster.hgetAll("h1");
        System.out.println(h1);

    }

    //获取整个HashMap的key-value
    @Test
    public void hlen(){
        init();
        Long h1 = jedisCluster.hlen("h1");
        System.out.println(h1);

    }


}
