package com.bookstore.controller.admin;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */

//어차피 이거 안 쓰느 서블릿(jpaDAO로 편입)
@WebServlet("/BaseServlet")
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static EntityManagerFactory entityManagerFactory; // 엔터티 매니저 팩토리를 담는 변수 선언
    private static EntityManager entityManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public BaseServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    
//
//    // 정적 초기화 블록: 엔터티 매니저 팩토리 생성
//    static {
//        entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
//    }

    @Override
    public void init() throws ServletException {
    	entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite"); // 엔터티 매니저 생성
        entityManager =entityManagerFactory.createEntityManager();

    }


    @Override
    public void destroy() {
    	entityManager.close();
    	entityManagerFactory.close();
    	
    }
}
