package com.wey.juc_2.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yale.Wei
 * @date 2018/10/19 下午4:35
 */
public class ReentrantLock_2 {
    private static final ReentrantLock reentrantLock=new ReentrantLock();
    static int i=0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock_2 reentrantLock2 = new ReentrantLock_2();
        Thread thread = new Thread(() -> {
            reentrantLock2.add();
        });
        thread.start();

        Thread.sleep(1000);//主要目的是让两个线程把事情干完
        Thread thread2 = new Thread(() -> {
            reentrantLock2.add();
        });
        thread2.start();
        Thread.sleep(1000);//主要目的是让两个线程把事情干完
        thread2.interrupt();//增加这段代码================
        System.out.println(i);

    }

    /***
     * 强调try fianlly规范
     */
    public  void add(){
        try {
            // reentrantLock.reentrantLock();
            try {
                reentrantLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (;;) {
                i++;
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
