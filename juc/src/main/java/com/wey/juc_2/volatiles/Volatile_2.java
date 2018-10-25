package com.wey.juc_2.volatiles;

/**
 * @author Yale.Wei
 * @date 2018/10/25 15:38
 */
public class Volatile_2 implements Runnable{
    static volatile int i = 1;

    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName()+" : "+i+", "+(++i));
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Volatile_2(),"A");
        Thread t2 = new Thread(new Volatile_2(),"B");
        Thread t3 = new Thread(new Volatile_2(),"C");
        Thread t4 = new Thread(new Volatile_2(),"D");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
