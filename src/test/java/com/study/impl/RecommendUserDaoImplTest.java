package com.study.impl;

import com.study.TestDB;
import com.study.mybatis.dao.IRecommendUserDao;
import com.study.mybatis.daoManager.DaoManager;
import com.study.mybatis.pojo.RecommendUser;

import java.util.List;
import com.google.common.collect.ImmutableList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecommendUserDaoImplTest {

	IRecommendUserDao recommendUserDao;

	@Before
	public void setUp() {
		DaoManager daoManager = new DaoManager(TestDB.h2DataSource());
		recommendUserDao = daoManager.getRecommendUserDAO();
	}

	@Test
	public void getAll() throws Exception {
		List<RecommendUser> all = recommendUserDao.getAll();
		assertEquals(1, all.get(0).getUser_id().intValue());
	}

	@Test
	public void getUserById() throws Exception {
		assertEquals(1, recommendUserDao.getUserById(1).getUser_id().intValue());
	}

	@Test
	public void testInsert() throws Exception {
		assertNull(recommendUserDao.getUserById(5));
		RecommendUser tru = new RecommendUser(5, 5);
		recommendUserDao.update(tru);
		assertEquals(5, recommendUserDao.getUserById(5).getUser_id().intValue());
	}

	@Test
	public void testUpdate() throws Exception {
		RecommendUser user1 = recommendUserDao.getUserById(1);
		assertEquals(3, user1.getRank().intValue());

		user1.setRank(4);
		recommendUserDao.update(user1);

		RecommendUser updatedUser1 = recommendUserDao.getUserById(1);
		assertEquals(4, updatedUser1.getRank().intValue());
	}

	@Test
	public void updateBatch() throws Exception {
		assertNull(recommendUserDao.getUserById(6));
		assertNull(recommendUserDao.getUserById(7));
		assertEquals(3, recommendUserDao.getUserById(1).getRank().intValue());
		List<RecommendUser> users = ImmutableList.of(new RecommendUser(6, 6), new RecommendUser(7, 7),
				new RecommendUser(1, 4));
		recommendUserDao.updateBatch(users);
		assertEquals(6, recommendUserDao.getUserById(6).getRank().intValue());
		assertEquals(7, recommendUserDao.getUserById(7).getRank().intValue());
		assertEquals(4, recommendUserDao.getUserById(1).getRank().intValue());
	}

	@Test
	public void delete() throws Exception {
		assertEquals(1, recommendUserDao.getUserById(1).getUser_id().intValue());
		recommendUserDao.delete(1);
		assertNull(recommendUserDao.getUserById(1));
	}
}
