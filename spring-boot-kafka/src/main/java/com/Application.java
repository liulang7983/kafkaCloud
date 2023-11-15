package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        Map<String, Object> map = new HashMap<>();
        map.put("sss","aaa");
        application.setDefaultProperties(map);
        application.run(args);
    }

}
