package com.wey.juc_3.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Yale.Wei
 * @date 2018/10/25 16:09
 */
public class AtomicStampedABAExample {
    static AtomicStampedReference reference = new AtomicStampedReference(100, 0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reference.compareAndSet(100, 101, reference.getStamp(), reference.getStamp() + 1);
            reference.compareAndSet(101, 100, reference.getStamp(), reference.getStamp() + 1);
        });

        Thread t2 = new Thread(() -> {
            int stamp = reference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = reference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(b);
        });

        t1.start();
        t2.start();
    }
}
