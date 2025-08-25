package com.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author ming.li
 * @date 2023/11/15 19:08
 */
@Component
public class PartitionsConsumer {
    //哪怕有三个分片，只有一个消费者，也只消费我指定的分片
    @KafkaListener(topicPartitions = {@TopicPartition(topic ="partitionsTopic",partitions = "0")},
            groupId = "partitionsTopicGroup")
    public void partitionsTopic(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(Thread.currentThread().getName()+" partitionsTopicGroup:"+record.key()+"："+value);
        ack.acknowledge();
    }
    //此时两个主题都是3个分片，但是我只消费partitionsTopic2的分片0，和partitionsTopic3的分片0和1
    @KafkaListener(topicPartitions = {@TopicPartition(topic ="partitionsTopic2",partitions = "0"),
                                      @TopicPartition(topic ="partitionsTopic3",partitions = {"0","1"}) },
            groupId = "partitionsTopicAnd3Group")
    public void partitionsTopic2(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(Thread.currentThread().getName()+" partitionsTopicGroup:"+record.key()+"："+value);
        ack.acknowledge();
    }

}
