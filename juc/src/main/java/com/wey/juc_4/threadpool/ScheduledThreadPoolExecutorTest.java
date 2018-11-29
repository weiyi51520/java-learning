package com.wey.juc_4.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/10/26 18:44
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        pool.scheduleAtFixedRate(() -> System.out.println("3"),0,3, TimeUnit.SECONDS);
        pool.scheduleWithFixedDelay(() -> System.out.println("3"),0,3, TimeUnit.SECONDS);
    }
}
