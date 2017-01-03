package com.study.mybatis.dao;

import java.util.List;

import com.study.mybatis.pojo.RecommendUser;

public interface IRecommendUserDao {

	List<RecommendUser> getAll();

	void update(RecommendUser user);

	void updateBatch(List<RecommendUser> user);

	void delete(int userId);

	RecommendUser getUserById(int userId);

}
