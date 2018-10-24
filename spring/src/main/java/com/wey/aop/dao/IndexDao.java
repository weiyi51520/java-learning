package com.wey.aop.dao;

import com.wey.aop.anno.Yale;
import com.wey.aop.mapper.MusicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Yale.Wei
 * @date 2018/10/11 17:56
 */
@Yale
public class IndexDao implements Dao{

	@Autowired
	private MusicMapper musicMapper;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void query() {
		jdbcTemplate.update("insert into user(id,username) values(1012,\"yale\")",new HashMap<>());
		int i =1/0;
	}
}
