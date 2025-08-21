package com.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author ming.li
 * @date 2023/11/15 19:08
 */
@Component
public class Topic5Consumer {
    //分别在两个微服务消费同一个topic，不指定组，此时会给他们设置同一个默认的组,
    // 三个分片，有一个消费者消费了两个分片而且是消费完一个再小诶另一个不是交替消费
    @KafkaListener(topics = "topic5")
    public void test0(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(record.key()+"："+value);
        ack.acknowledge();
    }
}
