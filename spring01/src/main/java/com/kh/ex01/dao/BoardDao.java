package com.kh.ex01.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.ex01.vo.BoardVo;
import com.kh.ex01.vo.PagingDto;

@Repository
public class BoardDao {
	private static final String NAMESPACE = "com.kh.ex01.mappers.board.";
	
	@Inject
	private SqlSession sqlSession;
	
	public void getDate() {
		Object obj = sqlSession.selectOne(NAMESPACE + "getDate");
		System.out.println(obj);
	}
	
	public List<BoardVo> selectAll(PagingDto pagingDto) {
		System.out.println("BoardDao, selectAll, pagingDto:" + pagingDto);
		List<BoardVo> list = sqlSession.selectList(NAMESPACE + "selectAll", pagingDto);
		return list;
	}
	
	public void insertBoard(BoardVo boardVo) {
		sqlSession.insert(NAMESPACE + "insertBoard", boardVo);
	}
	
	public BoardVo getBoard(int bno) {
		BoardVo boardVo = sqlSession.selectOne(NAMESPACE + "getBoard", bno);
		return boardVo;
	}
	
	public String[] getFilenames(int bno) {
		List<String> fileNames = sqlSession.selectList(
				NAMESPACE + "getFilenames", bno);
		String[] arr = new String[fileNames.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = fileNames.get(i);
		}
		return arr;
	}
	
	public void deleteBoard(int bno) {
		sqlSession.delete(NAMESPACE + "deleteBoard", bno);
	}
	
	public void modifyBoard(BoardVo boardVo) {
		sqlSession.update(NAMESPACE + "modifyBoard", boardVo);
	}
	
	public int getCount(PagingDto pagingDto) {
		int count = sqlSession.selectOne(NAMESPACE + "getCount", pagingDto);
		return count;
	}
	
	public void updateViewcnt(int bno) {
		sqlSession.update(NAMESPACE + "updateViewcnt", bno);
	}
	
	public void insertReply(BoardVo boardVo) {
		sqlSession.insert(NAMESPACE + "insertReply", boardVo);
	}
	
	public void updateReSeq(BoardVo boardVo) {
		sqlSession.update(NAMESPACE + "updateReSeq", boardVo);
	}
	
	public void updateCommentCnt(int bno, int num) {
		Map<String, Integer> map = new HashMap<>();
		map.put("bno", bno);
		map.put("num", num);
		sqlSession.update(NAMESPACE + "updateCommentCnt", map);
	}
	
	public int getBnoNextVal() {
		int bno = sqlSession.selectOne(NAMESPACE + "getBnoNextVal");
		return bno;
	}
	
	public void insertAttach(String file_name, int bno) {
		Map<String, Object> map = new HashMap<>();
		map.put("file_name", file_name);
		map.put("bno", bno);
		sqlSession.insert(NAMESPACE + "insertAttach", map);
	}
	
	public void deleteAttach(int bno) {
		sqlSession.delete(NAMESPACE + "deleteAttach", bno);
	}
	
	
}
