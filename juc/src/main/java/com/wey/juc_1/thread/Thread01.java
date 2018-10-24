package com.wey.juc_1.thread;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午3:29
 */
public class Thread01 extends Thread{

    public static void main(String[] args) {
        Thread01 thread01 = new Thread01();
        thread01.start();
    }

    @Override
    public void run() {
        System.out.println("活好人帅");
    }
}
