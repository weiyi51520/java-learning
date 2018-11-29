package com.wey.juc_4.singleton;

import com.wey.annotation.ThreadSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/26 17:09
 */
@ThreadSafe
public class Singleton_7 {


    private Singleton_7() {
    }

    private static class SingletonInner{
        private static final Singleton_7 singleton  = new Singleton_7();
    }

    public static Singleton_7 getSingleton() {
        return SingletonInner.singleton;
    }
}
