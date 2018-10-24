package com.wey.binding;


import com.wey.session.Configuration;
import com.wey.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yale.Wei
 * @date 2018/9/14 18:06
 */
public class MapperRegistry {
	private Configuration configuration;
	public Map<Class<?>,MapperProxyFactory<?>> konwMappers = new HashMap<Class<?>, MapperProxyFactory<?>>();//key->interface,value->proxy_class

	public MapperRegistry(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Map<Class<?>, MapperProxyFactory<?>> getKonwMappers() {
		return konwMappers;
	}

	public void setKonwMappers(Map<Class<?>, MapperProxyFactory<?>> konwMappers) {
		this.konwMappers = konwMappers;
	}

	public <T> T getMapper(Class<T> type, SqlSession sqlSession){
		MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) konwMappers.get(type);
		return mapperProxyFactory.newInstance(sqlSession);
	}
}
