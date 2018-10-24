package com.wey.juc_2.sempahore.current_limiting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Yale.Wei
 * @date 2018/10/24 15:33
 */
public class SemaphoreWrapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private volatile int queueCount = 20;

    private AtomicInteger blockedCount = new AtomicInteger(0);

    private Semaphore semaphore;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public SemaphoreWrapper(int queueCount) {
        this.queueCount = queueCount;
        this.semaphore = new Semaphore(queueCount);
    }

    public <T> T execute(Callable<T> r, String methodName, int timeout) throws Exception {
        T obj = null;
        try {
            lock.readLock().lockInterruptibly();
            blockedCount.addAndGet(1);

            if (semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
                blockedCount.addAndGet(-1);
                try {
                    obj = r.call();
                } finally {
                    semaphore.release();
                }
            }else {
                blockedCount.addAndGet(-1);
                if (log.isInfoEnabled()){
                    log.info("queueCount:" + getQueueCount() + ", blockedCount:" + getBlockedCount());
                }
                throw new SemaphoreGetException(" Get semaphore failed! ");
            }
        } finally {
            lock.readLock().unlock();
        }

        return obj;
    }

    public void resetSemaphore(int count){
        try {
            lock.writeLock().lock();
            this.queueCount = count;
            this.semaphore = new Semaphore(count);
        } finally {
            lock.writeLock().unlock();
        }

    }

    public int getQueueCount() {
        return queueCount;
    }

    public Integer getBlockedCount() {
        return blockedCount.get();
    }
}
