package com.wey.binding;

/**
 * @author Yale.Wei
 * @date 2018/9/14 18:07
 */
public class MapperMethod<T> {
	private String sqlName;
	private Class<T> resultType;

	public MapperMethod() {
	}

	public MapperMethod(String name, Class<T> resultType) {
		this.sqlName = name;
		this.resultType = resultType;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public Class<T> getResultType() {
		return resultType;
	}

	public void setResultType(Class<T> resultType) {
		this.resultType = resultType;
	}

}
