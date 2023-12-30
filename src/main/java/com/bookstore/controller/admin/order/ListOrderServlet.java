package com.bookstore.controller.admin.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.OrderServices;

@WebServlet("/admin/list_order") // "/admin/list_order" URL에 매핑된 서블릿을 정의하는 어노테이션
public class ListOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // OrderServices 클래스의 인스턴스 생성하여 request와 response 객체를 전달
        OrderServices orderServices = new OrderServices(request, response);
        // 모든 주문 목록을 표시하는 메서드 호출
        orderServices.listAllOrder();
    }
// 해당 Java Servlet 코드는 /admin/list_order URL에 매핑되는 서블릿을 정의합니다. HTTP GET 요청이 발생하면 doGet 메서드가 호출되며, OrderServices 클래스의 인스턴스를 생성한 후에 해당 클래스 내의 listAllOrder 메서드를 호출하여 모든 주문 목록을 표시합니다.
}