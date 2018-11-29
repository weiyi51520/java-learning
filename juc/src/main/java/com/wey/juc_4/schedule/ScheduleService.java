package com.wey.juc_4.schedule;

/**
 * @author Yale.Wei
 * @date 2018/10/26 16:47
 */
public interface ScheduleService {
    void startJob(int seconds);
    void shutDown();
}
