package com.kh.ex01.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController2 {

	@RequestMapping(value="/doG", method=RequestMethod.GET)
	public void doG() {
		// doG.jsp
	}
	
	@RequestMapping(value="/doH", method=RequestMethod.GET)
	public String doH() {
		return "redirect:/doG";
	}
	
	@RequestMapping(value="/doI", method=RequestMethod.GET)
	public void doI() {
		// doI.jsp
	}
	
	@RequestMapping(value="/doJ", method=RequestMethod.GET)
	public String doJ(HttpSession session) {
		// session - 브라우저 단위로 저장 되는 데이터(서버)-JSESSIONID
		session.setAttribute("msg", "success");
		return "redirect:/doI";
	}
	
	@RequestMapping(value="/doK", method=RequestMethod.GET)
	public void doK() {
		// doK.jsp
	}
	
	@RequestMapping(value="/doL", method=RequestMethod.GET)
	public String doL(RedirectAttributes rttr) {
		// rttr: 리다이렉트시 데이터를 저장할 바구니
		rttr.addFlashAttribute("msg", "success");
		// -> 한번 쓰고 버릴 용도
		return "redirect:/doK";
	}
}
