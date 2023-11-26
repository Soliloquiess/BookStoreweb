package com.bookstore.controller.admin.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CategoryServices;

@WebServlet("/admin/list_category") // 서블릿 매핑
public class ListCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListCategoryServlet() {
		super();
	}

	// HTTP GET 요청 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("List Category"); // 브라우저에 "List Category" 출력

		// CategoryServices를 사용하여 카테고리 목록을 가져와 출력
		CategoryServices categoryServices = new CategoryServices(request, response); // CategoryServices 객체 생성
		categoryServices.listCategory(); // 카테고리 목록 가져오는 메서드 호출
	}
}
