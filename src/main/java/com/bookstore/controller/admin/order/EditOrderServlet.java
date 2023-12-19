package com.bookstore.controller.admin.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.OrderServices;

@WebServlet("/admin/edit_order") // "/admin/edit_order" URL에 매핑된 서블릿을 정의하는 어노테이션
public class EditOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // OrderServices 클래스의 인스턴스 생성하여 request와 response 객체를 전달
        OrderServices orderServices = new OrderServices(request, response);
        // 주문을 수정하기 위한 폼을 보여주는 메서드 호출
        orderServices.showEditOrderForm();
    }
}
