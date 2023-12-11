package com.bookstore.controller.admin.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ReviewServices;

@WebServlet("/admin/delete_review")
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteReviewServlet() {
	    super();
	    // HttpServlet 클래스의 기본 생성자를 호출합니다.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // HTTP GET 요청을 처리하기 위한 메서드입니다.

	    ReviewServices reviewServices = new ReviewServices(request, response);
	    // ReviewServices 객체를 생성하고, 해당 객체에 요청(request)과 응답(response) 객체를 전달합니다.

	    reviewServices.deleteReview();
	    // ReviewServices 클래스의 deleteReview() 메서드를 호출하여 리뷰 삭제를 수행합니다.
	}


}
