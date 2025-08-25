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
    //哪怕模拟失败也还是原来的线程，区别于线程池失败后的生成一个新的线程
    //一个类存在两个@KafkaListener，通过线程名打印可得不同的监听是不同的线程
    @KafkaListener(topics = "orderFalseTopic",groupId = "orderFalseTopicGroup")
    public void orderFalseTopic(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(Thread.currentThread().getName()+" orderFalseTopicGroup:"+record.key()+"："+value);
        if (value.contains("1")){
            throw new RuntimeException("存在1抛异常");
        }
        ack.acknowledge();
    }
}
