package com.wey.juc_4.singleton;

import com.wey.annotation.ThreadSafe;

/**
 * @author Yale.Wei
 * @date 2018/10/26 17:09
 */
@ThreadSafe
public class Singleton_8 {


    private Singleton_8() {
    }

    private enum SingletonEnum{
        SINGLETON_ENUM;
        private Singleton_8 singleton;

        //JVM保证这个方法只调用一次
        SingletonEnum() {
            this.singleton = new Singleton_8();
        }

        public Singleton_8 getSingleton() {
            return singleton;
        }
    }

    public static Singleton_8 getSingleton(){
       return SingletonEnum.SINGLETON_ENUM.singleton;
    }
}
