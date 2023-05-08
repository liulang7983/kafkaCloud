package com.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
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
        /*jedisClusterNode.add(new HostAndPort("127.0.0.1", 8007));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 8008));*/
        try {
            //connectionTimeout：指的是连接一个url的连接等待时间
            //soTimeout：指的是连接上一个url，获取response的返回等待时间
            jedisCluster = new JedisCluster(jedisClusterNode, 6000, 5000, 10, "zhuge", config);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //删除
    @Test
    public void delete(){
        init();
        Long liming = jedisCluster.del("liming");
        System.out.println(liming);
    }
    //添加
    @Test
    public void set(){
        init();
         jedisCluster.set("liming5","1");
        System.out.println(jedisCluster.get("liming"));
    }
    //截取，前后都包含
    @Test
    public void getrange(){
        init();
        String liming = jedisCluster.getrange("liming", 0, 3);
        System.out.println(liming);
    }

    //获取长度
    @Test
    public void strlen(){
        init();
        Long liming = jedisCluster.strlen("liming");
        System.out.println(liming);
    }
    //批量插入，集群会不可以，槽位不在一个主节点
    @Test
    public void mset(){
        init();
        String mset = jedisCluster.mset( "liming2", "liming22");
        System.out.println(mset);
    }
    //批量查询，集群会不可以，槽位不在一个主节点会报错
    @Test
    public void mget(){
        init();
        List<String> mget = jedisCluster.mget("liming1");
        System.out.println(mget);
    }
    //插入且设置超时时间
    @Test
    public void setex(){
        init();
        String mset = jedisCluster.setex( "liming3",3, "liming33");
        System.out.println(mset);
    }

    //字符拼接，返回总长度
    @Test
    public void append(){
        init();
        Long append = jedisCluster.append("liming1", "liming33");
        System.out.println(append);
    }
    //字段自增和自减1
    @Test
    public void incr(){
        init();
        Long liming5 = jedisCluster.incr("liming5");
        System.out.println(liming5);
    }
    @Test
    public void decr(){
        init();
        Long liming5 = jedisCluster.decr("liming5");
        System.out.println(liming5);
    }

    //字段自增和自减指定数量
    @Test
    public void incrBy(){
        init();
        Long liming5 = jedisCluster.incrBy("liming5",100);
        System.out.println(liming5);
    }
    @Test
    public void decrby(){
        init();
        Long liming5 = jedisCluster.decrBy("liming5",50);
        System.out.println(liming5);
    }
    //修改名字，集群可能会不可以，新旧名字槽位不在一个主节点会报错
    @Test
    public void rename(){
        init();
        String rename = jedisCluster.rename("liming5", "lm5");
        System.out.println(rename);
    }

    @Test
    public void setAll(){
        init();
        for (int i = 300; i <700 ; i++) {
            jedisCluster.set("s"+i,"ss"+i);
        }
    }
    @Test
    public void deleteAll(){
        init();
        for (int i = 0; i <700 ; i++) {
            jedisCluster.del("s"+i);
        }
    }

}
