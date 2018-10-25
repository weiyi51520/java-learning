package com.wey.juc_3.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yale.Wei
 * @date 2018/10/25 15:53
 */
public class AtomIntegerABAExample {
    static AtomicInteger atomicInteger = new AtomicInteger(100);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            atomicInteger.compareAndSet(100,101);
            atomicInteger.compareAndSet(101,100);
        });

        Thread t2= new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = atomicInteger.compareAndSet(100, 101);
            System.out.println(b);
            System.out.println(atomicInteger.get());
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
