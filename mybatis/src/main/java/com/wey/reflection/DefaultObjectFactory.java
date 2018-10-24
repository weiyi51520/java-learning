package com.wey.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Yale.Wei
 * @date 2018/9/14 20:10
 */
public class DefaultObjectFactory implements ObjectFactory {

	public DefaultObjectFactory() {
	}

	public <T> T create(Class<T> var1) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class<?> classToCreate = resolveInterface(var1);
		Constructor<?> constructor = classToCreate.getDeclaredConstructor();
		return (T) constructor.newInstance();
	}

	protected Class<?> resolveInterface(Class<?> type){
		Class classToCreate;
		if (type != List.class && type != Collection.class && type != Iterable.class){
			if (type == Map.class) {
				classToCreate = HashMap.class;
			} else if (type == SortedSet.class) {
				classToCreate = TreeSet.class;
			} else if (type == Set.class) {
				classToCreate = HashSet.class;
			} else {
				classToCreate = type;
			}
		} else {
			classToCreate = ArrayList.class;
		}
		return classToCreate;
	}
}
