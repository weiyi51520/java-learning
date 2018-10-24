package com.wey.tx;

import org.springframework.aop.framework.AopContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Yale.Wei
 * @date 2018/9/25 21:54
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private SalaryService salaryService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createUser(int id,String name) {
		jdbcTemplate.update("INSERT INTO `user` (id,username) VALUES (?,?)",id,name);
		((UserService)AopContext.currentProxy()).addSalary(id,10000d);
		int i =1 / 0;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addSalary(int userid, Double salary) {
		jdbcTemplate.update("INSERT INTO salary VALUES (?,?)",userid,salary);
	}
}
