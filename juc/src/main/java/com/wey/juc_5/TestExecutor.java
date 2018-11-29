package com.wey.juc_5;

import com.wey.juc_5.concurrent_1.ThreadPoolExecutor;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Yale.Wei
 * @date 2018/10/28 12:57
 */
public class TestExecutor {

    @Test
    public void testExecutor1(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 1, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("Yale is a good man");
            });
        }
    }
}
