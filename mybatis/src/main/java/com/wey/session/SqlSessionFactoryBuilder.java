package com.wey.session;

import java.io.IOException;

/**
 * @author Yale.Wei
 * @date 2018/9/15 19:02
 */
public class SqlSessionFactoryBuilder {

	public SqlSessionFactory build(Configuration configuration) throws IOException {
		configuration.loanConfigurations();
		return new SqlSessionFactory();
	}
}
