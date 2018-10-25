package com.wey.juc_3.atomic;

import com.wey.util.ThreadUtil;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Yale.Wei
 * @date 2018/10/25 17:40
 */
public class AtomicIntegerFieldUpdaterExample {

    static final AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "score");

    public static void main(String[] args) throws InterruptedException {
        final Student student = new Student();
        ThreadUtil.timeTasks(100,0,() -> {
           updater.incrementAndGet(student);
        });
        System.out.println(student.score);
    }

}

class Student{
    String name;
    volatile int score;
}