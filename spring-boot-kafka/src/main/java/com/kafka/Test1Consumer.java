package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class Test1Consumer {
    private final static String TOPIC_NAME = "test1";
    //配置多个消费组，那么此时test1的同一个消息两个消费组都可以消费到
    @KafkaListener(topics = TOPIC_NAME,groupId = "zhugeGroup")
    public void listenZhugeGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println();
        System.out.println("进入zhugeGroup");
        String value = record.value();
        System.out.println(value);
        System.out.println();
        //手动提交offset
        ack.acknowledge();
    }


    @KafkaListener(topics = TOPIC_NAME,groupId = "tulingGroup")
    public void listenTulingGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("进入tulingGroup");
        String value = record.value();
        System.out.println(value);
        System.out.println();
        ack.acknowledge();
    }

}
