package com.wey.juc_4.singleton;

import com.wey.annotation.ThreadNoSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/26 17:09
 */
@ThreadNoSafe
public class Singleton_2 {

    private static Singleton_2 singleton;

    private Singleton_2() {
    }

    public static Singleton_2 getSingleton() {
        if (null == singleton){
            singleton = new Singleton_2();
        }
        return singleton;
    }
}
