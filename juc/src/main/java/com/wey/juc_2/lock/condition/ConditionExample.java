package com.wey.juc_2.lock.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sun.jvm.hotspot.runtime.PerfMemory.start;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午6:27
 */
public class ConditionExample {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await(){
        try {
            lock.lock();
            System.out.println( Thread.currentThread().getName() +" awaitUninterruptibly ... ");
            condition.awaitUninterruptibly();
            System.out.println( Thread.currentThread().getName() +" awake ... ");
            System.out.println( Thread.currentThread().isInterrupted());
        }finally {
            System.out.println(Thread.currentThread().getName() +" unlock ");
            lock.unlock();
        }
    }

    public void signal(){

        try {
            System.out.println(Thread.currentThread().getName() +" lock ");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get up ");
            condition.signal();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() +" unlock ");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ConditionExample example = new ConditionExample();

        Thread pig = new Thread(new Runnable() {
            @Override
            public void run() {
                example.await();
            }
        }, "pig > ");

        pig.start();
        Thread.sleep(100);

        Thread hypnotist = new Thread(new Runnable() {
            @Override
            public void run() {
                pig.interrupt();
                System.out.println("interrupt pig!");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                example.signal();
            }
        }, "hypnotist > ");
        hypnotist.start();

    }
}
