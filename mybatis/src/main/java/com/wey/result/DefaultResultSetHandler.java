package com.wey.result;

import com.wey.binding.MapperMethod;
import com.wey.reflection.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yale.Wei
 * @date 2018/9/14 20:05
 * 处理结果集 通过反射赋值
 */
public class DefaultResultSetHandler implements ResultSetHandler {

	public <T> T handle(PreparedStatement preparedStatement, MapperMethod mapperMethod) throws Exception {
		Object resultObj = new DefaultObjectFactory().create(mapperMethod.getResultType());
		ResultSet rs = preparedStatement.getResultSet();
		if (rs.next()){
			int i = 0;
			Field[] fields = resultObj.getClass().getDeclaredFields();
			for (Field field : fields) {
				Method method = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
				method.invoke(resultObj,getResult(field,rs));
			}
		}
		return (T) resultObj;
	}

	private String upperCapital(String name) {
		String first = name.substring(0, 1);
		String tail = name.substring(1);
		return first.toUpperCase() + tail;
	}

	private Object getResult(Field field, ResultSet rs) throws SQLException {
		Class<?> type = field.getType();
		if(Integer.class == type){
			return rs.getInt(field.getName());
		}
		if(String.class == type){
			return rs.getString(field.getName());
		}
		if(Long.class==type){
			return  rs.getLong(field.getName());
		}
		if (int.class==type){
			return rs.getInt(field.getName());
		}
		return rs.getString(field.getName());
	}

}
