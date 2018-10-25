package com.wey.juc_3.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yale.Wei
 * @date 2018/10/25 18:11
 */
public class Sync02 implements Runnable{
    static AtomicInteger i = new AtomicInteger();

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            i.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sync02 sync02 = new Sync02();
        Thread thread = new Thread(sync02);
        Thread thread2 = new Thread(sync02);
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(i.get());
    }
}
