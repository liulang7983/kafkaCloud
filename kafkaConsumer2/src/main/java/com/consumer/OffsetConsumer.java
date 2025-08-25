package com.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author ming.li
 * @date 2023/11/15 19:08
 */
@Component
public class OffsetConsumer {
    //指定消费某个分片，且从指定的offest开始消费(不管是否是有历史偏移量，只要我的偏移量没有超出最大偏移量，重启微服务就从指定的开始)
    @KafkaListener(topicPartitions = {@TopicPartition(topic = "offsetTopic",partitionOffsets =@PartitionOffset(partition = "0",initialOffset = "100"))},
            groupId = "offsetTopicGroup")
    public void offsetTopic(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(Thread.currentThread().getName()+" offsetTopicGroup:"+record.key()+"："+value);
        ack.acknowledge();
    }
    //指定消费某个分片，且从指定的offest开始消费(此时40000超出最大偏移量所以从0开始，重启后不管之前是否已经消费过这些消息)
    @KafkaListener(topicPartitions = {@TopicPartition(topic = "offsetTopic",partitionOffsets =@PartitionOffset(partition = "0",initialOffset = "40000"))},
            groupId = "offsetTopic2Group")
    public void offsetTopic2(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(Thread.currentThread().getName()+" offsetTopic2Group:"+record.key()+"："+value);
        ack.acknowledge();
    }
}
