package com.study.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.study.mybatis.pojo.RecommendUser;

public interface RecommendUserMapper {

    @Select("SELECT * FROM recommend_user")
    List<RecommendUser> getAll();

    @Update("INSERT INTO recommend_user (user_id, rank) VALUES (#{user_id}, #{rank}) ON DUPLICATE KEY UPDATE rank = #{rank}")
    void update(RecommendUser user);

    @Delete("DELETE FROM recommend_user WHERE user_id = #{userId}")
    void delete(int userId);

    @Select("SELECT * FROM recommend_user WHERE user_id = #{userId}")
    RecommendUser getUserById(int userId);
}
