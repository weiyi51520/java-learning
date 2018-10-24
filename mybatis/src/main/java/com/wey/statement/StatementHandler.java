package com.wey.statement;

import com.wey.binding.MapperMethod;
import com.wey.mapping.MappedStatement;
import com.wey.result.DefaultResultSetHandler;
import com.wey.result.ResultSetHandler;
import com.wey.session.Configuration;
import com.wey.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Yale.Wei
 * @date 2018/9/14 20:02
 */
public class StatementHandler {
	private Configuration configuration;

	private ResultSetHandler resultSetHandler;
	public StatementHandler() { }

	public StatementHandler(Configuration configuration) {
		this.configuration = configuration;
		this.resultSetHandler = new DefaultResultSetHandler();
	}

	public <T> T query(MapperMethod method,Object parameter) throws Exception {
		Connection connection = DBUtil.open();
		MappedStatement mappedStatement = configuration.getMappedStatements().get(method.getSqlName());
		PreparedStatement preparedStatement = connection.prepareStatement(String.format(mappedStatement.getBoundSql(), Integer.parseInt((String) parameter)));
		preparedStatement.execute();
		return resultSetHandler.handle(preparedStatement,method);
	}
}
