package com.bookstore.controller.frontend.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

@WebServlet("/search")
public class SearchBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchBookServlet() {
        // 기본 생성자
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // BookServices 객체 생성 및 요청과 응답 전달
        BookServices bookServices = new BookServices(request, response);
        
        // BookServices의 search() 메서드 호출하여 책 검색 수행
        bookServices.search();
    }
}
