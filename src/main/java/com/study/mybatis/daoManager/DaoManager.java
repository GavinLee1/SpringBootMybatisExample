package com.study.mybatis.daoManager;

import javax.sql.DataSource;

//import org.apache.commons.configuration.BaseConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mybatis.dao.IRecommendUserDao;
import com.study.mybatis.impl.RecommendUserDaoImpl;
import com.study.mybatis.util.MybatisSessionUtil;

@Component
public class DaoManager {

	private SqlSessionFactory sessionFactory;
	private ObjectMapper om;
	
	public DaoManager(DataSource dataSource) {
		init(dataSource);
	}

	private void init(DataSource dataSource) {
		sessionFactory = MybatisSessionUtil.getSessionFactory(dataSource);
		om = new ObjectMapper();
	}
	
	public IRecommendUserDao getRecommendUserDAO() {
		return new RecommendUserDaoImpl(sessionFactory);
	}
	
}
