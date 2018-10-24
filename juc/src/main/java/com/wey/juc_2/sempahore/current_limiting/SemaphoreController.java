package com.wey.juc_2.sempahore.current_limiting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author Yale.Wei
 * @date 2018/10/24 16:59
 * TODO 未完成
 */
public class SemaphoreController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"RebateSemaphoreController");
        }
    });

    static {

    }

    private static final ConcurrentHashMap<String,SemaphoreWrapper> blockedQueues = new ConcurrentHashMap<>();

    public static <T> T execute(String methodName, Callable<T> r){
        int globalBlockedCount = 0;
        if (0!=globalBlockedCount){

        }
        return null;
    }

    static SemaphoreWrapper getSemaphoreWrapper(String method,int blockedCount){
        SemaphoreWrapper wrapper = blockedQueues.get(method);
        if (null == wrapper){
            synchronized (blockedQueues){
                wrapper = blockedQueues.get(method);
                if (null == wrapper){
                    wrapper = new SemaphoreWrapper(blockedCount);
                    blockedQueues.put(method,wrapper);
                }
            }
        }
        return wrapper;
    }
}
