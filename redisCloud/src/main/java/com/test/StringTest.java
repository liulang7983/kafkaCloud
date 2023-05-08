package com.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ming.li
 * @date 2023/2/23 15:46
 */
public class StringTest {

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

    public void delete(){
        Long liming = jedisCluster.del("liming");
        System.out.println(liming);
    }

}
