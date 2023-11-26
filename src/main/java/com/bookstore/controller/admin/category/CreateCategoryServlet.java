package com.bookstore.controller.admin.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CategoryServices;

@WebServlet("/admin/create_category") // 서블릿 매핑
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateCategoryServlet() {
		// 기본 생성자
	}

	// HTTP POST 요청 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// CategoryServices를 사용하여 새 카테고리를 생성하는 로직 실행
		CategoryServices categoryServices = new CategoryServices(request, response); // CategoryServices 객체 생성
		categoryServices.createCategory(); // 카테고리 생성 메서드 호출
	}
}
