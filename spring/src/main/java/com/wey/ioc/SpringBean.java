package com.wey.ioc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Yale.Wei
 * @date 2018/9/23 19:44
 */
public class SpringBean implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,DisposableBean,InitializingBean{
	private String name;
	private int sex;
	private RefBean ref;

	public SpringBean() {
	}

	public SpringBean(RefBean ref) {
		this.ref = ref;
	}

	public SpringBean(String name, int sex) {
		System.out.println("SpringBean constructor ...");
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public static SpringBean createBean(String name){
		if (StringUtils.equalsIgnoreCase(name,"Yale.Wei")){
			return new SpringBean(name,1);
		}else {
			throw new IllegalArgumentException("Unexpected argument name when create SpringBean!");
		}
	}

	public RefBean getRef() {
		return ref;
	}

	public void setRef(RefBean ref) {
		this.ref = ref;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory ....");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("setBeanName ...");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("setApplicationContext ...");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy ...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet ...");
	}
}
