package com.wey.juc_4.singleton;

import com.wey.annotation.ThreadSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/26 16:56
 */
@ThreadSafe
public class Singleton_1 {
    private static Singleton_1 singeton= new Singleton_1();

    private Singleton_1() {
    }

    public static Singleton_1 getSingeton(){
        return singeton;
    }

}
