package com.study.mybatis.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.study.mybatis.dao.IRecommendUserDao;
import com.study.mybatis.mapper.RecommendUserMapper;
import com.study.mybatis.pojo.RecommendUser;

public class RecommendUserDaoImpl implements IRecommendUserDao {
	
	private SqlSessionFactory readOnlySessionFactory;
	
	private SqlSessionFactory sqlSessionFactory;

	
	public RecommendUserDaoImpl(SqlSessionFactory readOnlySessionFactory, SqlSessionFactory sqlSessionFactory) {
		super();
		this.readOnlySessionFactory = readOnlySessionFactory;
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private RecommendUserMapper getMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(RecommendUserMapper.class);
	}
	
	@Override
	public List<RecommendUser> getAll() {
		
		// Put into try is because it will release the connection after use.
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			RecommendUserMapper mapper = getMapper(session);
			return mapper.getAll();
		}
	}

	@Override
	public void update(RecommendUser user) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			RecommendUserMapper mapper = getMapper(session);
			mapper.update(user);
		}
	}

	@Override
	public void updateBatch(List<RecommendUser> user) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			RecommendUserMapper mapper = getMapper(session);
			for(RecommendUser reu : user)
			{
				mapper.update(reu);
			}
		}
	}

	@Override
	public void delete(int userId) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			RecommendUserMapper mapper = getMapper(session);
			mapper.delete(userId);
		}
	}

	@Override
	public RecommendUser getUserById(int userId) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			RecommendUserMapper mapper = getMapper(session);
			return mapper.getUserById(userId);
		}
	}

}
