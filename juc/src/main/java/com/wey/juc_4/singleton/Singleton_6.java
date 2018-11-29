package com.wey.juc_4.singleton;

import com.wey.annotation.ThreadSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/26 17:09
 */
@ThreadSafe
public class Singleton_6 {

    private static Singleton_6 singleton;

    private Singleton_6() {
    }


    public static Singleton_6 getSingleton() {
        if (null==singleton){
            synchronized (Singleton_6.class){
                if (null==singleton){
                    singleton = new Singleton_6();
                }
            }
        }
        return singleton;
    }
}
