package com.wey.juc_3.map;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Yale.Wei
 * @date 2018/10/26 11:21
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
    private static int defaultLength = 4;

    private static double defaultLoader = 0.75;

    private Entry<K, V>[] table = null;

    private int size;

    public MyHashMap(int length, double loader) {
        this.defaultLength = length;
        this.defaultLoader = loader;

        table = new Entry[defaultLength];
    }

    public MyHashMap() {
        this(defaultLength,defaultLoader);
    }


    @Override
    public V put(K k, V v) {
        if (size + 1 > defaultLength * defaultLoader) {
            reSize();
        }

        int index = getIndex(k);
        Entry<K, V> entry = table[index];
        if (entry == null){
            table[index] = new Entry<>(k,v,null);
        }else {
            table[index] = new Entry<>(k,v,entry);
        }
        size++;
        return table[index].getValue();
    }

    private int getIndex(K k) {
        int m = defaultLength;
        int index = k.hashCode() % m;
        return index >= 0 ? index : -index;
    }

    @Override
    public V get(K k) {
        Entry<K, V> first = table[getIndex(k)];
        if (k == first.getKey() || k.equals(first.getKey())){
            return first.getValue();
        }else {
            while (first.next!=null){
                first = first.next;
                if (k == first.getKey() || k.equals(first.getKey())){
                    return first.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public Entry<K, V>[] getTable() {
        return table;
    }

    private void reSize() {
        Entry<K, V>[] newTable = new Entry[2 * defaultLength];

        //re hash
        List<Entry> list = new ArrayList<>(2 * defaultLength);
        for (int i = 0; i < table.length; i++) {

            Entry<K, V> entry = table[i];
            if (entry == null) {
                continue;
            }
            list.add(entry);
            while (entry.next !=null){
                entry = entry.next;
                list.add(entry);
            }
        }

        if (list.size() > 0) {
            size = 0;
            defaultLength = defaultLength * 2;
            table = newTable;
            for (Entry<K, V> entry : list) {
                if (entry != null) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }


    class Entry<K, V> implements MyMap.Entry<K, V> {
        private K k;
        private V v;
        public Entry next;

        public Entry(K k, V v, Entry next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }


        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
