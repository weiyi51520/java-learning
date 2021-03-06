package com.wey.juc_2.sempahore.current_limiting;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Yale.Wei
 * @date 2018/10/22 09:48
 */
public class SearchServer<T> {
    private final CharColumn<T>[] columns = new CharColumn[Character.MAX_VALUE];
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public static void main(String[] args) {
        SearchServer<String> server = new SearchServer<>();
        server.put("湖北", "湖北");
        server.put("湖南", "湖南");
        server.put("河北", "河北");
        server.put("天北", "天北");
        server.put("地北", "地北");
        server.put("南北", "南北");
        server.put("北北", "北北");

        Collection collection = server.search("北北", 3);
        collection.forEach(o -> {
            System.out.println(o);
        });
    }

    public void put(T t, String value) {
        w.lock();
        try {
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                CharColumn<T> cloumn = columns[c];
                if (cloumn == null) {
                    cloumn = new CharColumn<T>();
                    columns[c] = cloumn;
                }
                cloumn.add(t, (byte) i);
            }
        } finally {
            w.unlock();
        }

    }

    //先删除
    //加入
    //更新了
    public void update(T t, String newValue) {
        w.lock();
        try {
            remove(t);
            put(t, newValue);
        } finally {
            w.unlock();
            ;
        }


    }

    public boolean remove(T t) {
        w.lock();
        try {
            for (CharColumn<T> column : columns) {
                if (column != null) {
                    if (column.remove(t)) {
                        return true;
                    }
                }
            }
            return false;
        } finally {
            w.unlock();
        }
    }

    public Collection search(String word, int limit) {
        r.lock();

        try {
            int n = word.length();
            char chars[] = word.toCharArray();
            Context context = new Context();
            for (int i = 0; i < chars.length; i++) {
                CharColumn<T> column = columns[chars[i]];
                if (column == null) {
                    break;
                }
                if (!context.filter(column)) {
                    break;
                }
                n--;

            }
            if (n == 0) {

                return context.limit(limit);

            }
            return Collections.emptySet();
        } finally {
            r.unlock();
        }

    }

    private class Context {
        Map<T, byte[]> result;
        boolean used = false;

        private boolean filter(CharColumn<T> columns) {
            if (this.used == false) {
                this.result = new TreeMap<>(columns.poxIndex);
                this.used = true;
                return true;
            }
            boolean flag = false;
            Map<T, byte[]> newReulst = new TreeMap<T, byte[]>();
            Set<Map.Entry<T, byte[]>> entrySet = columns.poxIndex.entrySet();
            for (Map.Entry<T, byte[]> entry : entrySet) {
                T id = entry.getKey();
                byte[] charPox = entry.getValue();
                if (!result.containsKey(id)) {
                    continue;
                }
                byte[] before = result.get(id);
                boolean in = false;
                for (byte pox : before) {

                    if (contain(charPox, (byte) (pox + 1))) {
                        in = true;
                        break;
                    }
                }
                if (in) {
                    flag = true;
                    newReulst.put(id, charPox);
                }
            }
            result = newReulst;
            return true;
        }

        public Collection<T> limit(int limit) {

            if (result.size() <= limit) {
                return result.keySet();
            }
            Collection<T> ids = new TreeSet<T>();
            for (T id : result.keySet()) {
                ids.add(id);
                if (ids.size() >= limit) {
                    break;
                }


            }
            return ids;
        }


    }

    private class CharColumn<T> {
        ConcurrentHashMap<T, byte[]> poxIndex = new ConcurrentHashMap<>();

        void add(T t, byte pox) {
            byte[] arr = poxIndex.get(t);
            if (null == arr) {
                arr = new byte[]{pox};
            } else {
                arr = copy(arr, pox);
            }
            poxIndex.put(t, arr);

        }

        boolean remove(T id) {
            if (poxIndex.remove(id) != null) {
                return true;
            }
            return false;
        }
    }

    private static byte[] copy(byte[] arr, byte value) {
        Arrays.sort(arr);
        if (contain(arr, value)) {
            return arr;
        }
        byte[] newArr = new byte[arr.length + 1];
        newArr[newArr.length - 1] = value;
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        Arrays.sort(newArr);
        return newArr;
    }

    private static boolean contain(byte[] arr, byte value) {
        int pox = Arrays.binarySearch(arr, value);
        return (pox >= 0) ? true : false;
    }
}
