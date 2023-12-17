package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;

@WebServlet("/remove_from_cart") // "/remove_from_cart" URL 패턴으로 이 서블릿을 매핑한다.

public class RemoveBookFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // 직렬화 버전 UID로 객체 직렬화 시 사용되는 ID

    public RemoveBookFromCartServlet() {
        super(); // 부모 클래스의 기본 생성자 호출
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("book_id")); // 요청 파라미터에서 book_id를 가져와 Integer로 변환

        Object cartObject = request.getSession().getAttribute("cart"); // 세션에서 "cart" 속성값 가져오기

        ShoppingCart shoppingCart = (ShoppingCart) cartObject; // 형변환하여 ShoppingCart 객체로 캐스팅

        shoppingCart.removeItem(new Book(bookId)); // Book 객체를 생성하여 해당 bookId를 가진 책을 장바구니에서 제거

        String cartPage = request.getContextPath().concat("/view_cart"); // 장바구니 페이지 경로 설정
        response.sendRedirect(cartPage); // 클라이언트로 장바구니 페이지로 리다이렉트
    }
}

