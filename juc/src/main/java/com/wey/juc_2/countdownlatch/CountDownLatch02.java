package com.wey.juc_2.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午6:05
 */
public class CountDownLatch02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countLatch = new CountDownLatch(1);
        CallableImpl callable = new CallableImpl(countLatch);
        FutureTask<String> task = new FutureTask<>(callable);

        new Thread(task).start();
        if (!task.isDone()){
            try {
                System.out.println("I am waiting for you>>>>>>>");
                countLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(task.get());

    }
}
