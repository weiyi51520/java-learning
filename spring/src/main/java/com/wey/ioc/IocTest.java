package com.wey.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yale.Wei
 * @date 2018/9/23 19:46
 */
public class IocTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
//		SpringBean bean = (SpringBean) context.getBean("createByFactory");
		SpringBean bean =context.getBean(SpringBean.class);
//		Driver driver = context.getBean(Driver.class);
	}
}
