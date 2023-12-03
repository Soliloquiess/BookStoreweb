package com.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

// 서블릿 매핑: 관리자가 책을 생성하는 관련 요청을 처리하는 서블릿입니다.
@WebServlet("/admin/create_book")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10,	// 데이터를 버퍼링하는 파일 크기 임계값 (10 KB)
		maxFileSize = 1024 * 300,		// 업로드된 파일 허용 최대 크기 (300 KB)
		maxRequestSize = 1024 * 1024	// 허용되는 최대 요청 크기 (1 MB)
)
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateBookServlet() {
	}

	// 책을 생성하기 위한 HTTP POST 요청 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청과 응답 객체를 사용하여 BookServices를 초기화하여 책 관련 작업을 처리합니다.
		BookServices bookServices = new BookServices(request, response);
		// 책을 생성하는 메서드를 호출합니다.
		bookServices.createBook();
	}

}
