package com.wey.ioc;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Arrays;

/**
 * @author Yale.Wei
 * @date 2018/9/24 21:42
 */
public class BeanDefinitionReaderDemo {

	public static void main(String[] args) {
		//创建注册中心
		BeanDefinitionRegistry register = new SimpleBeanDefinitionRegistry();
		//xml读取器
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(register);
		//资源加载器
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("spring-ioc.xml");
		//加载xml文件创建BeanDefinition Map
		reader.loadBeanDefinitions(resource);

		String[] aliases = register.getAliases("ref");
		BeanDefinition ref = register.getBeanDefinition("ref");
		System.out.println(Arrays.toString(register.getBeanDefinitionNames()));
	}
}
