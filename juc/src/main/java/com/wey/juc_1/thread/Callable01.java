package com.wey.juc_1.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午12:34
 */
public class Callable01 implements Callable {


    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        System.out.println("calling...");
        return "活好人帅";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask task = new FutureTask(new Callable01());
        new Thread(task).start();
//        System.out.println(task.get()); 调用 get 时会阻塞主线程
        System.out.println("11111");


    }
}
