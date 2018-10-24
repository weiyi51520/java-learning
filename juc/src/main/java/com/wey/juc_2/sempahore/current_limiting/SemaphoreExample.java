package com.wey.juc_2.sempahore.current_limiting;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * @author Yale.Wei
 * @date 2018/10/24 15:01
 */
public class SemaphoreExample {
    static final Semaphore SEMAPHORE = new Semaphore(10);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        IntStream.range(1,20).forEach(i -> {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    handler(i);
                }
            });
        });
        threadPool.shutdown();
    }

    static void handler(int i){
        try {
            if (SEMAPHORE.tryAcquire()){
                System.out.println(i + " no limit, execute do.");
                Thread.sleep(new Random().nextInt(200));
            }else {
                System.out.println(i + " in limit.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            SEMAPHORE.release();
        }
    }
}
