package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

@WebServlet("/admin/remove_book_from_order") // "/admin/remove_book_from_order" URL에 매핑된 서블릿을 정의하는 어노테이션
public class RemoveBookFromOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveBookFromOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청 매개변수인 bookId 값을 가져와서 정수로 변환
        int bookId = Integer.parseInt(request.getParameter("id"));
        // 현재 세션에서 "order" 속성을 통해 BookOrder 객체를 가져옴
        HttpSession session = request.getSession();
        BookOrder order = (BookOrder) session.getAttribute("order");

        // 주문에 속한 주문 상세 정보들을 가져옴
        Set<OrderDetail> orderDetails = order.getOrderDetails();
        Iterator<OrderDetail> iterator = orderDetails.iterator();
        
        while (iterator.hasNext()) {
            OrderDetail orderDetail = iterator.next();
            
            // 주문 상세 정보에서 bookId에 해당하는 도서를 찾아서 제거하고 총액을 조정함
            if (orderDetail.getBook().getBookId() == bookId) {
                float newTotal = order.getTotal() - orderDetail.getSubtotal();
                order.setTotal(newTotal);
                iterator.remove(); // 주문 상세 정보 제거
            }            
        }
        
        // 주문 수정 페이지로 포워딩

        // 수정된 주문 양식 페이지의 경로를 문자열 변수에 저장합니다.
        String editOrderFormPage = "order_form.jsp";

        // 현재 요청(request)과 응답(response)을 사용하여 RequestDispatcher를 얻습니다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(editOrderFormPage);

        // 얻은 RequestDispatcher를 통해 현재 요청(request)과 응답(response)을 해당 페이지로 전달(forward)합니다.
        dispatcher.forward(request, response);

        
// 해당 Java Servlet 코드는 /admin/remove_book_from_order URL에 매핑되는 서블릿을 정의합니다. HTTP GET 요청이 발생하면 doGet 메서드가 호출되며, 요청에서 bookId 매개변수 값을 가져와서 BookOrder 객체를 세션에서 가져옵니다. 주문에 속한 주문 상세 정보들을 반복하면서 해당 bookId에 해당하는 도서를 찾아 제거하고, 그에 따라 총액을 조정합니다. 마지막으로 주문 수정 페이지로 포워딩합니다.
    }
}