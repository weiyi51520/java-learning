package com.wey.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.Arrays;

/**
 * @author Yale.Wei
 * @date 2018/9/24 22:17
 */
public class BeanFactoryDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.setResourceLoader(new DefaultResourceLoader());
		reader.loadBeanDefinitions("spring-ioc.xml");
		Object ref = factory.getBean("ref");
		Object ref2 = factory.getBean("ref");//ref2==ref

		System.out.println(Arrays.toString(factory.getBeanDefinitionNames()));
	}
}
