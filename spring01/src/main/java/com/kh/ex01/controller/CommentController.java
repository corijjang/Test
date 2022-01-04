package com.kh.ex01.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex01.service.CommentService;
import com.kh.ex01.vo.CommentVo;

// 모든 메서드에 자동으로 @ReponseBody를 붙임
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Inject
	private CommentService commentService;

	@RequestMapping(value="/commentList/{bno}", method=RequestMethod.GET)
	public List<CommentVo> commentList(@PathVariable("bno") int bno) {
		System.out.println("CommentController, commentList, bno:" + bno);
		List<CommentVo> commentList = commentService.commentList(bno);
		return commentList;
	}
	
	@RequestMapping(value="/insertComment", method=RequestMethod.POST)
	public String insertComment(CommentVo commentVo) {
		System.out.println("CommentController, insertComment, commentVo:" + commentVo);
		commentService.insertComment(commentVo);
		return "success";
	}
	
	@RequestMapping(value="/deleteComment/{cno}/{bno}", method=RequestMethod.GET)
	public String deleteComment(@PathVariable("cno") int cno,
								@PathVariable("bno") int bno) {
		System.out.println("CommentController, deleteComment, cno:" + cno);
		commentService.deleteComment(cno, bno);
		return "success";
	}
	
	@RequestMapping(value="/updateComment", method=RequestMethod.POST)
	public String updateComment(CommentVo commentVo) {
		System.out.println("CommentController, updateComment, commentVo:" + commentVo);
		commentService.updateComment(commentVo);
		return "success";
	}
}
