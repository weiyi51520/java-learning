package com.wey.juc_3.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Yale.Wei
 * @date 2018/10/25 18:05
 */
public class AtomicBooleanExample {
    AtomicBoolean aBoolean = new AtomicBoolean(false);

    void shutDown(){
        aBoolean.compareAndSet(false,true);
    }

    void doWork(){
        while (!aBoolean.get()){
        }
        System.out.println("你能读到我吗");
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicBooleanExample example = new AtomicBooleanExample();
        new Thread(() -> {
            example.doWork();
        }).start();
        Thread.sleep(1000);

        new Thread(() -> {
           example.shutDown();
        }).start();

    }
}
