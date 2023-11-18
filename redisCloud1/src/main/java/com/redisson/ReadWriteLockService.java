package com.redisson;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author ming.li
 * @date 2023/11/18 17:34
 */
@Service
public class ReadWriteLockService {

    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(2,2,2000L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //此时线程A里面加读锁后休眠1秒在解锁对后面的读锁无影响
    public void readAndRead() throws ExecutionException, InterruptedException {
        RReadWriteLock andRead = redisson.getReadWriteLock("readAndRead");
        executor.submit(()->{
            RLock rLock = andRead.readLock();
            rLock.lock();
            System.out.println("线程A加入读锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rLock.unlock();
        });

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                RLock rLock = andRead.readLock();
                rLock.lock();
                System.out.println("线程A加入读锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rLock.unlock();
                return "完成";
            }
        });
        String s = future.get();
        System.out.println(s);
    }

    //此时线程A里面加锁后休眠1秒在解锁对后面的写锁是有影响的
    public void readAndWrite() throws ExecutionException, InterruptedException {
        RReadWriteLock andRead = redisson.getReadWriteLock("readAndRead");
        executor.submit(()->{
            RLock rLock = andRead.readLock();
            rLock.lock();
            System.out.println("线程A加入读锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rLock.unlock();
        });

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                RLock rLock = andRead.writeLock();
                rLock.lock();
                System.out.println("线程A加入写锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rLock.unlock();
                return "完成";
            }
        });
        String s = future.get();
        System.out.println(s);
    }
}
