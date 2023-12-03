package com.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

@WebServlet("/admin/delete_book") // 해당 서블릿을 "/admin/delete_book" URL에 매핑
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // HTTP GET 요청을 처리하는 메서드
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookServices bookServices = new BookServices(request, response); // BookServices 객체 생성
        bookServices.deleteBook(); // 책 삭제 메서드 호출
    }
}
