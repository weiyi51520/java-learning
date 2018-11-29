package com.wey.juc_3.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Yale.Wei
 * @date 2018/10/26 10:54
 */
public class CopyOnWriterArrayListExample {
    public static void main(String[] args) throws InterruptedException {
        final List<Integer> list = new CopyOnWriteArrayList<>();
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
            System.out.println("第" + (i + 1) + "个元素为：" + list.get(i));
        }
    }
}
