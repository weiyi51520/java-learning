package com.wey.session;

import com.wey.executor.SimpleExecutor;

/**
 * @author Yale.Wei
 * @date 2018/9/15 19:01
 */
public class SqlSessionFactory {

	public SqlSession openSession(Configuration configuration){
		return new DefaultSqlSession(configuration,new SimpleExecutor(configuration));
	}
}
