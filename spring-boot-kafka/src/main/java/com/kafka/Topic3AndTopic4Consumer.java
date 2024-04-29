package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class Topic3AndTopic4Consumer {
    //配置多个消费组，那么此时test1的同一个消息两个消费组都可以消费到
    @KafkaListener(topics ="#{'${kafka.topic.topic3AndTopic4}'.split(',')}",groupId = "topic3AndTopic4Group")
    public void listentopic2Group(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println("第一个消费者");
        System.out.println("进入topic3AndTopic4Group");
        String value = record.value();
        System.out.println(value);
        System.out.println();
        //手动提交offset
        ack.acknowledge();
    }

}
