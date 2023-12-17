package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; // 직렬화 버전 UID로 객체 직렬화 시 사용되는 ID

	public AddBookToCartServlet() {
	    super(); // 부모 클래스의 기본 생성자 호출
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    Integer bookId = Integer.parseInt(request.getParameter("book_id")); // 요청 파라미터에서 book_id를 가져와 Integer로 변환

	    Object cartObject = request.getSession().getAttribute("cart"); // 세션에서 "cart" 속성값 가져오기

	    ShoppingCart shoppingCart = null; // ShoppingCart 객체 생성을 위한 변수 선언

	    // 세션에 "cart" 속성이 존재하고 그 값이 ShoppingCart의 인스턴스인지 확인
	    if (cartObject != null && cartObject instanceof ShoppingCart) {
	        shoppingCart = (ShoppingCart) cartObject; // 형변환하여 기존의 ShoppingCart 객체 할당
	    } else {
	        shoppingCart = new ShoppingCart(); // "cart" 속성이 없거나 값이 ShoppingCart의 인스턴스가 아니면 새 ShoppingCart 객체 생성
	        request.getSession().setAttribute("cart", shoppingCart); // 생성된 객체를 세션에 "cart" 속성으로 설정
	    }

	    BookDAO bookDAO = new BookDAO(); // BookDAO 객체 생성
	    Book book = bookDAO.get(bookId); // 주어진 bookId에 해당하는 Book 객체 가져오기

	    shoppingCart.addItem(book); // 장바구니에 Book 객체 추가

	    String cartPage = request.getContextPath().concat("/view_cart"); // 장바구니 페이지 경로 설정
	    response.sendRedirect(cartPage); // 클라이언트로 장바구니 페이지로 리다이렉트
	}

}
