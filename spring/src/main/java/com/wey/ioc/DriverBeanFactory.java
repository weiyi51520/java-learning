package com.wey.ioc;

import org.springframework.beans.factory.FactoryBean;

import java.sql.DriverManager;

/**
 * @author Yale.Wei
 * @date 2018/9/23 20:07
 */
public class DriverBeanFactory implements FactoryBean{
	private String jdbcUrl;

	@Override
	public Object getObject() throws Exception {
		return DriverManager.getDriver(jdbcUrl);
	}

	@Override
	public Class<?> getObjectType() {
		return java.sql.Driver.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
}
