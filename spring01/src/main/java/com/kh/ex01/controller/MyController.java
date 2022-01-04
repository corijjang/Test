package com.kh.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

	// localshost/myHome(get)
	@RequestMapping(value="/myHome", method=RequestMethod.GET)
	public String myHome() {
		return "myHome";
	}
}
