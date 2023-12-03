package com.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

@WebServlet("/admin/update_book") // "/admin/update_book" URL에 해당 서블릿 매핑
@MultipartConfig(
    fileSizeThreshold = 1024 * 10,   // 10 KB
    maxFileSize = 1024 * 300,        // 300 KB
    maxRequestSize = 1024 * 1024     // 1 MB 
)
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateBookServlet() {
    }

    // HTTP POST 요청을 처리하는 메서드
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookServices bookServices = new BookServices(request, response); // BookServices 객체 생성
        bookServices.updateBook(); // 책을 업데이트하는 메서드 호출
    }
}
