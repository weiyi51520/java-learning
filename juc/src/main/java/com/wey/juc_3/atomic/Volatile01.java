package com.wey.juc_3.atomic;

/**
 * @author Yale.Wei
 * @date 2018/10/25 18:11
 */
public class Volatile01 {

    volatile boolean stop = false;


    public void shutDown() {
        stop = true;
    }

    public void doWork() {
        while (!stop) {
        }
        System.out.println("你能读到我吗...");
    }


    public static void main(String[] args) throws InterruptedException {
        Volatile01 volatile01 = new Volatile01();
        new Thread(() -> {
            volatile01.doWork();//先就开始工作，stop为false
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            volatile01.shutDown(); //调用shtdown方法stop为true 但是线程工作副本的问题
        }).start();

    }
}
