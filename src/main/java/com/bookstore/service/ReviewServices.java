package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

import static com.bookstore.service.CommonUtility.*;
public class ReviewServices {
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.reviewDAO = new ReviewDAO();
	}
	
	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}
	
	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();
		
		request.setAttribute("listReviews", listReviews);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
		
	}

	public void editReview() throws ServletException, IOException {
		
//		2명 또는 관리자/관리자가 동시에 고객 리뷰를 관리하는 경우도 있습니다. 다음 시나리오를 상상해 보세요.
//		- 첫 번째 관리자가 리뷰 목록을 봅니다.
//		- 두 번째 관리자가 리뷰 목록을 봅니다.
//		- 첫 번째 관리자는 리뷰 ID 33을 삭제합니다.
//		- 두 번째 관리자는 이미 삭제된 리뷰ID 33을 수정하려고 합니다.
//		이 경우 두 번째 관리자에게 의미 있는 오류 메시지가 표시됩니다.
		// 요청(request)에서 'id' 매개변수를 가져와서 Integer로 변환합니다.
		Integer reviewId = Integer.parseInt(request.getParameter("id"));

		// DAO(Data Access Object)에서 reviewId에 해당하는 'Review' 객체를 가져옵니다.
		Review review = reviewDAO.get(reviewId);

		// 'review' 객체가 null이 아닌지 확인합니다.
		if (review != null) {		
		    // 'review' 객체를 요청 범위(request scope)의 'review' 속성으로 설정합니다.
		    request.setAttribute("review", review);		
		    
		    // 'review_form.jsp' 페이지로 요청과 응답 객체를 함께 전달하여 요청을 전달합니다.
		    forwardToPage("review_form.jsp", request, response);
		} else {
		    // 'review' 객체가 null인 경우, 해당 ID의 리뷰를 찾을 수 없음을 나타내는 오류 메시지를 만듭니다.
		    String message = "ID가 " + reviewId + "인 리뷰를 찾을 수 없습니다.";
		    
		    // 'showMessageBackend' 메서드를 사용하여 오류 메시지를 표시하고, 요청과 응답 객체를 전달합니다.
		    showMessageBackend(message, request, response);
		}

		
		request.setAttribute("review", review);
		
		String editPage = "review_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);		
	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review review = reviewDAO.get(reviewId);
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDAO.update(review);
		
		String message = "The review has been updated successfully.";
		
		listAllReview(message);
		
	}

	public void deleteReview() throws ServletException, IOException {
		//Integer reviewId = Integer.parseInt(request.getParameter("id"));
		
		// 요청(request)에서 'id' 매개변수를 가져와서 Integer로 변환합니다.
		Integer reviewId = Integer.parseInt(request.getParameter("id"));

		// DAO(Data Access Object)에서 reviewId에 해당하는 'Review' 객체를 가져옵니다.
		Review review = reviewDAO.get(reviewId);

		// 'review' 객체가 null이 아닌지 확인합니다.
		if (review != null) {
		    // 'reviewId'에 해당하는 리뷰를 DAO를 통해 삭제합니다.
		    reviewDAO.delete(reviewId);
		    
		    // 삭제가 성공했음을 나타내는 메시지를 만듭니다.
		    String message = "리뷰가 성공적으로 삭제되었습니다.";

		    // 'listAllReview' 메서드를 호출하여 모든 리뷰 목록을 다시 가져와서 메시지와 함께 표시합니다.
		    listAllReview(message);
		} else {
		    // 'review' 객체가 null인 경우, 해당 ID의 리뷰를 찾을 수 없거나 다른 관리자에 의해 이미 삭제된 것일 수 있음을 나타내는 오류 메시지를 만듭니다.
		    String message = "ID가 " + reviewId + "인 리뷰를 찾을 수 없거나 다른 관리자에 의해 이미 삭제되었을 수 있습니다.";

		    // 'showMessageBackend' 메서드를 호출하여 오류 메시지를 표시합니다.
		    showMessageBackend(message, request, response);
		}

		reviewDAO.delete(reviewId);
		
		String message = "The review has been deleted successfully.";
		
		listAllReview(message);
		
	}

	public void showReviewForm() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDao = new BookDAO();
		Book book = bookDao.get(bookId);
		
		HttpSession session = request.getSession(); 
		session.setAttribute("book", book);
		
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		Review existReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
		
		String targetPage = "frontend/review_form.jsp";
		
		if (existReview != null) {
			request.setAttribute("review", existReview);
			targetPage = "frontend/review_info.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);		
	}

	public void submitReview() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review newReview = new Review();
		newReview.setHeadline(headline);
		newReview.setComment(comment);
		newReview.setRating(rating);
		
		Book book = new Book();
		book.setBookId(bookId);
		newReview.setBook(book);
		
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);
		
		reviewDAO.create(newReview);
		
		String messagePage = "frontend/review_done.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
		
		
	}
}
