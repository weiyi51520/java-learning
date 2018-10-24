package com.wey.juc_1.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午4:23
 */
public class SchedulePoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("悟空是只猴子");
//            }
//        });

//        executorService.schedule(() -> {
//            System.out.println("5");
//        },5, TimeUnit.SECONDS);

        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("Yale is a monkey!");
                throw new RuntimeException();
            }
        },0,5,TimeUnit.SECONDS);
    }
}
