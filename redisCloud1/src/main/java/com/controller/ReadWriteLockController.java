package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.service.ReadWriteLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author ming.li
 * @date 2023/11/18 17:30
 */
@RestController
@RequestMapping("readWriteLock")
public class ReadWriteLockController {
    @Autowired
    public ReadWriteLockService readWriteLockService;
    @RequestMapping("readAndRead")
    public JSONObject readAndRead() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            readWriteLockService.readAndRead();
        }
        long end = System.currentTimeMillis();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time",end-start);
        return jsonObject;
    }

    @RequestMapping("readAndWrite")
    public JSONObject readAndWrite() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            readWriteLockService.readAndWrite();
        }
        long end = System.currentTimeMillis();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time",end-start);
        return jsonObject;
    }
}
