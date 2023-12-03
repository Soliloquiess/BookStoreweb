package com.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

@WebServlet("/admin/edit_book") // "/admin/edit_book" URL에 해당 서블릿 매핑
public class EditBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditBookServlet() {
    }

    // HTTP GET 요청을 처리하는 메서드
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookServices bookServices = new BookServices(request, response); // BookServices 객체 생성
        bookServices.editBook(); // 책 편집 메서드 호출
    }
}
