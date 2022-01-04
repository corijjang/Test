package com.kh.ex01.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.ex01.vo.CommentVo;

@Repository
public class CommentDao {
	private static final String NAMESPACE = "com.kh.ex01.mappers.comment.";
	
	@Inject
	private SqlSession sqlSession;
	
	public List<CommentVo> commentList(int bno) {
		List<CommentVo> list = sqlSession.selectList(
				NAMESPACE + "commentList", bno);
		return list;
	}
	
	public void insertComment(CommentVo commentVo) {
		sqlSession.insert(NAMESPACE + "insertComment", commentVo);
	}
	
	public void deleteComment(int cno) {
		sqlSession.delete(NAMESPACE + "deleteComment", cno);
	}
	
	public void updateComment(CommentVo commentVo) {
		sqlSession.update(NAMESPACE + "updateComment", commentVo);
	}
}
