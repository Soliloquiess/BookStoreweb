package com.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

@WebServlet("/admin/new_book") // "/admin/new_book" URL에 해당 서블릿 매핑
public class NewBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewBookServlet() {
    }

    // HTTP GET 요청을 처리하는 메서드
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookServices bookServices = new BookServices(request, response); // BookServices 객체 생성
        bookServices.showBookNewForm(); // 새 책 추가 폼을 보여주는 메서드 호출
    }
}
