package com.bookstore.controller.frontend.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ReviewServices;

@WebServlet("/write_review")
public class WriteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 직렬화된 데이터의 버전을 나타내는 정적 상수를 정의합니다.

	public WriteReviewServlet() {
	    super();
	    // HttpServlet 클래스의 기본 생성자를 호출합니다.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // HTTP GET 요청을 처리하는 메서드입니다.

	    ReviewServices reviewServices = new ReviewServices(request, response);
	    // ReviewServices 객체를 생성하고, 요청(request)과 응답(response) 객체를 전달합니다.

	    reviewServices.showReviewForm();
	    // ReviewServices 클래스의 showReviewForm() 메서드를 호출하여 리뷰 작성 폼을 표시하는 작업을 수행합니다.
	}


}
