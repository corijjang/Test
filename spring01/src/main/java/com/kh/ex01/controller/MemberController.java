package com.kh.ex01.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.ex01.service.MemberService;
import com.kh.ex01.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	// 회원 가입 폼
	@RequestMapping(value="/regist_form", method=RequestMethod.GET)
	public String memberRegistFrom() {
		return "member/regist_form";
	}
	
	// 회원 가입 처리
	@RequestMapping(value="/regist_run", method=RequestMethod.POST)
	public String memberRegistRun(MemberVo memberVo) {
		System.out.println(
				"MemberController, memberRegistRun, memberVo:" + memberVo);
		memberService.insertMember(memberVo);
		return "redirect:/member/regist_form";
	}
	
	// 회원 목록
	@RequestMapping(value="/list_all", method=RequestMethod.GET)
	public String memberListAll(Model model) {
		List<MemberVo> list = memberService.selectAll();
		model.addAttribute("list", list);
		return "/member/list_all";
	}
	
	
}
