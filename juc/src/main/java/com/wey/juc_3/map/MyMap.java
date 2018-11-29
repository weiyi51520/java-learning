package com.wey.juc_3.map;

/**
 * @author Yale.Wei
 * @date 2018/10/26 11:17
 */
public interface MyMap<K,V> {
    V put(K k,V v);

    V get(K k);

    int size();

    interface Entry<K,V>{

        K getKey();

        V getValue();
    }
}
