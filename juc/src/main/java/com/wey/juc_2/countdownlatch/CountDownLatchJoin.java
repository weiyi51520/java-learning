package com.wey.juc_2.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午6:08
 */
public class CountDownLatchJoin {
    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                System.out.println("I am working");
                Thread.sleep(2000);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        latch.await();
        System.out.println("Waiting for you finish.");
    }

}
