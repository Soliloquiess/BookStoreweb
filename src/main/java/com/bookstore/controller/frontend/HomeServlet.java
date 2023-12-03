package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.experimental.categories.Categories;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

@WebServlet("") // 서블릿 매핑을 설정하는 어노테이션. 루트 경로("/")로 접속 시 이 서블릿이 호출됨.
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EntityManager = null;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//BookDAO bookDAO = new BookDAO(); // BookDAO 객체 생성
		
//		// 새로운 책, 베스트셀러, 가장 인기 있는 책 목록을 가져옴
//		List<Book> listNewBooks = bookDAO.listNewBooks();
//		List<Book> listBestSellingBooks = bookDAO.listBestSellingBooks();
//		List<Book> listFavoredBooks = bookDAO.listMostFavoredBooks();
//		
//		// 각 목록을 request 속성에 설정하여 JSP 페이지로 전달
//		request.setAttribute("listNewBooks", listNewBooks);
//		request.setAttribute("listBestSellingBooks", listBestSellingBooks);
//		request.setAttribute("listFavoredBooks", listFavoredBooks);
		BookDAO bookDAO = new BookDAO(); // BookDAO 객체 생성
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> listCategory = categoryDAO.listAll();
		

		// 새로운 책, 베스트셀러, 가장 인기 있는 책 목록을 가져옴
		List<Book> listNewBooks = bookDAO.listNewBooks();
		
		
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listNewBooks", listNewBooks);
		
		
		//이 경우 category 헤더부분에 추가(#52)
		
		String homepage = "frontend/index.jsp"; // 홈페이지 JSP 파일의 경로
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response); // JSP 페이지로 요청과 응답 객체 전달하여 이동
	}
}



//@WebServlet(""): 해당 서블릿 클래스를 루트 경로에 매핑시킵니다. 즉, 루트 경로("/")로 접속 시에 이 서블릿이 호출됩니다.
//doGet(): GET 요청을 처리하는 메서드입니다. 이 메서드는 새로운 책, 베스트셀러, 가장 인기 있는 책 목록을 가져옵니다.
//BookDAO bookDAO = new BookDAO();: BookDAO 클래스의 객체를 생성하여 새로운 책, 베스트셀러, 가장 인기 있는 책 목록을 가져올 수 있는 메서드를 사용할 수 있도록 합니다.
//List<Book> listNewBooks = bookDAO.listNewBooks();: BookDAO를 통해 새로운 책 목록을 가져옵니다. 마찬가지로 베스트셀러와 가장 인기 있는 책 목록도 가져옵니다
//request.setAttribute("listNewBooks", listNewBooks);: JSP 파일에서 사용할 수 있도록 각 목록을 request 객체의 속성으로 저장합니다.
//String homepage = "frontend/index.jsp";: 보여줄 홈페이지의 JSP 파일 경로를 지정합니다.
//RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);: JSP 페이지로 요청을 전달하기 위해 RequestDispatcher 객체를 생성합니다.
//dispatcher.forward(request, response);: JSP 페이지로 요청과 응답 객체를 전달하여 실제로 홈페이지를 표시하도록 이동합니다. JSP 페이지에서는 이러한 속성을 활용하여 데이터를 표시할 수 있습니다.


//package com.bookstore.controller.frontend;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.bookstore.dao.BookDAO;
//import com.bookstore.entity.Book;
//
//@WebServlet("")
//public class HomeServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    public HomeServlet() {
//        super();
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // BookDAO를 사용하여 새로운, 베스트셀러, 인기도 높은 책 목록을 가져와서 JSP에 전달하는 기능을 주석 처리합니다.
//        // BookDAO bookDAO = new BookDAO();
//        // List<Book> listNewBooks = bookDAO.listNewBooks();
//        // List<Book> listBestSellingBooks = bookDAO.listBestSellingBooks();
//        // List<Book> listFavoredBooks = bookDAO.listMostFavoredBooks();
//        // request.setAttribute("listNewBooks", listNewBooks);
//        // request.setAttribute("listBestSellingBooks", listBestSellingBooks);
//        // request.setAttribute("listFavoredBooks", listFavoredBooks);
//
//        // 주석 처리된 기능은 아래처럼 JSP로 포워딩하지 않고 책 목록을 가져오는데 사용할 수 있습니다.
//        String homepage = "frontend/index.jsp";
//     // 요청 디스패처 생성
//        RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
//
//        // 현재 요청과 응답을 포워드합니다.
//        // - request: 사용자의 요청(request)에 대한 정보를 포함하고 있습니다.
//        // - response: 클라이언트로 응답(response)을 전송하는 데 사용됩니다.
//        // 포워딩: 현재 요청을 JSP 페이지 또는 다른 서블릿으로 전달합니다.
//        dispatcher.forward(request, response);
//    }
//}