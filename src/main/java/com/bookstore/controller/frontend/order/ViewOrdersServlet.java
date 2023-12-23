package com.bookstore.controller.frontend.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.OrderServices;

@WebServlet("/view_orders") // URL 패턴 "/view_orders"에 서블릿을 매핑
public class ViewOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewOrdersServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // OrderServices 인스턴스 생성 및 요청(request)과 응답(response) 객체 전달
        OrderServices orderServices = new OrderServices(request, response);
        
        // 고객의 주문 목록을 보여주는 메서드 호출
        orderServices.listOrderByCustomer();
    }
    

//    	@WebServlet("/view_orders"): 이 서블릿 클래스를 "/view_orders" URL 패턴에 매핑하는 어노테이션입니다. 클라이언트가 "/view_orders" 경로로 GET 방식의 요청을 보내면 이 서블릿이 처리한다는 것을 의미합니다.
//
//    	public class ViewOrdersServlet extends HttpServlet { ... }: HttpServlet 클래스를 확장하는 ViewOrdersServlet 클래스 선언으로, 이 클래스는 HTTP 요청을 처리하는 서블릿으로 동작합니다.
//
//    	private static final long serialVersionUID = 1L;: 직렬화된 버전의 고유 식별자로, Serializable 인터페이스를 구현한 클래스의 버전 관리에 사용됩니다.
//
//    	public ViewOrdersServlet() { super(); }: ViewOrdersServlet 클래스의 기본 생성자입니다.
//
//    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { ... }: HTTP GET 요청을 처리하는 메서드로, 클라이언트가 GET 방식으로 요청하면 호출됩니다. HttpServletRequest와 HttpServletResponse를 매개변수로 받아와서 사용합니다.
//
//    	OrderServices orderServices = new OrderServices(request, response);: OrderServices 클래스의 인스턴스를 생성하고, 해당 클래스에 HTTP 요청과 응답 객체를 전달합니다.
//
//    	orderServices.listOrderByCustomer();: OrderServices 클래스의 listOrderByCustomer() 메서드를 호출하여 고객의 주문 목록을 보여줍니다. 이 메서드는 고객이 주문한 목록을 표시하는 작업을 수행할 것으로 예상됩니다.
}
