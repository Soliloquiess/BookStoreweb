package com.bookstore.controller.admin.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ReviewServices;

@WebServlet("/admin/edit_review")
public class EditReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditReviewServlet() {
	    super();
	    // HttpServlet 클래스의 기본 생성자를 호출합니다.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // HTTP GET 요청을 처리하는 메서드입니다.

	    ReviewServices reviewServices = new ReviewServices(request, response);
	    // ReviewServices 객체를 생성하고, 요청(request)과 응답(response) 객체를 전달합니다.

	    reviewServices.editReview();
	    // ReviewServices 클래스의 editReview() 메서드를 호출하여 리뷰를 수정하는 작업을 수행합니다.
	}


}
