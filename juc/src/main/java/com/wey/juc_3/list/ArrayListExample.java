package com.wey.juc_3.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yale.Wei
 * @date 2018/10/25 18:14
 */
public class ArrayListExample {
    public static void main(String[] args) throws InterruptedException {
        final List<Integer> list = new ArrayList<>();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                    list.add(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 10000; i < 20000; i++) {
                    list.add(i);
            }
        }).start();

        Thread.sleep(1000);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("第" + (i + 1) + "个元素:" + list.get(i));
        }
    }
}
