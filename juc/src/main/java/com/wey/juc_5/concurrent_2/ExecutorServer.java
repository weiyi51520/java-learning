package com.wey.juc_5.concurrent_2;


import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author Yale.Wei
 * @date 2018/10/28 13:03
 */
public interface ExecutorServer extends Executor {
    void shutDown();

    <T> Future<T> submit(Runnable runnable);

    <T> Future<T> submit(Callable callable);
}
