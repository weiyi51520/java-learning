package com.wey.juc_5.concurrent_1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yale.Wei
 * @date 2018/10/28 11:56
 */
public class ThreadPoolExecutor implements Executor {
    private volatile int corePoolSize;
    private volatile int maximumPoolSize;
    private BlockingQueue<Runnable> workQueue;

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable task) {
            Worker worker = new Worker(task);
            worker.thread.start();
            workQueue.offer(task);
    }

    class Worker extends ReentrantLock implements Runnable{
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
                if (task!=null||(task = getTask())!=null){
                    task.run();
                }
            } finally {
                this.unlock();
            }
        }

        public Runnable getTask(){
            try {
                if (workQueue.isEmpty()){
                    return null;
                }
                return workQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
