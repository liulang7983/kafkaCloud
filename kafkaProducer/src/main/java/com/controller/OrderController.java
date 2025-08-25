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
@RequestMapping("order")
public class OrderController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("orderTopic",0, "order"+i, "orderTopic:"+i);
        }
    }
    @RequestMapping("/send1")
    public void send1() {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("orderFalseTopic",0, "order"+i, "orderTopic:"+i);
        }
    }
}
