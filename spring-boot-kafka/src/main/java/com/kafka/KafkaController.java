package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final static String TOPIC_NAME = "test0";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        System.out.println("写入");
       kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a send");
    }

    @RequestMapping("/send1")
    public void send1() throws  Exception{
        System.out.println("写入");
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a send1");
        //同步会阻塞直到拿到返回
        String s = send.get().toString();
        System.out.println(s);
    }

    @RequestMapping("/send2")
    public void send2() throws  Exception{
        System.out.println("写入");
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a send2");
        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("异常");
            }
            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.println("成功："+stringStringSendResult.toString());
            }
        });
    }

    @RequestMapping("/topic0")
    public void topic0() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("topic0",  "key", "this is a topic0");
        }
    }

    @RequestMapping("/topic1")
    public void topic1() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("topic1",  "key"+i, "this is a topic1");
        }
    }

    @RequestMapping("/test")
    public void test() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("test",  "key"+i, "this is a test");
        }
    }
    @RequestMapping("/test1")
    public void test1() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("test1",  "key"+i, "this is a test1");
        }
    }

    @RequestMapping("/test2")
    public void test2() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("test2",  "key"+i, "this is a test2");
        }
    }
    @RequestMapping("/test3")
    public void test3() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("test3",  "key"+i, "this is a test3");
        }
    }

    @RequestMapping("/test4")
    public void test4() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("test4",  "key"+i, "this is a test4");
        }
    }

    @RequestMapping("/topic3")
    public void topic3() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("topic3",  "key"+i, "this is a topic3");
        }
    }

    @RequestMapping("/topic4")
    public void topic4() throws  Exception{
        System.out.println("写入");
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("topic4",  "key"+i, "this is a topic4");
        }
    }

}
