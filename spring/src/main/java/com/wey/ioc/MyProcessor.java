package com.wey.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Yale.Wei
 * @date 2018/11/15 19:30
 */
public class MyProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBean){
            System.out.println("before ...");
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBean){
            System.out.println("after ...");
        }
        return null;
    }
}
