package com.wey.juc_4.singleton;

import com.wey.annotation.ThreadSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/26 17:09
 */
@ThreadSafe
public class Singleton_4 {

    private static Singleton_4 singleton;

    private Singleton_4() {
    }


    public static synchronized Singleton_4 getSingleton() {
        if (null==singleton){
            singleton = new Singleton_4();
        }
        return singleton;
    }
}
