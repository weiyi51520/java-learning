package com.wey.juc_2.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午6:27
 */
public class ConditionExample {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await(){
        try {
            System.out.println(Thread.currentThread().getName() +" lock ");
            lock.lock();
            System.out.println( Thread.currentThread().getName() + " sleep ... ");
            Thread.sleep(1000);
            condition.await();
            System.out.println( Thread.currentThread().getName() +" awake ... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
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

        new Thread(new Runnable() {
            @Override
            public void run() { example.await(); }
        },"pig > ").start();
        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() { example.signal(); }
        },"hypnotist > ").start();

    }
}
