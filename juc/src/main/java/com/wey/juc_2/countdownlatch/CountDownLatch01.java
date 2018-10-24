package com.wey.juc_2.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午5:40
 */
public class CountDownLatch01 {

    private final static int count = 100;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await(120, TimeUnit.MILLISECONDS);
        System.out.println("end");
        exec.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(50);
        System.out.println(threadNum);
        Thread.sleep(50);
    }

}
