package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.mybatis.pojo.RecommendUser;
import com.study.response.ResponseWrapper;
import com.study.service.OperationService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/operation")
public class OperationController {
	
	/*http://localhost:8080/swagger-ui.html*/
	
	@Autowired private OperationService operationService;
	
	@ApiOperation(value = "Get all recommended user's list")
	@RequestMapping(value = "/recommend/user/index", method = RequestMethod.GET)
	@ResponseBody
	public ResponseWrapper<List<RecommendUser>> getRecommendUserList() {
		
		return operationService.getRecommendUserList();
	}
}
