package com.wey.aop.dao;

/**
 * @author Yale.Wei
 * @date 2018/10/12 9:57
 */
public class IndexService implements Service{
	@Override
	public String testService(String name) {
		return "hello";
	}
}
