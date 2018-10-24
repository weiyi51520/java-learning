package com.wey.mvc;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Yale.Wei
 * @date 2018/10/10 9:44
 */
public class MvcBeanFactory {
	private ApplicationContext applicationContext;

	private HashMap<String, MvcBean> apiMap = new HashMap();

	public MvcBeanFactory(ApplicationContext applicationContext) {
		Assert.notNull(applicationContext,"argument 'applicationContext' must not be null");
		this.applicationContext = applicationContext;
	}

	private void loadApiFromSpringBeans() {
		apiMap.clear();
		String[] names = applicationContext.getBeanDefinitionNames();
		Class<?> type;
		for (String name : names) {
			type = applicationContext.getType(name);
			for (Method method : type.getDeclaredMethods()) {
				MvcMapping annotation = method.getAnnotation(MvcMapping.class);
				if (annotation!=null){
					addApiItem(annotation,name,method);
				}
			}
		}
	}

	private void addApiItem(MvcMapping mvcMapping,String beanName,Method method){
		MvcBean apiRun = new MvcBean();
		apiRun.apiName = mvcMapping.value();
		apiRun.targetMethod = method;
		apiRun.targetName = beanName;
		apiRun.context = this.applicationContext;
		apiMap.put(mvcMapping.value(),apiRun);
	}

	public MvcBean getMvcBean(String apiName) {return apiMap.get(apiName);}


	public boolean contains(String apiName){
		return apiMap.containsKey(apiName);
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static class MvcBean {
		String apiName;
		String targetName;
		Object target;
		Method targetMethod;
		ApplicationContext context;

		public Object run(Object... args) throws InvocationTargetException, IllegalAccessException {
			if (target == null) {
				target = context.getBean(targetName);
			}
			return targetMethod.invoke(target,args);
		}

		public String getApiName() {
			return apiName;
		}

		public String getTargetName() {
			return targetName;
		}

		public Object getTarget() {
			return target;
		}

		public Method getTargetMethod() {
			return targetMethod;
		}
	}
}
