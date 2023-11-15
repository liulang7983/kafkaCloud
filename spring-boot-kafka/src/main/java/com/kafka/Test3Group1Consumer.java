package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author ming.li
 * @date 2023/11/15 19:08
 */
@Component
public class Test3Group1Consumer {

    //分别在两个微服务消费同一个topic，且组不相同，一个消息会被不同消费组各消费一次
    @KafkaListener(topics = "test3",groupId = "group1")
    public void test0(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(record.key()+"："+value);
        ack.acknowledge();
    }
}
