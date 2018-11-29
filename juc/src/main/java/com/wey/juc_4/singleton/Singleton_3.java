package com.wey.juc_4.singleton;

import com.wey.annotation.ThreadSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/26 17:09
 */
@ThreadSafe
public class Singleton_3 {

    private static Singleton_3 singleton;

    private Singleton_3() {
    }

    static {
        singleton = new Singleton_3();
    }

    public static Singleton_3 getSingleton() {
        return singleton;
    }
}
