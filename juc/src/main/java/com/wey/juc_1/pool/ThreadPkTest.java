package com.wey.juc_1.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午4:48
 */
public class ThreadPkTest {
    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final List<Integer> l = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(){
                @Override
                public void run() { l.add(random.nextInt()); }
            };
            t.start();
            t.join();
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(l.size());
    }
}
