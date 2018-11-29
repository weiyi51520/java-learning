package com.wey.juc_3.map;

/**
 * @author Yale.Wei
 * @date 2018/10/26 14:07
 */
public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String,String> map = new MyHashMap();
        map.put("wukong","悟空");
        map.put("tangsen","唐僧");
        map.put("shaheshang","沙和尚");
        map.put("zhubajie","猪八戒");
        System.out.println("map entry numbers :"+map.size());
        System.out.println("map table length :"+map.getTable().length);
        System.out.println(map.get("wukong"));
        System.out.println(map.get("tangsen"));
        System.out.println(map.get("shaheshang"));
        System.out.println(map.get("zhubajie"));
    }
}
