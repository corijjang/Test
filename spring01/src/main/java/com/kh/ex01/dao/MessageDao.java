package com.kh.ex01.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.ex01.vo.MessageVo;

@Repository
public class MessageDao {
	private static final String NAMESPACE = "com.kh.ex01.mappers.message.";
	
	@Inject
	private SqlSession sqlSession;
	
	public void insertMessage(MessageVo messageVo) {
		sqlSession.insert(NAMESPACE + "insertMessage", messageVo);
	}
}
