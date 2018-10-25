package com.wey.juc_2.volatiles;

import com.wey.util.ThreadUtil;

/**
 * @author Yale.Wei
 * @date 2018/10/25 15:16
 */
public class KafkaStart {
    static volatile boolean isStart;

    public synchronized void start(){
        if (isStart){
            throw new RuntimeException();
        }
        System.out.println("初始化完成");
        isStart = true;
    }

    public static void main(String[] args) throws InterruptedException {
        KafkaStart start = new KafkaStart();
        ThreadUtil.timeTasks(10,1,() -> {
            start.start();
        });
    }
}
