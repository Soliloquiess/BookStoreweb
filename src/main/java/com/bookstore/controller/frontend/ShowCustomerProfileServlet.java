package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CustomerServices;

@WebServlet("/view_profile")
public class ShowCustomerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCustomerProfileServlet() {
	    super(); // 부모 클래스의 기본 생성자를 호출하여 초기화
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    CustomerServices customerServices = new CustomerServices(request, response); // CustomerServices 인스턴스 생성
	    customerServices.showCustomerProfile(); // 고객 프로필을 보여주는 메서드 호출
	}

}
