package com.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Review;

@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; // 직렬화를 위한 시리얼 버전 ID

	public AdminHomeServlet() {
	    super(); // 상위 클래스(부모 클래스)의 생성자를 호출합니다.
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // POST 요청일 경우, doGet 메서드를 호출하여 요청을 처리합니다.
	    doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String homepage = "index.jsp"; // 'homepage'이라는 이름의 문자열 변수를 선언하고 "index.jsp" 값을 할당합니다.
	    System.out.println("admin home servlet"); // 콘솔에 "admin home servlet"을 출력합니다.
	    RequestDispatcher dispatcher = request.getRequestDispatcher(homepage); // 'index.jsp' 페이지에 대한 요청 디스패처를 가져옵니다.
	    dispatcher.forward(request, response); // 요청과 응답 객체를 'index.jsp' 페이지로 전달합니다.
	}

	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		UserDAO userDao = new UserDAO();
//		OrderDAO orderDao = new OrderDAO();
//		ReviewDAO reviewDao = new ReviewDAO();
//		BookDAO bookDao = new BookDAO();
//		CustomerDAO customerDao = new CustomerDAO();
//		
//		List<BookOrder> listMostRecentSales = orderDao.listMostRecentSales();
//		List<Review> listMostRecentReviews = reviewDao.listMostRecent();
//		
//		long totalUsers = userDao.count();
//		long totalBooks = bookDao.count();
//		long totalCustomers = customerDao.count();
//		long totalReviews = reviewDao.count();
//		long totalOrders = orderDao.count();
//		
//		request.setAttribute("listMostRecentSales", listMostRecentSales);
//		request.setAttribute("listMostRecentReviews", listMostRecentReviews);
//		
//		request.setAttribute("totalUsers", totalUsers);
//		request.setAttribute("totalBooks", totalBooks);
//		request.setAttribute("totalCustomers", totalCustomers);
//		request.setAttribute("totalReviews", totalReviews);
//		request.setAttribute("totalOrders", totalOrders);
//		
//		String homepage = "index.jsp";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
//		dispatcher.forward(request, response);
//	}

}
