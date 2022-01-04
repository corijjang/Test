package com.kh.ex01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex01.dao.BoardDao;
import com.kh.ex01.vo.BoardVo;
import com.kh.ex01.vo.PagingDto;

@Service
public class BoardService {

	@Inject
	private BoardDao boardDao;
	
	public List<BoardVo> selectAll(PagingDto pagingDto) {
		List<BoardVo> list = boardDao.selectAll(pagingDto);
		return list;
	}
	
	@Transactional
	public void insertBoard(BoardVo boardVo) {
		int bno = boardDao.getBnoNextVal(); // seq_bno.nextval
		boardVo.setBno(bno);
		boardDao.insertBoard(boardVo); // tbl_board
		String[] files = boardVo.getFiles();
		if (files != null && files.length > 0) {
			for (String file_name : files) {
				boardDao.insertAttach(file_name, bno); // tbl_attach
			}
		}
		
	}
	
	public BoardVo getBoard(int bno) {
		boardDao.updateViewcnt(bno);
		BoardVo boardVo = boardDao.getBoard(bno);
		String[] filenames = boardDao.getFilenames(bno);
		boardVo.setFiles(filenames);
		return boardVo;
	}
	
	@Transactional
	public String[] deleteBoard(int bno) {
		String[] filenames = boardDao.getFilenames(bno); // upload 폴더의 파일 삭제용
		boardDao.deleteAttach(bno); // 첨부파일 데이터 삭제(tbl_attach)
		boardDao.deleteBoard(bno); // 게시글 삭제(tbl_board)
		return filenames;
	}
	
	public void modifyBoard(BoardVo boardVo) {
		boardDao.modifyBoard(boardVo);
	}
	
	public int getCount(PagingDto pagingDto) {
		int count = boardDao.getCount(pagingDto);
		return count;
	}
	
	@Transactional
	public void insertReply(BoardVo boardVo) {
		boardDao.updateReSeq(boardVo);
		System.out.println("BoardService, insertReply, update....");
		boardDao.insertReply(boardVo);
		System.out.println("BoardService, insertReply, insert....");
	}
}
