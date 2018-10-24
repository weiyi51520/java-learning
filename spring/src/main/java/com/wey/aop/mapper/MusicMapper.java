package com.wey.aop.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yale.Wei
 * @date 2018/10/12 9:41
 */
@Mapper
public interface MusicMapper {

	@Insert("insert into user(id,username) values(1,\"turing\")")
	void save();

}
