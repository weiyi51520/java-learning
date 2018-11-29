package com.wey.juc_5.concurrent_2;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author Yale.Wei
 * @date 2018/10/28 13:02
 */
public abstract class AbstractExecutorService implements ExecutorServer{

    @Override
    public <T> Future<T> submit(Runnable runnable) {
        FutureTask task = new FutureTask(runnable,null);
        execute(task);
        return task;
    }

    @Override
    public <T> Future<T> submit(Callable callable) {
        FutureTask task = new FutureTask(callable);
        execute(task);
        return task;
    }
}
