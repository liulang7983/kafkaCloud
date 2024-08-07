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
public class TestConsumer {
    //删除topic bin/kafka-topics.sh --delete --topic test2 --zookeeper 172.18.26.20:2181
    //新建两个主分片的的topic  bin/kafka-topics.sh --create --zookeeper 172.18.26.20:2181 --replication-factor 1 --partitions 2 --topic test2
    //分别在连个微服务消费同一个topic，且组相同，name一个消息只会背心消费一次
    @KafkaListener(topics = "test")
    public void test0(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(record.key()+"："+value);
        ack.acknowledge();
    }
}
