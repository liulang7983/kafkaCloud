package com.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class Topic2Consumer {
    private final static String TOPIC_NAME = "topic2";
    //配置多个消费组，那么此时test1的同一个消息两个消费组都可以消费到
    @KafkaListener(topics = TOPIC_NAME,groupId = "topic2Group")
    public void listentopic2Group(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println("第一个消费者");
        System.out.println("进入zhugeGroup");
        String value = record.value();
        System.out.println(value);
        System.out.println();
        //手动提交offset
        ack.acknowledge();
    }


    @KafkaListener(topics = TOPIC_NAME,groupId = "topic2Group")
    public void listentopic2Group1(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println("第二个消费者");
        System.out.println("进入topic2Group");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = record.value();
        System.out.println(value);
        System.out.println();
        ack.acknowledge();
    }

    @KafkaListener(topics = TOPIC_NAME,groupId = "topic2Group")
    public void listentopic2Group2(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println("第三个消费者");
        System.out.println("进入topic2Group");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = record.value();
        System.out.println(value);
        System.out.println();
        ack.acknowledge();
    }

}
