package com.executorTest;

/**
 * @author ming.li
 * @date 2023/2/24 9:05
 */
public class Test implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
