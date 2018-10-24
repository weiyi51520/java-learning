package com.wey.juc_1.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午3:33
 */
public class ThreadState {

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Waiting(), "WaitingThread").start();
        Thread.sleep(1000);
        new Thread(new TimeWaiting (), "TimeWaitingThread").start();
//// 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
//        new Thread(new Blocked(), "BlockedThread-1").start();
//        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                        System.out.println("1111111");
                    } catch (InterruptedException e) {
                        System.out.println("等待超时");
                    }
                }
        }
    }

    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            synchronized (Waiting.class){

                try {
                    Thread.sleep(1000);
                    Waiting.class.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                    while (true){

                    }
            }
        }
    }

    static final void seconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
