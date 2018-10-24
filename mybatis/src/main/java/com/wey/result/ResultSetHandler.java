package com.wey.result;

import com.wey.binding.MapperMethod;

import java.sql.PreparedStatement;

/**
 * @author Yale.Wei
 * @date 2018/9/14 20:04
 */
public interface ResultSetHandler {
	<T> T handle(PreparedStatement preparedStatement, MapperMethod mapperMethod) throws Exception;
}
