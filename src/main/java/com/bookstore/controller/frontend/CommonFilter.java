package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

@WebFilter("/*") // 모든 요청에 대해 필터 적용
public class CommonFilter implements Filter {

	private final CategoryDAO categoryDAO;
	
	public CommonFilter() {
		categoryDAO = new CategoryDAO(); // CategoryDAO 객체 생성
	}

	public void destroy() {
		// 필터 해제 시 호출되는 메서드
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// HTTP 요청 객체에서 요청 URI를 가져옵니다.
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
//		httpRequest.getRequestURI(): httpRequest 객체에서 현재 요청의 URI(Uniform Resource Identifier)를 가져옵니다. 이는 클라이언트가 서버로 보낸 요청에서 리소스(페이지, 이미지, 동영상 등)의 식별자인 경로를 나타냅니다.
//		httpRequest.getContextPath(): httpRequest 객체에서 컨텍스트 경로를 가져옵니다. 이는 웹 애플리케이션의 루트 경로를 의미하며, 서버에 배포된 웹 애플리케이션의 기본 경로를 나타냅니다.
//		substring(httpRequest.getContextPath().length()): getRequestURI()에서 가져온 URI에서 getContextPath()의 길이만큼을 제거하여 실제 요청된 자원에 해당하는 부분을 추출합니다. 이렇게 하면 컨텍스트 경로 이후의 요청 경로만 추출됩니다.
//		즉, 위 코드는 클라이언트가 요청한 URI에서 웹 애플리케이션의 컨텍스트 경로를 제외한 나머지 부분을 추출하여 path 변수에 저장하는 것으로 보입니다. 이렇게 추출된 path 변수는 해당 요청에 대한 실제 처리할 리소스 경로로 사용될 수 있습니다.
		
		if (!path.startsWith("/admin/")) { // "/admin/"으로 시작하는 요청이 아닌 경우
			List<Category> listCategory = categoryDAO.listAll(); // 디비에서 모든 카테고리 목록을 가져옴
			request.setAttribute("listCategory", listCategory);	// 카테고리 목록을 request 속성에 저장
		}
		
		chain.doFilter(request, response); // 다음 필터로 요청 전달
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// 필터 초기화 시 호출되는 메서드
	}
}
