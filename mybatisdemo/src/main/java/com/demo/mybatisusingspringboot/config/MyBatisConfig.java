package com.demo.mybatisusingspringboot.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
	
	@Autowired
	DataSource dataSource;
	
	/*
	 * public DataSourceTransactionManager transactionManager() { return new
	 * DataSourceTransactionManager(dataSource); }
	 */
	
	/*
	 * @Bean public SqlSession sqlSession() throws Exception { SqlSessionFactoryBean
	 * sessionFactory = new SqlSessionFactoryBean();
	 * sessionFactory.setDataSource(dataSource); SqlSessionFactory sqlSessionFactory
	 * = sessionFactory.getObject(); return sqlSessionFactory.openSession(); }
	 */
}
