package com.bookstore.controller.admin.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.OrderServices;

@WebServlet("/admin/update_order") // "/admin/update_order" URL에 매핑된 서블릿을 정의하는 어노테이션
public class UpdateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateOrderServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // OrderServices 클래스의 인스턴스 생성하여 request와 response 객체를 전달
        OrderServices orderServices = new OrderServices(request, response);
        // 주문을 업데이트하는 메서드 호출
        orderServices.updateOrder();
    }
}

//해당 Java Servlet 코드는 /admin/update_order URL에 매핑되는 서블릿을 정의합니다. HTTP POST 요청이 발생하면 doPost 메서드가 호출되며, OrderServices 클래스의 인스턴스를 생성한 후 해당 클래스 내의 updateOrder 메서드를 호출하여 주문을 업데이트합니다.