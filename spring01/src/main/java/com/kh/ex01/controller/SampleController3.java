package com.kh.ex01.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ex01.vo.ProductVo;

@Controller
public class SampleController3 {

	@RequestMapping(value="/doJson", method=RequestMethod.GET)
	public @ResponseBody ProductVo doJson() {
		ProductVo productVo = new ProductVo("아이폰", 1000000);
		return productVo;
	}
	
	@RequestMapping(value="/doJsonList", method=RequestMethod.GET)
	public @ResponseBody ArrayList<ProductVo> doJsonList() {
		ArrayList<ProductVo> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			ProductVo productVo = new ProductVo("아이폰" + i, 1000000 + i);
			list.add(productVo);
		}
		
		return list;
	}
}
