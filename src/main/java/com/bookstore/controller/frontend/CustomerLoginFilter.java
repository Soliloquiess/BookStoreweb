//package com.bookstore.controller.frontend;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@WebFilter("/*") // 모든 요청에 대해 필터 적용
//public class CustomerLoginFilter implements Filter {
//	private static final String[] loginRequiredURLs = {
//			"/view_profile", "/edit_profile", "/update_profile", "/write_review",
//			"/checkout", "/place_order", "/view_orders", "/show_order_detail"
//	};
//
//	public CustomerLoginFilter() {
//	}
//
//	public void destroy() {
//		// 필터 해제 시 호출되는 메서드
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpSession session = httpRequest.getSession(false); // 세션 가져오기
//		
//		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()); // 요청 경로
//		
//		// 관리자 페이지 요청일 경우 필터 적용하지 않고 다음 필터로 요청 전달
//		if (path.startsWith("/admin/")) {
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		// 로그인 여부 확인
//		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;
//		
//		String requestURL = httpRequest.getRequestURL().toString(); // 요청 URL
//		
//		// 로그인 여부 및 요청 URL 출력 (디버깅 용도)
//		System.out.println("Path: " + path);
//		System.out.println("loggedIn: " + loggedIn);
//		
//		// 로그인이 필요한 페이지에 접근하면서 로그인되어 있지 않은 경우 로그인 페이지로 리다이렉트
//		if (!loggedIn && isLoginRequired(requestURL)) {
//			String queryString = httpRequest.getQueryString();
//			String redirectURL = requestURL;
//			
//			if (queryString != null) {
//				redirectURL = redirectURL.concat("?").concat(queryString);
//			}
//			
//			session.setAttribute("redirectURL", redirectURL);
//			
//			String loginPage = "frontend/login.jsp";
//			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
//			dispatcher.forward(request, response);
//		} else {
//			chain.doFilter(request, response); // 다음 필터로 요청 전달
//		}
//	}
//
//	// 요청 URL이 로그인이 필요한 URL인지 확인
//	private boolean isLoginRequired(String requestURL) {
//		for (String loginRequiredURL : loginRequiredURLs) {
//			if (requestURL.contains(loginRequiredURL)) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//	
//	public void init(FilterConfig fConfig) throws ServletException {
//		// 필터 초기화 시 호출되는 메서드
//	}
//}
