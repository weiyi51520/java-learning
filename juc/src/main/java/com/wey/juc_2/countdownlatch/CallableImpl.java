package com.wey.juc_2.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author Yale.Wei
 * @date 2018/10/18 下午5:35
 */
public class CallableImpl implements Callable {

    private CountDownLatch latch;

    public CallableImpl(CountDownLatch latch) { this.latch = latch; }

    public String doSomeThing(){
        latch.countDown();
        return "Things done in one minute";
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(5000);
        return doSomeThing();
    }
}
