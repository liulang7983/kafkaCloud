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
public class OrderConsumer {
    //通过打印线程名可得是单线程，区别于rocketMQ的默认多线程，天然实现了顺序消费
    @KafkaListener(topics = "orderTopic",groupId = "orderTopicGroup")
    public void orderTopic(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(Thread.currentThread().getName()+" orderTopicGroup:"+record.key()+"："+value);
        ack.acknowledge();
    }

}
