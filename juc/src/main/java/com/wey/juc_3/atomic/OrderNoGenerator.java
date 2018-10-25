package com.wey.juc_3.atomic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yale.Wei
 * @date 2018/10/25 17:52
 * TODO jvm雪花算法
 */
public class OrderNoGenerator {
    static AtomicInteger count= new AtomicInteger(0);

    String getOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd:HHmmss");
        return sdf.format(new Date())+count.incrementAndGet();
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        final OrderNoGenerator generator = new OrderNoGenerator();
        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                System.out.println(generator.getOrderNo());
            });
        }
        latch.countDown();
        pool.shutdown();
    }

}
