package com.wey.executor;

import com.wey.binding.MapperMethod;
import com.wey.session.Configuration;
import com.wey.statement.StatementHandler;

/**
 * @author Yale.Wei
 * @date 2018/9/14 20:01
 */
public class SimpleExecutor implements Executor {

	private Configuration configuration;

	public SimpleExecutor(Configuration configuration) {
		this.configuration = configuration;
	}

	public <T> T query(MapperMethod method, Object parameter) throws Exception {
		StatementHandler handler = new StatementHandler(configuration);
		return handler.query(method,parameter);
	}
}
