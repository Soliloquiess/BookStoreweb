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
	// ReviewDAO 객체를 참조하기 위한 private 변수 선언

	private HttpServletRequest request;
	// HTTP 요청 객체를 참조하기 위한 private 변수 선언

	private HttpServletResponse response;
	// HTTP 응답 객체를 참조하기 위한 private 변수 선언

	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
	    // ReviewServices 생성자 정의, HttpServletRequest와 HttpServletResponse를 매개변수로 받음
	    super();
	    // 상위 클래스(Object 클래스)의 생성자 호출

	    this.request = request;
	    // 생성자의 매개변수로 받은 request를 클래스의 request 변수에 할당

	    this.response = response;
	    // 생성자의 매개변수로 받은 response를 클래스의 response 변수에 할당

	    this.reviewDAO = new ReviewDAO();
	    // ReviewDAO 객체를 새로 생성하여 클래스의 reviewDAO 변수에 할당
	}

	public void listAllReview() throws ServletException, IOException {
	    // 모든 리뷰를 나열하는 메서드(listAllReview) 정의, ServletException과 IOException 예외 처리

	    listAllReview(null);
	    // listAllReview 메서드를 다시 호출하면서 매개변수로 null을 전달
	    // 즉, listAllReview 메서드를 오버로드한 다른 메서드를 호출하는데, 이 때 매개변수를 null로 전달하여 실행
	}

	
	public void listAllReview(String message) throws ServletException, IOException {
	    // 모든 리뷰를 나열하는 메서드(listAllReview) 정의, ServletException과 IOException 예외 처리

	    List<Review> listReviews = reviewDAO.listAll();
	    // reviewDAO를 사용하여 모든 리뷰를 가져와서 listReviews 변수에 할당

	    request.setAttribute("listReviews", listReviews);
	    // HTTP 요청에 listReviews 변수를 "listReviews"라는 이름으로 속성으로 설정하여 저장

	    if (message != null) {
	        // message가 null이 아닌 경우에 실행
	        request.setAttribute("message", message);
	        // HTTP 요청에 message 변수를 "message"라는 이름으로 속성으로 설정하여 저장
	    }

	    String listPage = "review_list.jsp";
	    // 리뷰 목록을 표시할 JSP 페이지의 경로를 listPage 변수에 저장

	    RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
	    // 요청된 JSP 페이지로의 요청을 처리하기 위한 Dispatcher를 생성

	    dispatcher.forward(request, response);
	    // 새로운 요청(request)과 응답(response)를 전달하여 리뷰 목록을 표시할 JSP 페이지로 포워딩하는 부분
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
		// HTTP 요청에 review 객체를 "review"라는 이름으로 속성으로 설정하여 저장

		String editPage = "review_form.jsp";
		// 리뷰를 편집하는 폼을 표시할 JSP 페이지의 경로를 editPage 변수에 저장

		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		// 요청된 JSP 페이지로의 요청을 처리하기 위한 Dispatcher를 생성

		dispatcher.forward(request, response);
		// 새로운 요청(request)과 응답(response)를 전달하여 리뷰를 편집할 폼을 표시할 JSP 페이지로 포워딩하는 부분
	
	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		// HTTP 요청에서 "reviewId" 매개변수를 가져와서 정수형으로 변환하여 reviewId 변수에 할당

		String headline = request.getParameter("headline");
		// HTTP 요청에서 "headline" 매개변수를 가져와서 headline 변수에 할당

		String comment = request.getParameter("comment");
		// HTTP 요청에서 "comment" 매개변수를 가져와서 comment 변수에 할당

		Review review = reviewDAO.get(reviewId);
		// reviewDAO를 사용하여 reviewId에 해당하는 Review 객체를 데이터베이스에서 가져와 review 변수에 할당

		review.setHeadline(headline);
		// 가져온 Review 객체의 제목(headline)을 HTTP 요청에서 가져온 값으로 설정

		review.setComment(comment);
		// 가져온 Review 객체의 코멘트(comment)를 HTTP 요청에서 가져온 값으로 설정

		reviewDAO.update(review);
		// 수정된 Review 객체를 데이터베이스에 업데이트하여 변경 사항을 반영

		String message = "The review has been updated successfully.";
		// 성공 메시지를 저장하는 message 변수에 메시지 할당

		listAllReview(message);
		// 모든 리뷰를 다시 가져와서 화면에 표시하는 함수(listAllReview)를 호출하고, 성공 메시지를 전달

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
		// reviewId에 해당하는 리뷰를 데이터베이스에서 삭제하는 reviewDAO의 delete 메서드 호출

		String message = "The review has been deleted successfully.";
		// 삭제 성공 메시지를 저장하는 message 변수에 메시지 할당

		listAllReview(message);
		// 삭제 후 모든 리뷰를 다시 가져와서 화면에 표시하는 listAllReview 메서드 호출하고, 성공 메시지를 전달

	}

	public void showReviewForm() throws ServletException, IOException {
	    // 리뷰 폼을 표시하는 메서드(showReviewForm) 정의, ServletException과 IOException 예외 처리

	    Integer bookId = Integer.parseInt(request.getParameter("book_id"));
	    // HTTP 요청에서 "book_id" 매개변수를 가져와서 정수형으로 변환하여 bookId 변수에 할당

	    BookDAO bookDao = new BookDAO();
	    // BookDAO 객체를 생성

	    Book book = bookDao.get(bookId);
	    // bookId에 해당하는 Book 객체를 데이터베이스에서 가져와 book 변수에 할당

	    HttpSession session = request.getSession();
	    // 현재 요청에 대한 세션 객체를 가져옴

	    session.setAttribute("book", book);
	    // 세션에 book 객체를 "book"이라는 이름으로 속성으로 설정하여 저장

	    Customer customer = (Customer) session.getAttribute("loggedCustomer");
	    // 세션에서 "loggedCustomer"라는 이름으로 저장된 Customer 객체를 가져옴

	    Review existReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
	    // 현재 고객과 bookId에 해당하는 리뷰가 있는지 확인하기 위해 reviewDAO를 사용하여 검색

	    String targetPage = "frontend/review_form.jsp";
	    // 리뷰 폼을 표시할 JSP 페이지의 경로를 targetPage 변수에 저장

	    if (existReview != null) {
	        // existReview가 null이 아닌 경우에 실행
	        request.setAttribute("review", existReview);
	        // HTTP 요청에 existReview 객체를 "review"라는 이름으로 속성으로 설정하여 저장
	        targetPage = "frontend/review_info.jsp";
	        // 리뷰 정보를 표시할 JSP 페이지로 targetPage 변수 값 변경
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
	    // 요청된 JSP 페이지로의 요청을 처리하기 위한 Dispatcher를 생성

	    dispatcher.forward(request, response);
	    // 새로운 요청(request)과 응답(response)를 전달하여 리뷰 폼 또는 리뷰 정보를 표시할 JSP 페이지로 포워딩하는 부분
	}


	public void submitReview() throws ServletException, IOException {
	    // 리뷰를 제출하는 메서드(submitReview) 정의, ServletException과 IOException 예외 처리

	    Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	    // HTTP 요청에서 "bookId" 매개변수를 가져와서 정수형으로 변환하여 bookId 변수에 할당

	    Integer rating = Integer.parseInt(request.getParameter("rating"));
	    // HTTP 요청에서 "rating" 매개변수를 가져와서 정수형으로 변환하여 rating 변수에 할당

	    String headline = request.getParameter("headline");
	    // HTTP 요청에서 "headline" 매개변수를 가져와서 headline 변수에 할당

	    String comment = request.getParameter("comment");
	    // HTTP 요청에서 "comment" 매개변수를 가져와서 comment 변수에 할당

	    Review newReview = new Review();
	    // Review 객체를 새로 생성

	    newReview.setHeadline(headline);
	    // 새로 생성한 Review 객체의 제목을 HTTP 요청에서 가져온 값으로 설정

	    newReview.setComment(comment);
	    // 새로 생성한 Review 객체의 코멘트를 HTTP 요청에서 가져온 값으로 설정

	    newReview.setRating(rating);
	    // 새로 생성한 Review 객체의 평점을 HTTP 요청에서 가져온 값으로 설정

	    Book book = new Book();
	    // Book 객체를 새로 생성

	    book.setBookId(bookId);
	    // 새로 생성한 Book 객체의 ID를 HTTP 요청에서 가져온 bookId 값으로 설정

	    newReview.setBook(book);
	    // 새로 생성한 Review 객체의 Book 속성을 위에서 생성한 Book 객체로 설정

	    Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
	    // 세션에서 "loggedCustomer"라는 이름으로 저장된 Customer 객체를 가져옴

	    newReview.setCustomer(customer);
	    // 새로 생성한 Review 객체의 Customer 속성을 위에서 가져온 Customer 객체로 설정

	    reviewDAO.create(newReview);
	    // ReviewDAO를 사용하여 새로 생성한 Review 객체를 데이터베이스에 저장

	    String messagePage = "frontend/review_done.jsp";
	    // 리뷰 제출 완료 후 보여줄 JSP 페이지의 경로를 messagePage 변수에 저장

	    RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
	    // 요청된 JSP 페이지로의 요청을 처리하기 위한 Dispatcher를 생성

	    dispatcher.forward(request, response);
	    // 새로운 요청(request)과 응답(response)를 전달하여 리뷰 제출 완료 페이지로 포워딩하는 부분
	}

}
