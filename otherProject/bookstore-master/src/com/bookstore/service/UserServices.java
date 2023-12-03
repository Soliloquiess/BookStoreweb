package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
    private UserDAO userDAO; // UserDAO 객체를 사용하기 위한 필드 선언
    private HttpServletRequest request; // HTTP 요청 객체를 담을 필드
    private HttpServletResponse response; // HTTP 응답 객체를 담을 필드

    // 생성자: HTTP 요청, 응답 및 UserDAO 객체를 초기화
    public UserServices(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        userDAO = new UserDAO();
    }

    // 모든 사용자 목록을 가져와서 user_list.jsp로 포워딩
    public void listUser() throws ServletException, IOException {
        listUser(null);
    }

    // 사용자 목록을 가져와서 필요한 메시지와 함께 user_list.jsp로 포워딩
    public void listUser(String message) throws ServletException, IOException {
        List<Users> listUsers = userDAO.listAll(); // 모든 사용자 목록을 가져옴

        request.setAttribute("listUsers", listUsers); // 사용자 목록을 request 객체에 설정

        if (message != null) {
            request.setAttribute("message", message); // 메시지가 있을 경우 설정
        }

        String listPage = "user_list.jsp"; // 사용자 목록 페이지 경로
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage); // Dispatcher 생성

        requestDispatcher.forward(request, response); // 해당 페이지로 포워딩
    }

    // 사용자 생성
    public void createUser() throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String password = request.getParameter("password");

        Users existUser = userDAO.findByEmail(email); // 이메일로 사용자 검색

        if (existUser != null) {
            // 이미 존재하는 이메일로 사용자를 생성할 경우 메시지를 설정하고 message.jsp로 포워딩
            String message = "Could not create user. A user with email " + email + " already exists";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request, response);
        } else {
            Users newUser = new Users(email, fullName, password); // 새 사용자 생성
            userDAO.create(newUser); // 새 사용자를 데이터베이스에 추가
            listUser("New user created successfully"); // 성공 메시지와 함께 사용자 목록 페이지로 이동
        }
    }

    // 사용자 정보 수정 폼으로 이동
    public void editUser() throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id")); // 요청에서 사용자 ID 추출
        Users user = userDAO.get(userId); // 해당 ID의 사용자 정보 가져오기

        String editPage = "user_form.jsp"; // 사용자 정보 수정 페이지 경로
        request.setAttribute("user", user); // 해당 사용자 정보 설정

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage); // Dispatcher 생성
        requestDispatcher.forward(request, response); // 해당 페이지로 포워딩
    }

    // 사용자 정보 업데이트
    public void updateUser() throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String password = request.getParameter("password");

        Users userById = userDAO.get(userId);
        Users userByEmail = userDAO.findByEmail(email);

        if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
            String message = "Could not update user. User with email " + email + " already exists.";
            request.setAttribute("message", message);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Users user = new Users(userId, email, fullName, password);
            userDAO.update(user);

            String message = "User has been updated successfully";
            listUser(message);
        }
    }

    // 사용자 삭제
    public void deleteUser() throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        userDAO.delete(userId); // 해당 ID의 사용자 삭제

        String message = "User has been deleted successfully";
        listUser(message);
    }

    // 사용자 로그인 처리
    public void login() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean loginResult = userDAO.checkLogin(email, password);

        if (loginResult) {
            request.getSession().setAttribute("useremail", email);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        } else {
            String message = "Login failed!";
            request.setAttribute("message", message);

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
