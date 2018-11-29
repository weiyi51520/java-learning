package com.wey.juc_4.singleton;

import com.wey.util.ThreadUtil;
import org.junit.Test;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Yale.Wei
 * @date 2018/10/26 17:05
 */
public class SingletonTest {

    @Test
    public void testSingleton1() throws InterruptedException {
        ThreadUtil.timeTasks(100,1,() -> {
            System.out.println(Singleton_1.getSingeton().hashCode());
        });
    }

    @Test
    public void testSingleton2() throws InterruptedException {
        final Set set = new CopyOnWriteArraySet();

        ThreadUtil.timeTasks(100,1,() -> {
            set.add(Singleton_2.getSingleton().hashCode());
        });

        System.out.println(set.size());
    }

    @Test
    public void testSingleton3() throws InterruptedException {
        final Set set = new CopyOnWriteArraySet();

        Singleton_3.getSingleton();

        ThreadUtil.timeTasks(100,1,() -> {
            set.add(Singleton_3.getSingleton().hashCode());
        });

        System.out.println(set.size());
    }

    @Test
    public void testSingleton4() throws InterruptedException {
        final Set set = new CopyOnWriteArraySet();

        Singleton_4.getSingleton();

        ThreadUtil.timeTasks(100,1,() -> {
            set.add(Singleton_4.getSingleton().hashCode());
        });

        System.out.println(set.size());
    }

    @Test
    public void testSingleton5() throws InterruptedException {
        final Set set = new CopyOnWriteArraySet();

        ThreadUtil.timeTasks(100,1,() -> {
            set.add(Singleton_5.getSingleton().hashCode());
        });

        System.out.println(set.size());
    }

    @Test
    public void testSingleton6() throws InterruptedException {
        final Set set = new CopyOnWriteArraySet();

        ThreadUtil.timeTasks(100,1,() -> {
            set.add(Singleton_6.getSingleton().hashCode());
        });

        System.out.println(set.size());
    }

    @Test
    public void testSingleton7() throws InterruptedException {
        final Set set = new CopyOnWriteArraySet();
        Singleton_7.getSingleton();

        ThreadUtil.timeTasks(100,1,() -> {
            set.add(Singleton_7.getSingleton().hashCode());
        });

        System.out.println(set.size());
    }

    @Test
    public void testSingleton8() throws InterruptedException {
        final Set set = new CopyOnWriteArraySet();
        Singleton_8.getSingleton();

        ThreadUtil.timeTasks(1000,100,() -> {
            set.add(Singleton_8.getSingleton().hashCode());
        });

        System.out.println(set.size());


    }
}
