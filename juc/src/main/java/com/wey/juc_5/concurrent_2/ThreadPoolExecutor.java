package com.wey.juc_5.concurrent_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yale.Wei
 * @date 2018/10/28 13:07
 */
public class ThreadPoolExecutor extends AbstractExecutorService {
    private volatile int corePoolSize;
    private volatile int maximumPoolSize;
    private volatile long keepAliveTime;
    private volatile boolean allowCoreThreadTimeOut;
    private final AtomicInteger ctl = new AtomicInteger(0);
    private BlockingQueue<Runnable> workQueue;

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, boolean allowCoreThreadTimeOut, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        if (keepAliveTime > 0) {
            allowCoreThreadTimeOut = true;
        }
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        this.workQueue = workQueue;
    }

    public int getCorePoolSize() {
        return ctl.get();
    }

    @Override
    public void shutDown() {

    }

    @Override
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }
        int c = ctl.get();
        if (c < corePoolSize) {

        }
    }

    private void addWorker(Runnable task, Boolean core) {
        if (core) {
            ctl.incrementAndGet();
        }
        Worker worker = new Worker(task);
        worker.thread.start();
    }


    class Worker extends ReentrantLock implements Runnable {
        private Runnable firstTask;
        private Thread thread;

        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            thread = new Thread(this);
        }

        @Override
        public void run() {
            try {
                this.lock();
                Runnable task = this.firstTask;
                if (task != null || (task = getTask()) != null) {
                    task.run();
                }
            } finally {
                processWorkerExit(this);
                this.unlock();
            }
        }

        private void processWorkerExit(Worker worker) {addWorker(null,false);
        }

        public Runnable getTask() {
            try {
                if (workQueue.isEmpty()) {
                    return null;
                }
                Runnable runnable = allowCoreThreadTimeOut ? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : workQueue.take();
                if (runnable != null) {
                    return runnable;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class RejectExceptionHandler{
        public void rejectExeception(Runnable command){
            throw new RejectedExecutionException("task name "+ command + "can not solve");
        }
    }

    private void reject(Runnable command){
        RejectExceptionHandler exceptionHandler = new RejectExceptionHandler();
        exceptionHandler.rejectExeception(command);
    }
}
