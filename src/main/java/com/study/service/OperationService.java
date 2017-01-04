package com.study.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.study.mybatis.daoManager.DaoManager;
import com.study.mybatis.pojo.RecommendUser;
import com.study.response.ResponseWrapper;
import com.study.mybatis.dao.IRecommendUserDao;

@Component
public class OperationService {

	private static final Logger log = LoggerFactory.getLogger(OperationService.class);

	@Autowired
	private DaoManager daoManager;

	private IRecommendUserDao recommendUserDao;

	@PostConstruct
	private void init() {
		recommendUserDao = daoManager.getRecommendUserDAO();
	}

	public ResponseWrapper<List<RecommendUser>> getRecommendUserList() {
		// TODO Auto-generated method stub
		ResponseWrapper<List<RecommendUser>> response = null;

		try {
			List<RecommendUser> data = recommendUserDao.getAll();
			if (data != null) {
				response = new ResponseWrapper(data);
			} else {
				response = ResponseWrapper.ERROR_RESPONSE();
			}
		} catch (Exception e) {
			log.error("Failed on get recommend user list", e);
			response = ResponseWrapper.ERROR_RESPONSE();
		}

		return response;
	}

}
