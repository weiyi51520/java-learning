package com.wey.reflection;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Yale.Wei
 * @date 2018/9/14 20:08
 */
public interface ObjectFactory {
	<T> T create(Class<T> var1) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
