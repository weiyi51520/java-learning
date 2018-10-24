package com.wey.session;

import com.wey.binding.MapperMethod;
import com.wey.binding.MapperProxy;
import com.wey.executor.Executor;

import java.lang.reflect.Proxy;

/**
 * @author Yale.Wei
 * @date 2018/9/14 19:59
 */
public class DefaultSqlSession implements SqlSession {

	private Configuration configuration;
	private Executor executor;

	public DefaultSqlSession(Configuration configuration, Executor executor) {
		this.configuration = configuration;
		this.executor = executor;
	}


	public <T> T selectOne(MapperMethod mapperMethod, Object statement) throws Exception {
		return executor.query(mapperMethod,statement);
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public <T> T getMapper(Class<T> type) {
		return configuration.getMapper(type,this);
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}
}
