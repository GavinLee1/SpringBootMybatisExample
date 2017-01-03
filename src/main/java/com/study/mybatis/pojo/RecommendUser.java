package com.study.mybatis.pojo;

public class RecommendUser {
	
	private Integer user_id;
	private Integer rank;

	public RecommendUser() {
	}

	public RecommendUser(Integer user_id, Integer rank) {
		super();
		this.user_id = user_id;
		this.rank = rank;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
