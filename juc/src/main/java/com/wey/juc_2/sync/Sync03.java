package com.wey.juc_2.sync;

import com.wey.annotation.ThreadNoSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/24 17:42
 */
@ThreadNoSafe
public class Sync03 implements Runnable{
    static int i;

    @Override
    public void run() {
        synchronized (this){ //实例级别锁
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sync03 sync1 = new Sync03();
        Sync03 sync2 = new Sync03();

        Thread t1 = new Thread(sync1);
        Thread t2 = new Thread(sync2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }
}
