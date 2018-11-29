package com.wey.juc_4.schedule;

import java.util.Date;

/**
 * @author Yale.Wei
 * @date 2018/10/26 16:27
 */
public class Monitor implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start. Time = " + new Date());
        System.out.println("java.version: "+System.getProperty("java.version"));
        System.out.println("java.class.path: "+System.getProperty("java.class.path"));
        System.out.println("user.dir: "+System.getProperty("user.dir"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" end. Time = "+new Date());
        System.out.println("===================================");

        throw new RuntimeException();
    }
}
