package com.wey.tx;

import java.util.List;

/**
 * @author Yale.Wei
 * @date 2018/9/25 21:54
 */
public interface SalaryService {

	void addSalary(int userid,Double salary);

	List<Salary> selectSalary(int userid);

	int updateSalary(int userid,Double salary);
}
