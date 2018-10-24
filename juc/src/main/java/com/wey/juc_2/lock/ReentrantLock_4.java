package com.wey.juc_2.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author Yale.Wei
 * @date 2018/10/19 下午4:51
 */
public class ReentrantLock_4 implements Runnable{
    static final ReentrantLock LOCK = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (LOCK.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
                System.out.println("get it");
            }else {
                System.out.println("get failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (LOCK.isHeldByCurrentThread()){
                LOCK.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock_4 reentrantLock4 = new ReentrantLock_4();
        IntStream.range(0,2).forEach(i -> new Thread(reentrantLock4).start());
    }
}
