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
public class BroadcastingConsumer {
    //同一个主题，两个组消费，每个组有两个组员，那么两个组都消费全部消息，每个组内按照一定规则分配这些消息
    //此时是存在三个分片，那么有一个组员消费了两个分片，大概是一个67，一个33
    @KafkaListener(topics = "broadcastingTopic",groupId = "broadcastingTopicGroup1")
    public void broadcastingTopic1(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println("broadcastingTopicGroup1:"+record.key()+"："+value);
        ack.acknowledge();
    }
    @KafkaListener(topics = "broadcastingTopic",groupId = "broadcastingTopicGroup2")
    public void broadcastingTopic2(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println("broadcastingTopicGroup2:"+record.key()+"："+value);
        ack.acknowledge();
    }
}
