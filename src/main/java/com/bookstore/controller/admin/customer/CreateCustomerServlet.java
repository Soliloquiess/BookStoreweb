package com.bookstore.controller.admin.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CustomerServices;

@WebServlet("/admin/create_customer")
public class CreateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateCustomerServlet() {
	    super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // CustomerServices 객체를 생성하여 요청과 응답을 처리합니다.
	    CustomerServices customerServices = new CustomerServices(request, response);
	    // 새로운 고객을 생성하는 메서드를 호출합니다.
	    customerServices.createCustomer();
	}

}
