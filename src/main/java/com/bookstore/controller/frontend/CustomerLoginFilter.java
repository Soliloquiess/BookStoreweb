package com.bookstore.controller.frontend;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*") // 모든 요청에 대해 필터 적용
public class CustomerLoginFilter implements Filter {
	private static final String[] loginRequiredURLs = {
			"/view_profile", "/edit_profile", "/update_profile", "/write_review",
			"/checkout", "/place_order", "/view_orders", "/show_order_detail"
	};
	
//위 코드는 Java에서 사용되는 loginRequiredURLs라는 이름의 String 배열을 정의하는 부분입니다. 각 문자열은 사용자가 로그인해야 하는 URL 경로를 나타냅니다. 이 URL들은 사용자가 로그인한 상태에서만 접근할 수 있는 페이지들로 추정됩니다.

	public CustomerLoginFilter() {
	}

	public void destroy() {
		// 필터 해제 시 호출되는 메서드
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	//FilterChain은 서블릿 필터에서 사용되는 인터페이스로, 필터 체인 내에서 다음에 적용될 필터를 호출하는데 사용됩니다.
//	필터 체인은 여러 필터가 연속적으로 적용되어야 하는 경우, 요청에 대한 전처리 및 후처리 작업을 위해 사용됩니다. 각각의 필터는 doFilter() 메서드를 호출하여 요청을 다음 필터로 전달하거나, 체인의 끝에 도달했을 때 실제 서블릿이나 JSP로 요청을 전달합니다.
//	FilterChain 객체는 doFilter() 메서드를 통해 다음 필터로 요청을 전송하는 역할을 합니다. 즉, chain.doFilter(request, response)를 호출하면 현재 필터의 작업을 완료하고, 다음 필터로 제어를 넘기거나, 체인의 끝에 도달했을 때 요청을 실제 서블릿이나 JSP로 전달합니다.
//	이것은 필터 체인 내에서 다음으로 처리해야 할 필터 또는 리소스로 제어를 전달하는 메커니즘을 담당합니다.
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false); // 세션 가져오기
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()); // 요청 경로
		
		// 관리자 페이지 요청일 경우 필터 적용하지 않고 다음 필터로 요청 전달
		if (path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		
		// 로그인 여부 확인
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;
		
		String requestURL = httpRequest.getRequestURL().toString(); // 요청 URL
		
		// 로그인 여부 및 요청 URL 출력 (디버깅 용도)
		System.out.println("Path: " + path);
		System.out.println("loggedIn: " + loggedIn);
		
		// 로그인이 필요한 페이지에 접근하면서 로그인되어 있지 않은 경우 로그인 페이지로 리다이렉트
		if (!loggedIn && isLoginRequired(requestURL)) {
			String queryString = httpRequest.getQueryString();
			// 현재 HTTP 요청의 쿼리 문자열을 가져오는 부분

			String redirectURL = requestURL;
			// 요청된 URL을 저장할 변수를 현재 요청된 URL(requestURL)로 초기화

			if (queryString != null) {
			    // 쿼리 문자열이 존재하는 경우에 실행
			    redirectURL = redirectURL.concat("?").concat(queryString);
			    // 요청된 URL 뒤에 쿼리 문자열을 붙여서 새로운 redirectURL을 만듦
			}

			session.setAttribute("redirectURL", redirectURL);
			// 세션에 redirectURL 값을 저장하는 부분

			String loginPage = "frontend/login.jsp";
			// 로그인 페이지의 경로를 저장하는 부분

			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
			// 로그인 페이지로의 요청을 처리하기 위한 Dispatcher를 생성

			dispatcher.forward(request, response);
			// 새로운 요청(request)과 응답(response)를 전달하여 로그인 페이지로 포워딩하는 부분

		} else {
			chain.doFilter(request, response); // 다음 필터로 요청 전달
//			chain.doFilter(request, response)는 필터 체인 내에서 현재 필터 이후의 필터로 제어를 전달하는 역할을 합니다.
//			서블릿 필터(Filter)는 클라이언트의 요청이 서블릿이나 JSP에 도달하기 전에 그 요청을 가로채서 필터에서 지정한 작업을 수행할 수 있도록 해줍니다. 여러 개의 필터가 체인 형태로 연결될 수 있는데, chain.doFilter(request, response)는 현재 필터에서의 작업을 마치고 그 다음 필터로 제어를 넘기는 역할을 합니다.
//			즉, 이 메서드를 호출하면 현재 필터의 작업이 완료되고, 체인에 속한 다음 필터로 제어가 전달됩니다. 만약 현재 필터가 체인의 마지막 필터라면, 요청은 이후에 등록된 서블릿이나 JSP로 전달됩니다.
			
		}
	}

	// 요청 URL이 로그인이 필요한 URL인지 확인
	private boolean isLoginRequired(String requestURL) {
		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// 필터 초기화 시 호출되는 메서드
	}
}
