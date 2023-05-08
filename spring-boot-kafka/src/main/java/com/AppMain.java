package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ming.li
 * @date 2023/2/16 19:30
 */
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AppMain.class);
        Map<String, Object> map = new HashMap<>();
        map.put("sss","aaa");
        application.setDefaultProperties(map);
        application.run(args);
    }
}
