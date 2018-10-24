package com.wey.tx;

/**
 * @author Yale.Wei
 * @date 2018/9/25 21:51
 */
public class Salary {
	private int userid;
	private Double salary;

	public Salary() {
	}

	public Salary(int userid, Double salary) {
		this.userid = userid;
		this.salary = salary;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Salary{" +
				"userid=" + userid +
				", salary=" + salary +
				'}';
	}
}
