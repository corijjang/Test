package com.kh.ex01.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDao {
	private static final String NAMESPACE = "com.kh.ex01.mappers.point.";
	
	@Inject
	private SqlSession sqlSession;
	
	public void insertPoint(String userid, int point_score, String point_code) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("point_score", point_score);
		paramMap.put("point_code", point_code);
		sqlSession.insert(NAMESPACE + "insertPoint", paramMap);
	}
	
}
