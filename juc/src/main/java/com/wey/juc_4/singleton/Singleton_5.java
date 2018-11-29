package com.wey.juc_4.singleton;

import com.wey.annotation.ThreadNoSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/26 17:09
 */
@ThreadNoSafe
public class Singleton_5 {

    private static Singleton_5 singleton;

    private Singleton_5() {
    }

    /**
     *
     * @return
     */
    public static Singleton_5 getSingleton() {
        if (null==singleton){
            synchronized (Singleton_5.class){
                if (null == singleton){
                    singleton = new Singleton_5();//指令重排 导致多个实例
                }
            }
        }
        return singleton;
    }
}
