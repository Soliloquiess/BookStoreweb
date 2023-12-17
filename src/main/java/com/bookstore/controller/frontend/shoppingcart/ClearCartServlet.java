package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clear_cart")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; // 직렬화 버전 UID로 객체 직렬화 시 사용되는 ID

	public ClearCartServlet() {
	    super(); // 부모 클래스의 기본 생성자 호출
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart"); // 세션에서 "cart" 속성값 가져오기

	    cart.clear(); // 장바구니 비우기

	    String cartPage = request.getContextPath().concat("/view_cart"); // 장바구니 페이지 경로 설정
	    response.sendRedirect(cartPage); // 클라이언트로 장바구니 페이지로 리다이렉트
	}
}
