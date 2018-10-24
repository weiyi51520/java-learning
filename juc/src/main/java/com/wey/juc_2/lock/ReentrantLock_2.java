package com.wey.juc_2.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yale.Wei
 * @date 2018/10/19 下午4:35
 */
public class ReentrantLock_2 {
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock_2 reentrantLock2 = new ReentrantLock_2();

        Thread t1 = new Thread(() -> {
            reentrantLock2.addInterrupt();
        }, "t1");

        Thread t2 = new Thread(() -> {
            reentrantLock2.addInterrupt();
        }, "t2");
        t1.start();
        t2.start();

        Thread.sleep(1000);

        System.out.println("main do interrupt!");
        t1.interrupt();
        t2.interrupt();
        System.out.println(i);
    }

    public void add() {
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+ " lock ");
            for (; ; ) {
                i++;
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + " unlock ");
            reentrantLock.unlock();
        }
    }

    /***
     * 强调try fianlly规范
     */
    public void addInterrupt() {
        try {
            reentrantLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+ " lockInterruptibly ");
            for (; ; ) {
                i++;
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+ " interrupted when state :" + Thread.currentThread().getState());
        } finally {
            System.out.println(Thread.currentThread().getName() + " unlock ");
            reentrantLock.unlock();
        }
    }
}
