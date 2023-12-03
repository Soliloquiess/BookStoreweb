package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UserServices;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // UserServices 객체 생성하여 요청과 응답 객체 전달
        UserServices userServices = new UserServices(request, response);
        // UserServices에서 로그인 처리 메서드 호출
        userServices.login();
    }

}
