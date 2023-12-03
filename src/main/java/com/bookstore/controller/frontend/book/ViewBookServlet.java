package com.bookstore.controller.frontend.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

@WebServlet("/view_book")
public class ViewBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewBookServlet() {
        // 기본 생성자
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // BookServices 객체 생성 및 요청과 응답 전달
        BookServices bookServices = new BookServices(request, response);
        
        // BookServices의 viewBookDetail() 메서드 호출하여 특정 책의 상세 정보 조회
        bookServices.viewBookDetail();
    }
}
