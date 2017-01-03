package com.study.mybatis.daoManager;

import javax.sql.DataSource;

//import org.apache.commons.configuration.BaseConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mybatis.dao.IRecommendUserDao;
import com.study.mybatis.impl.RecommendUserDaoImpl;
import com.study.mybatis.util.MybatisSessionUtil;

public class DaoManager {

	private SqlSessionFactory sessionFactory;
	private SqlSessionFactory readOnlySessionFactory;
	private ObjectMapper om;
	
	public DaoManager(DataSource readOnlyDataSource, DataSource dataSource) {
		init(readOnlyDataSource, dataSource);
	}

	private void init(DataSource readOnlyDataSource, DataSource dataSource) {
		sessionFactory = MybatisSessionUtil.getSessionFactory(dataSource);
		readOnlySessionFactory = MybatisSessionUtil.getSessionFactory(readOnlyDataSource);
		om = new ObjectMapper();
	}
	
	public IRecommendUserDao getRecommendUserDAO() {
		return new RecommendUserDaoImpl(readOnlySessionFactory, sessionFactory);
	}
	
}
