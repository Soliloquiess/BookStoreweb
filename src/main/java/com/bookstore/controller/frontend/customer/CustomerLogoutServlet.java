package com.bookstore.controller.frontend.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class CustomerLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerLogoutServlet() {
	    super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // 세션에서 'loggedCustomer' 속성을 제거하여 사용자 로그아웃을 처리합니다.
	    request.getSession().removeAttribute("loggedCustomer");
	    
	    // 사용자를 로그아웃한 후, 홈 페이지로 리다이렉트합니다.
	    response.sendRedirect(request.getContextPath());
	}


}
