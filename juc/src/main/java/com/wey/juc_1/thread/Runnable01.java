package com.wey.juc_1.thread;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午3:16
 */
public class Runnable01 implements Runnable {
    public static final Object lock = new Object();

    @Override
    public void run() {
        synchronized (lock) {

            System.out.println(Thread.currentThread().getName() + "活好人帅");
            while (true) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable01 runnable01 = new Runnable01();
        Runnable01 runnable02 = new Runnable01();
        Runnable01 runnable03 = new Runnable01();
        Thread thread1 = new Thread(new Runnable01(), "MyThread_01");
        Thread thread2 = new Thread(new Runnable01(), "MyThread_02");
        Thread thread3 = new Thread(new Runnable01(), "MyThread_03");
        thread1.start();
        thread2.start();
        thread3.start();


    }

}
