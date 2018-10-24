package com.wey;

import com.wey.mapper.UserMapper;
import com.wey.pojo.User;
import com.wey.session.Configuration;
import com.wey.session.SqlSession;
import com.wey.session.SqlSessionFactory;
import com.wey.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yale.Wei
 * @date 2018/9/15 18:47
 */
public class MybatisTest {
	public static void main(String[] args) throws IOException {
		InputStream inputStream = MybatisTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
		Configuration configuration = new Configuration();
		configuration.setInputStream(inputStream);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		SqlSession sqlSession = sqlSessionFactory.openSession(configuration);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUser(1);
		System.out.println(user);
	}
}
