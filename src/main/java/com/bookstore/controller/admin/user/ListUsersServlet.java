package com.bookstore.controller.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Users;
import com.bookstore.service.UserServices;

@WebServlet("/admin/list_users")
public class ListUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListUsersServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // doGet 메서드는 HTTP GET 요청을 처리하는 메서드입니다.

        // 사용자 서비스를 생성하고 사용자 목록을 요청합니다.
        UserServices userServices = new UserServices(request, response);
        
        // listUser 메서드를 호출하여 사용자 목록을 가져옵니다.
        userServices.listUser();
        
        //아래 부분  userService로 이동
//        public void listUser(String message) throws ServletException, IOException {
//            List<Users> listUsers = userDAO.listAll();
//
//            request.setAttribute("listUsers", listUsers);
//
//            if (message != null) {
//                request.setAttribute("message", message);
//            }
//
//            String listPage = "user_list.jsp";
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
//
//            requestDispatcher.forward(request, response);
//        }
    }
}
