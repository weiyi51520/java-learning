package com.wey.juc_2.sync;


import com.wey.annotation.ThreadSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/24 17:34
 */


@ThreadSafe
public class Sync02 implements Runnable{
    static int i = 0;

    @Override
    public synchronized void run() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sync02 sync02 = new Sync02();
        Thread t1 = new Thread(sync02);
        Thread t2 = new Thread(sync02);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
