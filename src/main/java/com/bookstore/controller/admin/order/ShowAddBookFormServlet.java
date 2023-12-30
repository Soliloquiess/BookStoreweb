package com.bookstore.controller.admin.order;

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

@WebServlet("/admin/add_book_form") // "/admin/add_book_form" URL에 매핑된 서블릿을 정의하는 어노테이션
public class ShowAddBookFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowAddBookFormServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO bookDao = new BookDAO(); // BookDAO 객체 생성
        List<Book> listBook = bookDao.listAll(); // 모든 Book 객체 목록을 가져오는 메서드 호출하여 리스트에 저장
        request.setAttribute("listBook", listBook); // 요청 속성에 Book 목록을 설정
        
        String addFormPage = "add_book_form.jsp"; // 추가 폼 페이지 경로 설정
        RequestDispatcher dispatcher = request.getRequestDispatcher(addFormPage); // 요청을 위해 지정된 페이지로 포워딩하기 위한 Dispatcher 객체 생성
        dispatcher.forward(request, response); // 요청과 응답 객체를 지정된 페이지로 전달하여 페이지를 로드함
    }
    
    //이 서블릿 코드는 /admin/add_book_form URL에 매핑되는 서블릿을 정의합니다. HTTP GET 요청이 발생하면 doGet 메서드가 호출되며, BookDAO 객체를 사용하여 모든 책 목록을 가져옵니다. 그 후, 요청 속성에 책 목록을 설정하고 add_book_form.jsp 페이지로 포워딩하여 추가 폼 페이지를 표시합니다.
}
