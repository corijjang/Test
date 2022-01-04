package com.kh.ex01;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ex01.service.MemberService;
import com.kh.ex01.vo.LoginDto;
import com.kh.ex01.vo.MemberVo;

@Controller
public class HomeController {
	
	@Inject
	private MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/board/list_all";
	}
	
	@RequestMapping(value = "/login_form", method = RequestMethod.GET)
	public String login_form() {
		return "login_form";
	}
	
	@RequestMapping(value = "/checkDupId/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public String checkDupId(@PathVariable("userid") String userid) {
		System.out.println("userid:" + userid);
		int count = memberService.checkDupId(userid);
		if (count == 0) {
			return "available";
		}
		return "used";
	}
	
	@RequestMapping(value = "/login_run", method = RequestMethod.POST)
	public String loginRun(LoginDto loginDto, RedirectAttributes rttr,
						   HttpSession session) {
		System.out.println("HomeController, loginRun, loginDto: " + loginDto);
		MemberVo memberVo = memberService.login(
				loginDto.getUserid(), loginDto.getUserpw());
		System.out.println("HomeController, loginRun, memberVo: " + memberVo);
		if (memberVo == null) {
			rttr.addFlashAttribute("msg", "fail");
			return "redirect:/login_form";
		} else {
			session.setAttribute("memberVo", memberVo);
			String targetLocation = (String)session.getAttribute("targetLocation");
			session.removeAttribute("targetLocation");
			if (targetLocation == null) {
				return "redirect:/board/list_all";
			} else {
				return "redirect:" + targetLocation;
			}
			
		}
		
	}
}
