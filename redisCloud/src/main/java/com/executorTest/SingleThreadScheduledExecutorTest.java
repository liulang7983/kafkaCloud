package com.executorTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/2/24 10:11
 */
public class SingleThreadScheduledExecutorTest {
    public static void main(String[] args) {
        //核心池为3，最大线程池为Integer.MAX_VALUE，队列长度为Integer.MAX_VALUE
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 50000; i++) {
            threadPool.schedule(new Test(),2, TimeUnit.SECONDS);
        }
        threadPool.shutdown();
    }
}
