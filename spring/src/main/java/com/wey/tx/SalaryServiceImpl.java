package com.wey.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Yale.Wei
 * @date 2018/9/25 21:57
 */
@Service
public class SalaryServiceImpl implements SalaryService {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addSalary(int userid, Double salary) {
		jdbcTemplate.update("INSERT INTO salary VALUES (?,?)",userid,salary);
	}

	@Override
	@Transactional
	public List<Salary> selectSalary(int userid) {
		List<Salary> list = jdbcTemplate.queryForList("SELECT * FROM salary WHERE userid=?", Salary.class, userid);
		Arrays.toString(list.toArray());
		return list;
	}

	@Override
	@Transactional
	public int updateSalary(int userid, Double salary) {
		return jdbcTemplate.update("SELECT * from account set salary =salary+? where userid=?", salary,userid );
	}
}
