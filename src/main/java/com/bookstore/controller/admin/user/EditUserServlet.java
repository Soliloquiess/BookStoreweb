package com.bookstore.controller.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UserServices;

@WebServlet("/admin/edit_user") // 서블릿 매핑
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditUserServlet() {
		super();
	}

	// HTTP GET 요청 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// UserServices를 사용하여 사용자 편집 로직 실행
		UserServices userServices = new UserServices(request, response); // UserServices 객체 생성
		userServices.editUser(); // 사용자 편집 메서드 호출
	}
}
