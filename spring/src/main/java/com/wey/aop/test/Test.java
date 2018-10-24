package com.wey.aop.test;

import com.wey.aop.config.AppConfig;
import com.wey.aop.dao.Dao;
import com.wey.aop.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Yale.Wei
 * @date 2018/10/12 11:08
 */
public class Test {


	@org.junit.Test
	public void testJdkProxy(){//直接对类进行代理
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.start();
		IndexDao bean = context.getBean(IndexDao.class);
		bean.query();
	}

	@org.junit.Test
	public void testCglib(){//通过spring对接口代理
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.start();
		Dao bean = context.getBean(Dao.class);
		bean.query();
	}
}
