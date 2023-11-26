//package com.bookstore.controller.frontend;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//
//import com.bookstore.dao.CategoryDAO;
//import com.bookstore.entity.Category;
//
//@WebFilter("/*") // 모든 요청에 대해 필터 적용
//public class CommonFilter implements Filter {
//
//	private final CategoryDAO categoryDAO;
//	
//	public CommonFilter() {
//		categoryDAO = new CategoryDAO(); // CategoryDAO 객체 생성
//	}
//
//	public void destroy() {
//		// 필터 해제 시 호출되는 메서드
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//		
//		if (!path.startsWith("/admin/")) { // "/admin/"으로 시작하는 요청이 아닌 경우
//			List<Category> listCategory = categoryDAO.listAll(); // 모든 카테고리 목록을 가져옴
//			request.setAttribute("listCategory", listCategory);	// 카테고리 목록을 request 속성에 저장
//		}
//		
//		chain.doFilter(request, response); // 다음 필터로 요청 전달
//	}
//
//	public void init(FilterConfig fConfig) throws ServletException {
//		// 필터 초기화 시 호출되는 메서드
//	}
//}
