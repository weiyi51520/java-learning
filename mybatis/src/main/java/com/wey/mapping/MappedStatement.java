package com.wey.mapping;

/**
 * @author Yale.Wei
 * @date 2018/9/16 16:03
 */
public class MappedStatement {
	private String id;
	private String boundSql;

	public MappedStatement(String id, String boundSql) {
		this.id = id;
		this.boundSql = boundSql;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBoundSql() {
		return boundSql;
	}

	public void setBoundSql(String boundSql) {
		this.boundSql = boundSql;
	}
}
