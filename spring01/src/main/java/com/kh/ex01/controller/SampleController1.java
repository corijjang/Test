package com.kh.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.ex01.vo.ProductVo;

@Controller
public class SampleController1 {
	
	// 반환타입이 void인 경우 - 요청경로.jsp 로 포워드

	// get방식으로 localhost/doA 요청이 들어오면 doA() 메서드가 실행
	@RequestMapping(value="/doA", method=RequestMethod.GET)
	public void doA() {
		System.out.println("/doA...");
	}
	
	@RequestMapping(value="/doB", method=RequestMethod.GET)
	public void doB() {
		System.out.println("/doB...");
	}
	
	// 반환 타입이 String인 경우 - return 값의 jsp로 포워드
	@RequestMapping(value="/doC", method=RequestMethod.GET)
	public String doC() {
		return "do_c";
	}
	
	
	@RequestMapping(value="/doD", method=RequestMethod.GET)
	public String doD(@ModelAttribute("msg") String msg) {
		// String msg = request.getParameter("msg")
		// request.setAttribute("msg", msg)
		// 파라미터로 전달 받은 값을 View(jsp)까지 전달
		return "do_d";
	}
	
	@RequestMapping(value="/doE", method=RequestMethod.GET)
	public String doE(Model model) {
		// Model : view로 데이터를 전달할 바구니
		// 객체 생성
		ProductVo productVo = new ProductVo("아이폰", 1000000);
		// 바구니에 데이터 담기
		model.addAttribute("productVo", productVo);
		return "do_e";
	}
	
	@RequestMapping(value="/doF", method=RequestMethod.GET)
	public String doF(Model model) {
		// Model : view로 데이터를 전달할 바구니
		// 객체 생성
		ProductVo productVo = new ProductVo("아이폰", 1000000);
		// 바구니에 데이터 담기
		// 이름을 생략 - 클래스명의 첫글자(P)를 소문자로 바꾼 이름(productVo)으로 설정
		model.addAttribute(productVo); // ("productVo", productVo)
		return "do_e";
	}
}
