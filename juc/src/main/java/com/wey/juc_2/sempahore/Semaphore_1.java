package com.wey.juc_2.sempahore;

import java.util.concurrent.Semaphore;

/**
 * @author Yale.Wei
 * @date 2018/10/19 下午5:38
 */
public class Semaphore_1 {
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    action(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }


    public static void action(int i) throws InterruptedException {
        semaphore.acquire();

        System.out.println(i + "kill the iphone x in JD");
        Thread.sleep(3000);
        System.out.println(i + "kill successful");

        semaphore.release();
    }
}
