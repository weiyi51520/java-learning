package com.wey.juc_1.pool;

import java.util.concurrent.*;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午5:01
 */
public class ThreadPoolTest implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(4));

        new Thread(new MonitorUtil(poolExecutor, 10)).start();
        for (int i = 0; i < 8; i++) {
            Thread.sleep(500);
            synchronized (MonitorUtil.class) {
                poolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " is running ....");
                        try {
                            Thread.sleep(10000);
                            System.out.println(Thread.currentThread().getName() + " is done");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                System.out.println("Monitor notify...");
                MonitorUtil.class.notify();
            }
        }

    }

    @Override
    public void run() {
    }
}
