package com.study.mybatis.util;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.study.mybatis.mapper.RecommendUserMapper;

public class MybatisSessionUtil {
	public static SqlSessionFactory getSessionFactory(DataSource dataSource) {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);

		Configuration configuration = new Configuration(environment);

		configuration.addMapper(RecommendUserMapper.class);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

		return sqlSessionFactory;
	}
}
