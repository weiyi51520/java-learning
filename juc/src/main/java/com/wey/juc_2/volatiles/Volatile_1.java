package com.wey.juc_2.volatiles;

/**
 * @author Yale.Wei
 * @date 2018/10/25 15:25
 */
public class Volatile_1 {
    boolean stop = false;

    public void shutDown() {
        stop = true;
        System.out.println("shutDown stop="+stop);
    }

    public void work() {
        while (!stop) { }
        System.out.println("你能读到我吗");
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile_1 volatile1 = new Volatile_1();

        new Thread(() -> {
            volatile1.work();
        }).start(); //开始工作 读取 stop 为false
        Thread.sleep(1000);

        new Thread(() -> {
            volatile1.shutDown();
        }).start(); //修改stop为 true,但由于缓存, 工作线程依旧读到的值为false


    }
}
