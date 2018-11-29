package com.wey.juc_3.map;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/10/26 15:36
 */
public class ConcurrentHashMapExample {
    final static Map<String,String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(String.valueOf(i),String.valueOf(i));
            }
        }).start();

        new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                map.put(String.valueOf(i),String.valueOf(i));
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);

        for(int i=0;i<2000;i++){
            String key = String.valueOf(i);
            String value = map.get(key);
            if (!StringUtils.equalsIgnoreCase(key, value)){
                System.out.println("元素: 键=" + i + ", 值:"+value);
            }
        }
    }

}
