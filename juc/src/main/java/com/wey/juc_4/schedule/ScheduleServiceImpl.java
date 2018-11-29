package com.wey.juc_4.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/10/26 16:48
 */
public class ScheduleServiceImpl implements ScheduleService {

    ScheduledExecutorService executor =  Executors.newScheduledThreadPool(10);

    @Override
    public void startJob(int seconds) {
        executor.scheduleWithFixedDelay(new Monitor(),0,seconds, TimeUnit.SECONDS);
    }

    @Override
    public void shutDown() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        ScheduleService scheduledExecutor = new ScheduleServiceImpl();
        scheduledExecutor.startJob(2);
    }
}
