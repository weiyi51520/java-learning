package com.wey.juc_2.lock;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午6:15
 */
public class Demo {

    public static void main(String[] args) {

        LikeSearch<String> likeSearch = new LikeSearch<String>();
        likeSearch.put("湖北","湖北");
        likeSearch.put("湖南","湖南");
        likeSearch.put("河北","河北");
        likeSearch.put("天北","天北");
        likeSearch.put("地北","地北");
        likeSearch.put("南北","南北");
        likeSearch.put("北北","北北");
        Collection<String> search = likeSearch.search("北北", 3);
        for(Iterator it = search.iterator(); it.hasNext();){
            System.out.println(it.next());
        }

    }
}
