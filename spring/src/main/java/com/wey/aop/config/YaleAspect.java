package com.wey.aop.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Yale.Wei
 * @date 2018/10/12 9:59
 */
@Aspect
@Component
public class YaleAspect {

	@Before("execution(* com.wey.aop.dao.Dao.*(..))")
	public void pointCutExecution(){
		System.out.println("before ...");
	}
}
