package com.executorTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ming.li
 * @date 2023/2/24 9:21
 */
public class newCachedThreadPoolTest {
    public static void main(String[] args) {
        //核心池和最大线程池都为Integer.MAX_VALUE，队列长度为Integer.MAX_VALUE，容易cpu，且GC时间长收集不到垃圾
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 50000; i++) {
            threadPool.submit(new Test());
        }
        threadPool.shutdown();
    }
}
