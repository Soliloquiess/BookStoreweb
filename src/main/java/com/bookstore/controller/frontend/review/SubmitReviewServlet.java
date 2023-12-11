package com.bookstore.controller.frontend.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ReviewServices;

@WebServlet("/submit_review")
public class SubmitReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 직렬화된 데이터의 버전을 나타내는 정적 상수를 정의합니다.

	public SubmitReviewServlet() {
	    super();
	    // HttpServlet 클래스의 기본 생성자를 호출합니다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // HTTP POST 요청을 처리하는 메서드입니다.

	    ReviewServices reviewServices = new ReviewServices(request, response);
	    // ReviewServices 객체를 생성하고, 요청(request)과 응답(response) 객체를 전달합니다.

	    reviewServices.submitReview();
	    // ReviewServices 클래스의 submitReview() 메서드를 호출하여 리뷰를 제출하는 작업을 수행합니다.
	}

}
