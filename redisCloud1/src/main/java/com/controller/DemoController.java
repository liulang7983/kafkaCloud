package com.controller;

import com.bean.BatchStatus;
import com.util.DemoUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ming.li
 * @Date 2024/3/20 16:08
 * @Version 1.0
 */
@RestController
public class DemoController {

    @RequestMapping("add")
    public String add(@RequestBody BatchStatus batchStatus){
        DemoUtil.BATCH_STATUS_MAP.put(batchStatus.getBatchId(),batchStatus.getStatus());
        return "成功";
    }
    @RequestMapping("get")
    public Integer get(@RequestBody BatchStatus batchStatus){
        Integer integer = DemoUtil.BATCH_STATUS_MAP.get(batchStatus.getBatchId());
        return integer;
    }

    @RequestMapping("delete")
    public Integer delete(@RequestBody BatchStatus batchStatus){
        Integer integer = DemoUtil.BATCH_STATUS_MAP.remove(batchStatus.getBatchId());
        return integer;
    }
}
