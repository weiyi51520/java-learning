package com.wey.juc_2.sync;

import com.wey.annotation.ThreadNoSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/24 17:15
 */
@ThreadNoSafe
public class Sync01 implements Runnable{

    static int i;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sync01 sync01 = new Sync01();

        Thread t1 = new Thread(sync01);
        Thread t2 = new Thread(sync01);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }
}
