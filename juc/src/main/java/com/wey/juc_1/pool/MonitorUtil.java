package com.wey.juc_1.pool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午4:08
 */
public class MonitorUtil implements Runnable {
    private ThreadPoolExecutor executor;
    private int delay;
    private boolean flag = true;

    public MonitorUtil(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        this.delay = delay;
    }

    public void shutDown() {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {
            synchronized (MonitorUtil.class) {
                try {
                    if (executor.isTerminated()) {
                        System.out.println("任务执行完成");
                        break;
                    }
                    System.out.println(
                            String.format("[monitor] 池大小:%d,核心数：%d,最大线程数：%d,队列长度：%d, 任务活跃数: %d, 任务完成数: %d, 任务数: %d, 线程结束没: %s, 任务结束没: %s",
                                    this.executor.getPoolSize(),
                                    this.executor.getCorePoolSize(),
                                    this.executor.getMaximumPoolSize(),
                                    this.executor.getQueue().size(),
                                    this.executor.getActiveCount(),
                                    this.executor.getCompletedTaskCount(),
                                    this.executor.getTaskCount(),
                                    this.executor.isShutdown(),
                                    this.executor.isTerminated()));
                    System.out.println("Monitor waiting ...");
                    MonitorUtil.class.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
