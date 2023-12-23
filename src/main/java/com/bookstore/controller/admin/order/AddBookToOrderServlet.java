package com.bookstore.controller.admin.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBookToOrderServlet() {
	    super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // HTTP POST 요청에서 bookId와 quantity 매개변수 값을 가져옴
	    int bookId = Integer.parseInt(request.getParameter("bookId"));
	    int quantity = Integer.parseInt(request.getParameter("quantity"));
	    
	    // BookDAO 객체를 생성하여 bookId에 해당하는 Book 정보를 가져옴
	    BookDAO bookDao = new BookDAO();
	    Book book = bookDao.get(bookId);
	    
	    // 현재 세션에서 "order" 속성을 통해 BookOrder 객체를 가져옴
	    HttpSession session = request.getSession();
	    BookOrder order = (BookOrder) session.getAttribute("order");
	    
	    // 주문 상품의 소계 계산
	    float subtotal = quantity * book.getPrice();
	    
	    // 새로운 주문 상세 정보를 생성하고 값을 설정함
	    OrderDetail orderDetail = new OrderDetail();
	    orderDetail.setBook(book);
	    orderDetail.setQuantity(quantity);
	    orderDetail.setSubtotal(subtotal);
	    
	    // 주문의 총액을 업데이트
	    float newTotal = order.getTotal() + subtotal;
	    order.setTotal(newTotal);
	    
	    // 주문에 새로운 상세 정보를 추가함
	    order.getOrderDetails().add(orderDetail);
	    
	    // 요청 속성에 Book 객체를 설정함
	    request.setAttribute("book", book);
	    // 세션에 NewBookPendingToAddToOrder 속성을 설정하여 새로운 책이 주문에 추가될 것임을 표시함
	    session.setAttribute("NewBookPendingToAddToOrder", true);
	    
	    // 결과 페이지를 add_book_result.jsp로 설정하고 해당 페이지로 포워딩함
	    String resultPage = "add_book_result.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
	    dispatcher.forward(request, response);
	    
	    // 이 Java Servlet 코드는 HTTP POST 요청을 처리합니다. 요청에서 bookId와 quantity 매개변수 값을 가져와서 Book 객체를 생성하고, 현재 세션에서 주문 정보를 가져옵니다. 그 후, 주문에 대한 새로운 상세 정보를 만들고 주문의 총액을 업데이트한 후에 새로운 상세 정보를 주문에 추가합니다. 마지막으로, 결과 페이지를 설정하고 해당 페이지로 요청을 포워딩합니다.	    
	}
}
