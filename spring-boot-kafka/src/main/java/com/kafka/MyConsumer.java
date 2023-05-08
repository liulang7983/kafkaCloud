package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    /**
     * @KafkaListener(groupId = "testGroup", topicPartitions = {
     *             @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
     *             @TopicPartition(topic = "topic2", partitions = "0",
     *                     partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
     *     },concurrency = "6")
     *  //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
     * @param record
     */
    @KafkaListener(topics = "test0",groupId = "zhugeGroup")
    public void listenZhugeGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println();
        System.out.println("进入zhugeGroup");
        String value = record.value();
        System.out.println("zhugeGroup的value:"+value);
        System.out.println("zhugeGroup的value:"+record);
        System.out.println();
        //手动提交offset
        ack.acknowledge();
    }

    //配置多个消费组，那么此时test0的同一个消息两个消费组都可以消费到
    @KafkaListener(topics = "test0",groupId = "tulingGroup")
    public void listenTulingGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println();
        System.out.println("进入tulingGroup");
        String value = record.value();
        System.out.println("tulingGroup的value:"+value);
        System.out.println("tulingGroup的record:"+record);
        System.out.println();
        ack.acknowledge();
    }
    //消费组一个消费者好几个，同一个消息只有一个消费者可以消费到
    @KafkaListener(topics = "topic0",groupId = "limingGroup")
    public void listenLimingGroup1(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println();
        System.out.println("进入limingGroup1");
        String value = record.value();
        System.out.println("limingGroup1的value:"+value);
        System.out.println("limingGroup1的record:"+record);
        System.out.println();
        ack.acknowledge();
    }

    @KafkaListener(topics = "topic0",groupId = "limingGroup")
    public void listenLimingGroup2(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println();
        System.out.println("进入limingGroup2");
        String value = record.value();
        System.out.println("limingGroup2的value:"+value);
        System.out.println("limingGroup2的record:"+record);
        System.out.println();
        ack.acknowledge();
    }
}
