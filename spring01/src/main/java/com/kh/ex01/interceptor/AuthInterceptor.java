package com.kh.ex01.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.ex01.vo.MemberVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("memberVo");
		if (memberVo == null) {
			// 로그인하기전 요청했던 경로가 있었다면 로그인 후에 그쪽으로 이동
			String uri = request.getRequestURI();
			System.out.println("AuthInterceptor, preHandle, uri: " + uri);
			String queryString = request.getQueryString();
			System.out.println("AuthInterceptor, preHandle, queryString: " + queryString);
			String targetLocation = uri + "?" + queryString;
			session.setAttribute("targetLocation", targetLocation);
			response.sendRedirect("/login_form");
			return false; // 사용자의 실제 요청을 처리하지 않음
		}
		return true; 
	}
}
