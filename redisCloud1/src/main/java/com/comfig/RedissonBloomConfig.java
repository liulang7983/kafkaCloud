package com.comfig;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ming.li
 * @Date 2024/3/19 17:19
 * @Version 1.0
 */
@Configuration
public class RedissonBloomConfig {
    @Autowired
    private RedissonClient redissonClient;
    @Bean
    public RBloomFilter<String> createRBloomFilter(){
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("nameList");
        //初始化布隆过滤器：预计元素为100000000L,误差率为3%,根据这两个参数会计算出底层的bit数组大小
        bloomFilter.tryInit(100000L,0.03);
        return bloomFilter;
    }
}
