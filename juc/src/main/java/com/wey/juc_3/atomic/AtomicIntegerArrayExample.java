package com.wey.juc_3.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Yale.Wei
 * @date 2018/10/25 16:19
 */
public class AtomicIntegerArrayExample {
    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                array.getAndIncrement(i%array.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new AddThread());
        }
        for (Thread thread: threads) {
            thread.start();
        }
        for (Thread thread: threads) {
            thread.join();
        }

        System.out.println(array);
    }
}
