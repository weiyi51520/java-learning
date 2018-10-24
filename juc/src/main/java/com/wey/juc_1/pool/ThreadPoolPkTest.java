package com.wey.juc_1.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午4:55
 */
public class ThreadPoolPkTest {
    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            final int j = i;
            executor.execute(new Runnable() {
                @Override
                public void run() { list.add(j); }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(list.size());

    }
}
