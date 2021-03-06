package com.wey.juc_2.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yale.Wei
 * @date 2018/10/19 下午4:38
 */
public class ReentrantLock_3 {
    static final ReentrantLock LOCK = new ReentrantLock();
    static int i=0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock_3 reentrantLock3 = new ReentrantLock_3();

        new Thread(() -> {
           reentrantLock3.add();
        },"t1").start();

        new Thread(() -> {
            reentrantLock3.add();
        },"t2").start();


    }

    public void add() {
        if (LOCK.tryLock()) {
            try {
                for (; ; ) {
                    i++;
                }
            } finally {
                LOCK.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "没有获取到锁" );
        }
    }
}
