package com.wey.executor;

import com.wey.binding.MapperMethod;

/**
 * @author Yale.Wei
 * @date 2018/9/14 20:00
 */
public interface Executor {
	<T> T query(MapperMethod method,Object parameter) throws Exception;
}
