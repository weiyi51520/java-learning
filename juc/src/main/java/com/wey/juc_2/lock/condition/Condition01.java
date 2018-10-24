package com.wey.juc_2.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午6:15
 */
public class Condition01 {
    static Lock lock = new ReentrantLock();
    static Condition full = lock.newCondition();
    static Condition empty = lock.newCondition();

    private static int i=0;

    public static void main(String[] args) {
        new Thread(()->{
            try {
                Condition01.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Condition01.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static void producer() throws InterruptedException {
        lock.lock();
        try {
            for(;;i++){
                System.out.println("addInterrupt i: "+i);
                if (i>=30){
                    System.out.println("waiting...");
                    Thread.sleep(1000);
                    full.await();
                    empty.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    static void consumer() throws InterruptedException {
        lock.lock();
        try {
            for (;;i--){
                System.out.println("minus i: "+ i);
                if (i<=0){
                    System.out.println("awaiting ...... ");
                    Thread.sleep(1000);
                    full.signal();
                    empty.await();
                }
            }
        } finally {
            lock.unlock();
        }

    }
}
