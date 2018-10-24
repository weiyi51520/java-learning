package com.wey.binding;

import com.wey.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yale.Wei
 * @date 2018/9/15 9:07
 */
public class MapperProxyFactory<T> {

	private Class<T> mapperInterface;
	private Map<Method,MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();

	public MapperProxyFactory(Class<T> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}


	public Map<Method, MapperMethod> getMethodCache() {
		return methodCache;
	}

	protected T newInstance(MapperProxy<T> mapperProxy){
		return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
	}

	public T newInstance(SqlSession sqlSession){
		final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface, methodCache);
		return newInstance(mapperProxy);
	}

}
