package com.bookstore.controller.frontend.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CustomerServices;

@WebServlet("/login")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // CustomerServices 객체를 생성하여 요청과 응답을 처리합니다.
	    CustomerServices customerServices = new CustomerServices(request, response);
	    // 로그인 페이지를 보여주는 메서드를 호출합니다.
	    customerServices.showLogin(); //customerServices 안의 showLogin 메서드 호출
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // CustomerServices 객체를 생성하여 요청과 응답을 처리합니다.
	    CustomerServices customerServices = new CustomerServices(request, response);
	    // 로그인 처리를 수행하는 메서드를 호출합니다.
	    customerServices.doLogin();
	}


}
