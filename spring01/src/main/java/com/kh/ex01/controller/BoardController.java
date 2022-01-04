package com.kh.ex01.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ex01.service.BoardService;
import com.kh.ex01.util.MyFileUploadUtil;
import com.kh.ex01.vo.BoardVo;
import com.kh.ex01.vo.PagingDto;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final String UPLOAD_PATH = "D:/upload";
	
	@Inject
	private BoardService boardService;

	// 글 전체 목록(/board/list_all)
	@RequestMapping(value="/list_all", method=RequestMethod.GET)
	public String boardListAll(Model model, PagingDto pagingDto) {
		
		int count = boardService.getCount(pagingDto);
		pagingDto.setCount(count);
		pagingDto.setPageInfo();
		System.out.println("BoardController, boardListAll, pagingDto:" + pagingDto);
		List<BoardVo> list = boardService.selectAll(pagingDto);
		model.addAttribute("list", list);
		model.addAttribute("pagingDto", pagingDto);
		return "board/list_all";
	}
	
	// 글쓰기 폼(/board/regist_form)
	@RequestMapping(value="/regist_form", method=RequestMethod.GET)
	public String boardRegistForm() {
		return "board/regist_form";
	}
	
	// 글쓰기 처리(/board/regist_run)
	@RequestMapping(value="/regist_run", method=RequestMethod.POST)
	public String boardRegistRun(BoardVo boardVo, RedirectAttributes rttr) {
		System.out.println("BoardController, boardRegistRun, boardVo:" + boardVo);
		boardService.insertBoard(boardVo);
		rttr.addFlashAttribute("message", "regist_success");
		return "redirect:/board/list_all";
	}
	
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public String boardContent(int bno, PagingDto pagingDto, Model model) {
		System.out.println("BoardController, boardContent, bno:" + bno);
		BoardVo boardVo = boardService.getBoard(bno);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("pagingDto", pagingDto);
		return "board/content";
	}
	
	// 삭제처리
	@RequestMapping(value="/deleteBoard", method=RequestMethod.GET)
	public String deleteBoard(int bno, PagingDto pagingDto, RedirectAttributes rttr) {
		System.out.println("BoardController, deleteBoard, pagingDto:" + pagingDto);
		String[] filenames = boardService.deleteBoard(bno);
		for (String filename : filenames) {
			MyFileUploadUtil.deleteFile(UPLOAD_PATH + filename);
		}
		rttr.addFlashAttribute("message", "delete_success");
		return "redirect:/board/list_all?page=" + pagingDto.getPage() +
				"&perPage=" + pagingDto.getPerPage() +
				"&searchType=" + pagingDto.getSearchType() +
				"&keyword=" + pagingDto.getKeyword();
	}
	
	// 수정 처리
	@RequestMapping(value="/modify_run", method=RequestMethod.POST)
	public String modifyBoard(BoardVo boardVo, PagingDto pagingDto, 
							  RedirectAttributes rttr) {
		System.out.println("BoardController, modifyBoard, boardVo:" + boardVo);
		System.out.println("BoardController, modifyBoard, pagingDto:" + pagingDto);
		boardService.modifyBoard(boardVo);
		rttr.addFlashAttribute("message", "modify_success");
		return "redirect:/board/content?bno=" + boardVo.getBno() + 
				"&page=" + pagingDto.getPage() + 
				"&perPage=" + pagingDto.getPerPage() +
				"&searchType=" + pagingDto.getSearchType() +
				"&keyword=" + pagingDto.getKeyword();
	}
	
	// 답글 폼
	@RequestMapping(value="/reply_form", method=RequestMethod.GET)
	public String replyForm(/*@ModelAttribute*/ BoardVo boardVo) {
		return "board/reply_form";
	}
	
	// 답글 처리
	@RequestMapping(value="/reply_run", method=RequestMethod.POST)
	public String replyRun(BoardVo boardVo) {
		System.out.println("BoardController, replyRun, boardVo:" + boardVo);
		boardService.insertReply(boardVo);
		return "redirect:/board/list_all";
	}
	
}
