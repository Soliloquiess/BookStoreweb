package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view_cart") // "/view_cart" URL 패턴으로 이 서블릿을 매핑한다.

public class ViewCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // 직렬화 버전 UID로 객체 직렬화 시 사용되는 ID

    public ViewCartServlet() {
        super(); // 부모 클래스의 기본 생성자 호출
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Object cartObject = request.getSession().getAttribute("cart"); // 세션에서 "cart" 속성값 가져오기
        
        if (cartObject == null) { // 장바구니 객체가 세션에 없을 경우
            ShoppingCart shoppingCart = new ShoppingCart(); // 새로운 ShoppingCart 객체 생성
            request.getSession().setAttribute("cart", shoppingCart); // 세션에 "cart" 속성으로 설정
        }   

        String cartPage = "frontend/shopping_cart.jsp"; // 장바구니 페이지 경로
        RequestDispatcher dispatcher = request.getRequestDispatcher(cartPage); // 요청을 위임할 Dispatcher 생성
        dispatcher.forward(request, response); // 현재 요청과 응답을 장바구니 페이지로 전달하여 표시
    }
}
	 