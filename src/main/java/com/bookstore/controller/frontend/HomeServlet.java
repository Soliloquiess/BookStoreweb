package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // BookDAO를 사용하여 새로운, 베스트셀러, 인기도 높은 책 목록을 가져와서 JSP에 전달하는 기능을 주석 처리합니다.
        // BookDAO bookDAO = new BookDAO();
        // List<Book> listNewBooks = bookDAO.listNewBooks();
        // List<Book> listBestSellingBooks = bookDAO.listBestSellingBooks();
        // List<Book> listFavoredBooks = bookDAO.listMostFavoredBooks();
        // request.setAttribute("listNewBooks", listNewBooks);
        // request.setAttribute("listBestSellingBooks", listBestSellingBooks);
        // request.setAttribute("listFavoredBooks", listFavoredBooks);

        // 주석 처리된 기능은 아래처럼 JSP로 포워딩하지 않고 책 목록을 가져오는데 사용할 수 있습니다.
        String homepage = "frontend/index.jsp";
     // 요청 디스패처 생성
        RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);

        // 현재 요청과 응답을 포워드합니다.
        // - request: 사용자의 요청(request)에 대한 정보를 포함하고 있습니다.
        // - response: 클라이언트로 응답(response)을 전송하는 데 사용됩니다.
        // 포워딩: 현재 요청을 JSP 페이지 또는 다른 서블릿으로 전달합니다.
        dispatcher.forward(request, response);
    }
}