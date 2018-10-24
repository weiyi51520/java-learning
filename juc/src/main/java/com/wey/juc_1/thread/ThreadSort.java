package com.wey.juc_1.thread;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午3:30
 */
public class ThreadSort {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("thread1");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("thread2");
        });

        Thread t3 = new Thread(() -> {
            System.out.println("thread3");
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }

}
