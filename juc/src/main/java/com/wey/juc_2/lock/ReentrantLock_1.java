package com.wey.juc_2.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yale.Wei
 * @date 2018/10/19 下午4:24
 */
public class ReentrantLock_1 {
    static final ReentrantLock LOCK = new ReentrantLock();
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock_1 reentrantLock1 = new ReentrantLock_1();

        Thread t1 = new Thread(() -> {
            add();
        });

        Thread t2 = new Thread(() -> {
            add();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }

    static void add(){
        try {
            LOCK.lock();
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        } finally {
            LOCK.unlock();
        }

    }
}
