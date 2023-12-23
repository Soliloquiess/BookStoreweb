package com.bookstore.controller.frontend.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.service.OrderServices;
import com.bookstore.service.PaymentServices;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/execute_payment") // URL 패턴 "/execute_payment"에 서블릿을 매핑
public class ExecutePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ExecutePaymentServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // PaymentServices 인스턴스 생성 및 요청(request)과 응답(response) 객체 전달
        PaymentServices paymentServices = new PaymentServices(request, response);
        
        try {
            // 결제를 실행하고 결제 정보를 가져옴
            Payment payment = paymentServices.executePayment();
            
            // OrderServices 인스턴스 생성 및 요청(request)과 응답(response) 객체 전달
            OrderServices orderServices = new OrderServices(request, response);
            
            // PayPal을 통해 주문을 처리하고, 반환된 주문 ID를 받음
            Integer orderId = orderServices.placeOrderPaypal(payment);
            
            // HTTP 세션을 얻어서 orderId 속성 설정
            HttpSession session = request.getSession();
            session.setAttribute("orderId", orderId);
            
            // 결제자 정보와 트랜잭션 정보를 세션 속성으로 설정
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            session.setAttribute("payer", payerInfo);
            session.setAttribute("transaction", transaction);
            
            // 결제 영수증 페이지의 경로
            String receiptPage = "frontend/payment_receipt.jsp";
            
            // 요청과 응답 객체를 사용하여 페이지 전환(forward)
            request.getRequestDispatcher(receiptPage).forward(request, response);            
            
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            throw new ServletException("결제 실행 중 오류 발생.");
        }
    }
    
//    @WebServlet("/execute_payment"): 이 서블릿 클래스를 "/execute_payment" URL 패턴에 매핑하는 어노테이션입니다. 클라이언트가 "/execute_payment" 경로로 POST 방식의 요청을 보내면 이 서블릿이 처리한다는 것을 의미합니다.
//
//    public class ExecutePaymentServlet extends HttpServlet { ... }: HttpServlet 클래스를 확장하는 ExecutePaymentServlet 클래스 선언으로, 이 클래스는 HTTP 요청을 처리하는 서블릿으로 동작합니다.
//
//    private static final long serialVersionUID = 1L;: 직렬화된 버전의 고유 식별자로, Serializable 인터페이스를 구현한 클래스의 버전 관리에 사용됩니다.
//
//    public ExecutePaymentServlet() { super(); }: ExecutePaymentServlet 클래스의 기본 생성자입니다.
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { ... }: HTTP POST 요청을 처리하는 메서드로, 클라이언트가 POST 방식으로 요청하면 호출됩니다. HttpServletRequest와 HttpServletResponse를 매개변수로 받아와서 사용합니다.
//
//    PaymentServices paymentServices = new PaymentServices(request, response);: PaymentServices 클래스의 인스턴스를 생성하고, 해당 클래스에 HTTP 요청과 응답 객체를 전달합니다.
//
//    Payment payment = paymentServices.executePayment();: PaymentServices 클래스의 executePayment() 메서드를 호출하여 결제를 실행하고 결제 정보를 가져옵니다.
//
//    Integer orderId = orderServices.placeOrderPaypal(payment);: OrderServices 클래스의 placeOrderPaypal() 메서드를 사용하여 PayPal을 통해 주문을 처리하고, 반환된 주문 ID를 받아옵니다.
//
//    HttpSession session = request.getSession();: HTTP 요청에서 세션을 가져옵니다.
//
//    session.setAttribute("orderId", orderId);: 세션에 orderId 속성을 설정하여 저장합니다.
//
//    PayerInfo payerInfo = payment.getPayer().getPayerInfo();: 결제자 정보를 가져옵니다.
//
//    Transaction transaction = payment.getTransactions().get(0);: 트랜잭션 정보를 가져옵니다.
//
//    session.setAttribute("payer", payerInfo);: 세션에 결제자 정보를 저장합니다.
//
//    session.setAttribute("transaction", transaction);: 세션에 트랜잭션 정보를 저장합니다.
//
//    String receiptPage = "frontend/payment_receipt.jsp";: 결제 영수증 페이지의 경로를 설정합니다.
//
//    request.getRequestDispatcher(receiptPage).forward(request, response);: 요청과 응답 객체를 사용하여 결제 영수증 페이지로 forward합니다.
    
}
