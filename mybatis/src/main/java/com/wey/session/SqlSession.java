package com.wey.session;

import com.wey.binding.MapperMethod;

/**
 * @author Yale.Wei
 * @date 2018/9/14 19:57
 */
public interface SqlSession {
	<T> T selectOne(MapperMethod mapperMethod,Object statement) throws Exception;

	Configuration getConfiguration();

	<T> T getMapper(Class<T> type);
}
