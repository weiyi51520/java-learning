package com.wey.ioc;

/**
 * @author Yale.Wei
 * @date 2018/9/23 21:05
 */
public class RefBean {
	public RefBean() {
		System.out.println("ref constructor ...");
	}

	public void say(){
		System.out.println("Hello");
	}
}
