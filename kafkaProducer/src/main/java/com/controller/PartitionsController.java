package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ming.li
 * @Date 2025/8/22 9:43
 * @Version 1.0
 */
@RestController
@RequestMapping("partitions")
public class PartitionsController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("partitionsTopic","partitionsTopic:"+i);
        }
    }
    @RequestMapping("/send2")
    public void send2() {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("partitionsTopic2","partitionsTopic2:"+i);
            kafkaTemplate.send("partitionsTopic3","partitionsTopic3:"+i);
        }
    }

}
