package com.wey.binding;

import com.wey.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Yale.Wei
 * @date 2018/9/15 8:48
 */
public class MapperProxy<T> implements InvocationHandler{

	private SqlSession sqlSession;
	private final Class<T> mapperInterface;
	private Map<Method,MapperMethod> methodCache;

	public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
		this.sqlSession = sqlSession;
		this.mapperInterface = mapperInterface;
		this.methodCache = methodCache;
	}

	public Object invoke(Object proxy, final Method method, Object[] args) throws Throwable {
		MapperMethod mapperMethod = methodCache.computeIfAbsent(method,
				k -> new MapperMethod(method.getDeclaringClass()+"."+method.getName(), method.getReturnType()));
		if (mapperMethod!=null){
			return sqlSession.selectOne(mapperMethod,String.valueOf(args[0]));
		}else {
			return method.invoke(proxy,args);
		}
	}


}
