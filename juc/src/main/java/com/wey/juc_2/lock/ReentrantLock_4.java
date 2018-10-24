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
                System.out.println(Thread.currentThread().getName()+" get lock");
                Thread.sleep(6000);
            }else {
                System.out.println(Thread.currentThread().getName()+" get failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (LOCK.isHeldByCurrentThread()){

                System.out.println(Thread.currentThread().getName()+" unlock");
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock_4 reentrantLock4 = new ReentrantLock_4();
        IntStream.range(0,5).forEach(i -> new Thread(reentrantLock4).start());
    }
}
