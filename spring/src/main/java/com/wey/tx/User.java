package com.wey.tx;

/**
 * @author Yale.Wei
 * @date 2018/9/25 21:49
 */
public class User {
	private int id;
	private String username;
	private int age;
	private String phone;
	private String remarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", age=" + age +
				", phone='" + phone + '\'' +
				", remarks='" + remarks + '\'' +
				'}';
	}
}
