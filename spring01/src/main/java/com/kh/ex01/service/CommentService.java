package com.kh.ex01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex01.dao.BoardDao;
import com.kh.ex01.dao.CommentDao;
import com.kh.ex01.vo.CommentVo;

@Service
public class CommentService {

	@Inject
	private CommentDao commentDao;
	
	@Inject
	private BoardDao boardDao;
	
	public List<CommentVo> commentList(int bno) {
		List<CommentVo> list = commentDao.commentList(bno);
		return list;
	}
	
	@Transactional
	public void insertComment(CommentVo commentVo) {
		commentDao.insertComment(commentVo);
		boardDao.updateCommentCnt(commentVo.getBno(), 1);
	}
	
	@Transactional
	public void deleteComment(int cno, int bno) {
		commentDao.deleteComment(cno);
		boardDao.updateCommentCnt(bno, -1);
	}
	
	public void updateComment(CommentVo commentVo) {
		commentDao.updateComment(commentVo);
	}
}
