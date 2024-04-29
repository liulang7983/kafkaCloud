package com.kafka;

import com.bean.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ming.li
 * @Date 2024/4/29 10:33
 * @Version 1.0
 */
@RestController
@RequestMapping("topic3AndTopic4")
public class Topic3AndTopic4Controller {
    private final static String TOPIC_NAME3 = "topic3";
    private final static String TOPIC_NAME4 = "topic4";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @RequestMapping("/sned")
    public Msg test2() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send3 = kafkaTemplate.send(TOPIC_NAME3,  "key"+i, "this is a msg"+TOPIC_NAME3);
            ListenableFuture<SendResult<String, String>> send4 = kafkaTemplate.send(TOPIC_NAME4,  "key"+i, "this is a msg"+TOPIC_NAME4);
        }
     return new Msg("000000","成功");
    }
}
