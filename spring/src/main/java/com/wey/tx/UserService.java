package com.wey.tx;

/**
 * @author Yale.Wei
 * @date 2018/9/25 21:53
 */
public interface UserService {

	void createUser(int id, String name);

	void addSalary(int userid, Double salary);
}
