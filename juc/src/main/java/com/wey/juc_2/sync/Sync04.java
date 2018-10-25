package com.wey.juc_2.sync;

import com.wey.juc_2.annotation.ThreadSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/24 17:42
 */
@ThreadSafe
public class Sync04 implements Runnable{
    static int i;

    @Override
    public void run() {
        synchronized (this.getClass()){ //class级别锁
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sync04 sync1 = new Sync04();
        Sync04 sync2 = new Sync04();

        Thread t1 = new Thread(sync1);
        Thread t2 = new Thread(sync2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }
}
