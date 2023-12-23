package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import static com.bookstore.service.CommonUtility.*;

public class OrderServices {
	private OrderDAO orderDao; // OrderDAO 객체를 선언합니다.

	private HttpServletRequest request; // HTTP 요청을 처리하기 위한 HttpServletRequest 객체를 선언합니다.
	private HttpServletResponse response; // HTTP 응답을 처리하기 위한 HttpServletResponse 객체를 선언합니다.

	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
	    // 생성자를 통해 HttpServletRequest와 HttpServletResponse 객체를 전달받고 필드에 할당합니다.
	    this.request = request;
	    this.response = response;
	    
	    // OrderDAO 객체를 생성하여 할당합니다.
	    this.orderDao = new OrderDAO();
	}

	public void listAllOrder() throws ServletException, IOException {
	    listAllOrder(null); // listAllOrder 메서드를 호출하는데, 초기 메시지를 null로 설정하여 호출합니다.
	}

	public void listAllOrder(String message) throws ServletException, IOException {
	    List<BookOrder> listOrder = orderDao.listAll(); // OrderDAO를 사용하여 모든 주문 목록을 가져옵니다.

	    if (message != null) {
	        request.setAttribute("message", message); // 메시지가 존재하면 요청 속성에 메시지를 설정합니다.
	    }

	    request.setAttribute("listOrder", listOrder); // 주문 목록을 요청 속성에 설정합니다.

	    String listPage = "order_list.jsp"; // 주문 목록 페이지의 경로를 설정합니다.
	    RequestDispatcher dispatcher = request.getRequestDispatcher(listPage); // 요청을 해당 페이지로 전달하기 위한 Dispatcher를 가져옵니다.
	    dispatcher.forward(request, response); // 요청과 응답을 해당 페이지로 전달합니다.
	}

//	private OrderDAO orderDao;: OrderDAO 객체를 선언합니다.
//
//	private HttpServletRequest request;: HTTP 요청을 처리하기 위한 HttpServletRequest 객체를 선언합니다.
//
//	private HttpServletResponse response;: HTTP 응답을 처리하기 위한 HttpServletResponse 객체를 선언합니다.
//
//	public OrderServices(HttpServletRequest request, HttpServletResponse response) { ... }: 생성자에서 HttpServletRequest와 HttpServletResponse 객체를 받아와서 필드에 할당하고, OrderDAO 객체를 생성하여 초기화합니다.
//
//	public void listAllOrder() throws ServletException, IOException { ... }: 주문 목록을 나열하는 메서드입니다. 이 메서드는 초기 메시지를 null로 설정하여 listAllOrder(String message) 메서드를 호출합니다.
//
//	public void listAllOrder(String message) throws ServletException, IOException { ... }: 메시지와 함께 주문 목록을 나열하는 메서드입니다. OrderDAO를 사용하여 모든 주문 목록을 가져온 후, 요청 속성에 메시지와 주문 목록을 설정하고, 해당 페이지로 요청을 전달합니다	
	
	public void viewOrderDetailForAdmin() throws ServletException, IOException {
	    // 요청에서 "id" 매개변수를 추출하여 orderId 변수에 저장합니다.
	    int orderId = Integer.parseInt(request.getParameter("id"));
	    
	    // 주문 ID를 사용하여 해당 주문 정보를 orderDao에서 가져옵니다.
	    BookOrder order = orderDao.get(orderId);
	    
	    // 만약 해당 주문 정보가 존재하는 경우
	    if (order != null) {
	        // 주문 정보를 "order"라는 이름으로 요청 속성에 설정합니다.
	        request.setAttribute("order", order);
	        
	        // "order_detail.jsp" 페이지로 요청을 전달하여 해당 주문의 세부 정보를 표시합니다.
	        forwardToPage("order_detail.jsp", request, response);
	    } else {
	        // 주문 정보가 존재하지 않는 경우, 해당 ID를 가진 주문을 찾을 수 없다는 메시지를 작성합니다.
	        String message = "Could not find order with ID " + orderId;
	        
	        // showMessageBackend 메서드를 사용하여 메시지를 브라우저에 표시합니다.
	        showMessageBackend(message, request, response);
	    }
	}

	
	//이 아래는 2023-12-23 전 코드 assignnment 21 참조. 이 코드 실행 시 제대로 동작 X
//	public void viewOrderDetailForAdmin() throws ServletException, IOException {
//	    // request.getParameter("id")를 통해 주문 ID를 가져와 정수형으로 변환합니다.
//	    int orderId = Integer.parseInt(request.getParameter("id"));
//
//	    // orderDao에서 해당 주문 ID에 해당하는 주문을 가져옵니다.
//	    BookOrder order = orderDao.get(orderId);
//
//	    if (order != null) {
//	        // 주문이 존재하는 경우 해당 주문 정보를 요청 속성에 설정하고 order_detail.jsp 페이지로 전달합니다.
//	        request.setAttribute("order", order);
//	        forwardToPage("order_detail.jsp", request, response);
//	    } else {
//	        // 주문을 찾지 못한 경우 오류 메시지를 생성하고 백엔드 페이지에 메시지를 표시합니다.
//	        String message = "Could not find order with ID " + orderId;
//	        showMessageBackend(message, request, response);
//	    }
//	    request.setAttribute("order", order); // 주문 정보를 요청 속성에 다시 설정합니다.
//
//	    String detailPage = "order_detail.jsp"; // 주문 상세 페이지의 경로를 지정합니다.
//	    RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage); // Dispatcher를 설정합니다.
//	    dispatcher.forward(request, response); // 요청과 응답을 해당 페이지로 전달합니다.
//	}
	
	//아래는 기본적인 OrderDetailFormAdmini 폼
//	public void viewOrderDetailForAdmin() throws ServletException, IOException {
//		int orderId = Integer.parseInt(request.getParameter("id"));
//		
//		BookOrder order = orderDao.get(orderId);
//		request.setAttribute("order", order);
//		
//		String detailPage = "order_detail.jsp";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
//		dispatcher.forward(request, response);		
//	}

	
	public void showCheckoutForm() throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart"); // 세션에서 장바구니 정보를 가져옵니다.

	    // 세금은 총액의 10%로 계산됩니다.
	    float tax = shoppingCart.getTotalAmount() * 0.1f;

	    // 배송료는 상품 수량당 1.0 달러로 계산됩니다.
	    float shippingFee = shoppingCart.getTotalQuantity() * 1.0f;

	    // 총 금액은 총액에 세금과 배송료를 더한 값입니다.
	    float total = shoppingCart.getTotalAmount() + tax + shippingFee;

	    // 세션에 세금, 배송료, 총 금액을 설정합니다.
	    session.setAttribute("tax", tax);
	    session.setAttribute("shippingFee", shippingFee);
	    session.setAttribute("total", total);

	    CommonUtility.generateCountryList(request); // 국가 목록을 생성하여 요청 속성에 설정합니다.

	    String checkOutPage = "frontend/checkout.jsp"; // 체크아웃 페이지의 경로를 설정합니다.
	    RequestDispatcher dispatcher = request.getRequestDispatcher(checkOutPage); // Dispatcher를 설정합니다.
	    dispatcher.forward(request, response); // 요청과 응답을 해당 페이지로 전달합니다.
	}

//	public void viewOrderDetailForAdmin() throws ServletException, IOException { ... }:
//
//		URL 매개변수인 "id"를 통해 주문 ID를 가져온 후 해당 ID에 해당하는 주문 정보를 검색합니다.
//		주문이 존재하는 경우 해당 정보를 설정하고, 주문 상세 페이지로 전달합니다. 그렇지 않은 경우 오류 메시지를 생성하고 백엔드 페이지에 표시합니다.
//		public void showCheckoutForm() throws ServletException, IOException { ... }:
//
//		세션에서 장바구니 정보를 가져와 세금 및 배송료 등을 계산하여 세션에 설정합니다.
//		국가 목록을 생성하여 요청 속성에 설정한 후, 체크아웃 페이지로 전달합니다.
	

	public void placeOrder() throws ServletException, IOException {
	    // paymentMethod 매개변수에서 결제 방법을 가져옵니다.
	    String paymentMethod = request.getParameter("paymentMethod");
	    
	    // readOrderInfo 메서드를 사용하여 주문 정보를 가져옵니다.
	    BookOrder order = readOrderInfo();

	    if (paymentMethod.equals("paypal")) {
	        // PayPal 결제 방법을 선택한 경우 PaymentServices를 사용하여 PayPal 결제를 인증합니다.
	        PaymentServices paymentServices = new PaymentServices(request, response);
	        
	        // 세션에 주문 정보를 저장하고 PayPal 결제를 인증합니다.
	        request.getSession().setAttribute("order4Paypal", order);
	        paymentServices.authorizePayment(order);
	    } else {    // 현금 결제인 경우
	        // 현금으로 결제하는 메서드를 호출하여 주문을 처리합니다.
	        placeOrderCOD(order);
	    }           
	}

	public Integer placeOrderPaypal(Payment payment) {
	    // PayPal에서 받은 결제 정보를 사용하여 주문 및 배송 정보를 가져옵니다.
	    BookOrder order = (BookOrder) request.getSession().getAttribute("order4Paypal");
	    ItemList itemList = payment.getTransactions().get(0).getItemList();
	    ShippingAddress shippingAddress = itemList.getShippingAddress();
	    String shippingPhoneNumber = itemList.getShippingPhoneNumber();

	    String recipientName = shippingAddress.getRecipientName();
	    String[] names = recipientName.split(" "); // 수령자 이름을 공백으로 분리합니다.

	    // 주문 정보에 수령자 정보를 설정합니다.
	    order.setFirstname(names[0]);
	    order.setLastname(names[1]);
	    order.setAddressLine1(shippingAddress.getLine1());
	    order.setAddressLine2(shippingAddress.getLine2());
	    order.setCity(shippingAddress.getCity());
	    order.setState(shippingAddress.getState());
	    order.setCountry(shippingAddress.getCountryCode());
	    order.setPhone(shippingPhoneNumber);

	    return saveOrder(order); // 주문 정보를 저장하고 주문 ID를 반환합니다.
	}
	
//	public void placeOrder() throws ServletException, IOException { ... }:
//
//		결제 방법을 가져온 후, 해당 방법에 따라 PayPal 결제인지 현금 결제인지 확인하고 처리합니다.
//		PayPal 결제를 선택한 경우, 주문 정보를 세션에 저장하고 PayPal 결제를 인증하는 PaymentServices 클래스의 authorizePayment 메서드를 호출합니다.
//		현금 결제인 경우, placeOrderCOD 메서드를 호출하여 현금으로 주문을 처리합니다.
//		public Integer placeOrderPaypal(Payment payment) { ... }:
//
//		PayPal 결제를 선택한 경우 PayPal에서 받은 결제 정보를 사용하여 주문 및 배송 정보를 가져와서 처리합니다.
//		주문 정보에 수령자 정보 및 배송 정보를 설정하고, saveOrder 메서드를 호출하여 주문 정보를 저장하고 주문 ID를 반환합니다.
	
	private Integer saveOrder(BookOrder order) {
	    // orderDao를 사용하여 주문을 생성하고 반환합니다.
	    BookOrder savedOrder = orderDao.create(order);

	    // 세션에서 장바구니 정보를 가져옵니다.
	    ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
	    
	    shoppingCart.clear(); // 주문 완료 후 장바구니를 비웁니다.

	    return savedOrder.getOrderId(); // 저장된 주문의 ID를 반환합니다.
	}
	

	private BookOrder readOrderInfo() {
	    // 요청 매개변수를 통해 주문 정보를 읽어옵니다.
	    String paymentMethod = request.getParameter("paymentMethod");
	    String firstname = request.getParameter("firstname");
	    String lastname = request.getParameter("lastname");
	    String phone = request.getParameter("phone");
	    String address1 = request.getParameter("address1");
	    String address2 = request.getParameter("address2");
	    String city = request.getParameter("city");
	    String state = request.getParameter("state");
	    String zipcode = request.getParameter("zipcode");
	    String country = request.getParameter("country");        
	    
	    // 주문 정보를 생성하고 요청 매개변수로부터 값을 설정합니다.
	    BookOrder order = new BookOrder();
	    order.setFirstname(firstname);
	    order.setLastname(lastname);
	    order.setPhone(phone);
	    order.setAddressLine1(address1);
	    order.setAddressLine2(address2);
	    order.setCity(city);
	    order.setState(state);
	    order.setCountry(country);
	    order.setZipcode(zipcode);
	    order.setPaymentMethod(paymentMethod);
	    
	    // 세션에서 현재 로그인한 고객 정보를 가져옵니다.
	    HttpSession session = request.getSession();
	    Customer customer = (Customer) session.getAttribute("loggedCustomer");
	    order.setCustomer(customer);
	    
	    // 세션에서 장바구니 정보와 책의 목록을 가져와서 주문 상세 정보를 설정합니다.
	 // 세션에서 쇼핑 카트 객체를 가져옵니다.
	    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");

	    // 쇼핑 카트에 담긴 아이템들과 해당 수량을 맵으로 가져옵니다.
	    Map<Book, Integer> items = shoppingCart.getItems();

	    // 아이템들을 순회할 이터레이터를 선언하고 초기화합니다.
	    Iterator<Book> iterator = items.keySet().iterator();

	    // 주문 상세 정보를 저장할 Set을 초기화합니다.
	    Set<OrderDetail> orderDetails = new HashSet<>();

	    // 모든 아이템에 대해 반복하여 주문 상세 정보를 만듭니다.
	    while (iterator.hasNext()) {
	        // 이터레이터에서 다음 책을 가져옵니다.
	        Book book = iterator.next();
	        
	        // 해당 책의 수량을 가져옵니다.
	        Integer quantity = items.get(book);
	        
	        // 총 가격을 계산합니다. 수량 * 책의 가격
	        float subtotal = quantity * book.getPrice();
	        
	        // 주문 상세 정보(OrderDetail) 객체를 생성하고 정보를 설정합니다.
	        OrderDetail orderDetail = new OrderDetail();
	        orderDetail.setBook(book); // 책 정보 설정
	        orderDetail.setBookOrder(order); // 해당 주문에 대한 정보 설정
	        orderDetail.setQuantity(quantity); // 주문된 책의 수량 설정
	        orderDetail.setSubtotal(subtotal); // 해당 아이템들의 총 가격 설정
	        
	        // 생성된 주문 상세 정보를 주문 상세 정보 Set에 추가합니다.
	        orderDetails.add(orderDetail);
	    }

	    // 생성된 모든 주문 상세 정보를 해당 주문(Order) 객체의 상세 주문 목록에 설정합니다.
	    order.setOrderDetails(orderDetails);

	    
	    // 세션에서 세금, 배송료, 총 금액 등의 정보를 가져와서 주문 정보에 설정합니다.
	    float tax = (Float) session.getAttribute("tax");
	    float shippingFee = (Float) session.getAttribute("shippingFee");
	    float total = (Float) session.getAttribute("total");
	    
	    order.setSubtotal(shoppingCart.getTotalAmount());
	    order.setTax(tax);
	    order.setShippingFee(shippingFee);
	    order.setTotal(total);        
	    
	    return order; // 생성된 주문 정보를 반환합니다.
	}
	
//	private Integer saveOrder(BookOrder order) { ... }:
//
//		orderDao.create(order)를 사용하여 주문을 생성하고, 생성된 주문의 ID를 반환합니다.
//		장바구니 정보를 비우고, 저장된 주문의 ID를 반환합니다.
//		private BookOrder readOrderInfo() { ... }:
//
//		요청 매개변수를 통해 주문 정보를 읽어와 BookOrder 객체에 설정합니다.
//		세션에서 현재 로그인한 고객 정보를 가져와 주문 객체에 설정합니다.
//		세션에서 장바구니 정보와 책 목록을 가져와서 주문 상세 정보를 생성하고 설정합니다.
//		세션에서 세금, 배송료, 총 금액 등의 정보를 가져와 주문 정보에 설정합니다.
//		생성된 주문 정보를 반환합니다.
	
	private void placeOrderCOD(BookOrder order) throws ServletException, IOException {
	    saveOrder(order); // 현금 결제로 주문을 처리하고 저장합니다.
	    
	    // 주문 완료 메시지를 생성하고 페이지에 전달할 요청 속성을 설정합니다.
	    String message = "Thank you. Your order has been received. "
	            + "We will deliver your books within a few days.";
	    request.setAttribute("message", message);
	    request.setAttribute("pageTitle", "Order Completed"); // 페이지 타이틀을 설정합니다.
	    
	    String messagePage = "frontend/message.jsp"; // 메시지 페이지의 경로를 설정합니다.
	    RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage); // Dispatcher를 설정합니다.
	    dispatcher.forward(request, response); // 요청과 응답을 해당 페이지로 전달합니다.
	}

	public void listOrderByCustomer() throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Customer customer = (Customer) session.getAttribute("loggedCustomer"); // 세션에서 현재 로그인한 고객 정보를 가져옵니다.
	    List<BookOrder> listOrders = orderDao.listByCustomer(customer.getCustomerId()); // 고객의 주문 목록을 가져옵니다.
	    
	    request.setAttribute("listOrders", listOrders); // 요청 속성에 고객의 주문 목록을 설정합니다.
	    
	    String historyPage = "frontend/order_list.jsp"; // 주문 내역 페이지의 경로를 설정합니다.
	    RequestDispatcher dispatcher = request.getRequestDispatcher(historyPage); // Dispatcher를 설정합니다.
	    dispatcher.forward(request, response); // 요청과 응답을 해당 페이지로 전달합니다.
	}

//	private void placeOrderCOD(BookOrder order) throws ServletException, IOException { ... }:
//
//		saveOrder(order) 메서드를 호출하여 주문을 처리하고 저장합니다.
//		주문 완료 메시지를 생성하고 페이지에 표시할 요청 속성을 설정합니다.
//		설정된 속성을 사용하여 메시지 페이지로 요청과 응답을 전달하여 주문 완료 메시지를 표시합니다.
//		public void listOrderByCustomer() throws ServletException, IOException { ... }:
//
//		세션에서 현재 로그인한 고객 정보를 가져옵니다.
//		OrderDAO를 사용하여 해당 고객의 주문 목록을 가져와 요청 속성에 설정합니다.
//		설정된 속성을 사용하여 주문 내역 페이지로 요청과 응답을 전달하여 고객의 주문 목록을 표시합니다.
	

	public void showOrderDetailForCustomer() throws ServletException, IOException {
	    // URL 매개변수에서 주문 ID를 가져와 정수형으로 변환합니다.
	    int orderId = Integer.parseInt(request.getParameter("id"));
	    
	    // 세션에서 현재 로그인한 고객 정보를 가져옵니다.
	    HttpSession session = request.getSession();
	    Customer customer = (Customer) session.getAttribute("loggedCustomer");
	    
	    // OrderDAO를 사용하여 주문 ID와 고객 ID에 해당하는 주문 정보를 가져옵니다.
	    BookOrder order = orderDao.get(orderId, customer.getCustomerId());
	    request.setAttribute("order", order); // 요청 속성에 주문 정보를 설정합니다.
	    
	    String detailPage = "frontend/order_detail.jsp"; // 주문 상세 페이지의 경로를 지정합니다.
	    RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage); // Dispatcher를 설정합니다.
	    dispatcher.forward(request, response); // 요청과 응답을 해당 페이지로 전달합니다.
	}

//	public void showOrderDetailForCustomer() throws ServletException, IOException { ... }:
//		URL 매개변수에서 주문 ID를 가져와 정수형으로 변환합니다.
//		세션에서 현재 로그인한 고객 정보를 가져옵니다.
//		OrderDAO를 사용하여 해당 주문 ID와 고객 ID에 해당하는 주문 정보를 가져옵니다.
//		요청 속성에 주문 정보를 설정하고, 주문 상세 페이지로 요청과 응답을 전달하여 해당 주문의 상세 정보를 표시합니다.


	//2023-12-23 전 코드 assignment24 추가 코드
	public void showEditOrderForm() throws ServletException, IOException {
	    // HTTP 요청에서 "id" 매개변수를 추출하여 orderId 변수에 저장합니다.
	    Integer orderId = Integer.parseInt(request.getParameter("id"));		
	    
	    // 주문 ID를 사용하여 해당 주문을 orderDao에서 가져옵니다.
	    BookOrder order = orderDao.get(orderId);
	    
	    // 주문이 존재하지 않는 경우
	    if (order == null) {
	        // 해당 ID를 가진 주문을 찾을 수 없음을 알리는 메시지를 작성합니다.
	        String message = "Could not find order with ID " + orderId;
	        
	        // showMessageBackend 메서드를 사용하여 메시지를 브라우저에 표시하고, 서블릿 처리를 중단합니다.
	        showMessageBackend(message, request, response);
	        return; // 메서드 실행을 종료하고 반환합니다.
	    }
	    
	    // 현재 요청의 세션 객체를 가져옵니다.
	    HttpSession session = request.getSession();
	    
	    // 세션에서 "NewBookPendingToAddToOrder" 속성의 값을 가져옵니다.
	    Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");
	    
	    // "NewBookPendingToAddToOrder" 속성이 없는 경우
	    if (isPendingBook == null) {			
	        // 세션에 "order" 속성으로 현재의 주문 정보를 저장합니다.
	        session.setAttribute("order", order);
	    } else {
	        // "NewBookPendingToAddToOrder" 속성이 있는 경우, 해당 속성을 세션에서 제거합니다.
	        session.removeAttribute("NewBookPendingToAddToOrder");
	    }
	    
	    // "order_form.jsp" 페이지로 요청을 전달합니다.
	    forwardToPage("order_form.jsp", request, response);
	}

	
	//2023-12-23 전 코드
//	public void showEditOrderForm() throws ServletException, IOException {
//	    // URL 매개변수에서 주문 ID를 가져와 정수형으로 변환합니다.
//	    Integer orderId = Integer.parseInt(request.getParameter("id"));
//	    
//	    // 세션에서 "NewBookPendingToAddToOrder" 속성을 가져옵니다.
//	    HttpSession session = request.getSession();
//	    Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");
//	    
//	    if (isPendingBook == null) {
//	        // "NewBookPendingToAddToOrder" 속성이 없는 경우 OrderDAO를 사용하여 해당 주문 ID의 주문 정보를 가져와 세션에 저장합니다.
//	        BookOrder order = orderDao.get(orderId);
//	        session.setAttribute("order", order); // 주문 정보를 세션에 저장합니다.
//	    } else {
//	        // "NewBookPendingToAddToOrder" 속성이 있는 경우 해당 속성을 세션에서 제거합니다.
//	        session.removeAttribute("NewBookPendingToAddToOrder");
//	    }
//	    
//	    // 국가 목록을 생성하여 요청 속성에 추가합니다.
//	    CommonUtility.generateCountryList(request);
//	    
//	    String editPage = "order_form.jsp"; // 주문 편집 페이지의 경로를 지정합니다.
//	    RequestDispatcher dispatcher = request.getRequestDispatcher(editPage); // Dispatcher를 설정합니다.
//	    dispatcher.forward(request, response); // 요청과 응답을 해당 페이지로 전달합니다.
//	}

//	public void showEditOrderForm() throws ServletException, IOException { ... }:
//		URL 매개변수에서 주문 ID를 가져와 정수형으로 변환합니다.
//		세션에서 "NewBookPendingToAddToOrder" 속성을 가져옵니다.
//		만약 "NewBookPendingToAddToOrder" 속성이 없는 경우 OrderDAO를 사용하여 해당 주문 ID의 주문 정보를 가져와 세션에 저장합니다.
//		"NewBookPendingToAddToOrder" 속성이 있는 경우 해당 속성을 세션에서 제거합니다.
//		국가 목록을 생성하여 요청 속성에 추가하고, 주문 편집 페이지로 요청과 응답을 전달하여 해당 주문을 편집할 수 있는 페이지를 표시합니다.

	public void updateOrder() throws ServletException, IOException {
	    // 세션에서 주문 정보를 가져옵니다.
	    HttpSession session = request.getSession();
	    BookOrder order = (BookOrder) session.getAttribute("order");
	    
	    // 요청 매개변수에서 주문자 정보를 읽어옵니다.
	    String firstname = request.getParameter("firstname");
	    String lastname = request.getParameter("lastname");
	    String phone = request.getParameter("phone");
	    String address1 = request.getParameter("address1");
	    String address2 = request.getParameter("address2");
	    String city = request.getParameter("city");
	    String state = request.getParameter("state");
	    String zipcode = request.getParameter("zipcode");
	    String country = request.getParameter("country");
	    
	    // 요청 매개변수에서 배송비와 세금 정보를 가져옵니다.
	    float shippingFee = Float.parseFloat(request.getParameter("shippingFee"));
	    float tax = Float.parseFloat(request.getParameter("tax"));
	    
	    // 요청 매개변수에서 결제 방법과 주문 상태 정보를 가져옵니다.
	    String paymentMethod = request.getParameter("paymentMethod");
	    String orderStatus = request.getParameter("orderStatus");
	    
	    // 주문자 정보와 관련된 주문 객체의 속성을 업데이트합니다.
	    order.setFirstname(firstname);
	    order.setLastname(lastname);
	    order.setPhone(phone);
	    order.setAddressLine1(address1);
	    order.setAddressLine2(address2);
	    order.setCity(city);
	    order.setState(state);
	    order.setZipcode(zipcode);
	    order.setCountry(country);
	    order.setShippingFee(shippingFee);
	    order.setTax(tax);
	    order.setPaymentMethod(paymentMethod);
	    order.setStatus(orderStatus);
	    
	    // 요청 매개변수에서 책 ID, 가격, 수량 정보를 배열로 받습니다.
	    String[] arrayBookId = request.getParameterValues("bookId");
	    String[] arrayPrice = request.getParameterValues("price");
	    String[] arrayQuantity = new String[arrayBookId.length];
	    
	    // 수량을 배열로 받아와서 주문 상세 정보를 업데이트합니다.
	    for (int i = 1; i <= arrayQuantity.length; i++) {
	        arrayQuantity[i - 1] = request.getParameter("quantity" + i);
	    }
	    
	    Set<OrderDetail> orderDetails = order.getOrderDetails();
	    orderDetails.clear(); // 기존 주문 상세 정보를 지웁니다.
	    
	    float totalAmount = 0.0f; // 총 금액을 초기화합니다.
	    
	    // 주문 상세 정보를 업데이트하고 총 주문 금액을 계산합니다.
	    for (int i = 0; i < arrayBookId.length; i++) {
	        int bookId = Integer.parseInt(arrayBookId[i]);
	        int quantity = Integer.parseInt(arrayQuantity[i]);
	        float price = Float.parseFloat(arrayPrice[i]);
	        
	        float subtotal = price * quantity;
	        
	        OrderDetail orderDetail = new OrderDetail();
	        orderDetail.setBook(new Book(bookId));
	        orderDetail.setQuantity(quantity);
	        orderDetail.setSubtotal(subtotal);
	        orderDetail.setBookOrder(order);
	        
	        orderDetails.add(orderDetail); // 새로운 주문 상세 정보를 추가합니다.
	        
	        totalAmount += subtotal; // 총 주문 금액을 업데이트합니다.
	    }
	    
	    order.setSubtotal(totalAmount); // 총 주문 금액을 설정합니다.
	    totalAmount += shippingFee; // 배송비를 추가합니다.
	    totalAmount += tax; // 세금을 추가합니다.
	    
	    order.setTotal(totalAmount); // 최종 총 금액을 설정합니다.
	    
	    orderDao.update(order); // 주문을 업데이트합니다.
	    
	    String message = "The order " + order.getOrderId() + " has been updated successfully"; // 성공 메시지를 생성합니다.
	    
	    listAllOrder(message); // 모든 주문 목록을 표시하며 성공 메시지를 함께 전달합니다.
	}

//	public void updateOrder() throws ServletException, IOException { ... }:
//		세션에서 주문 정보를 가져옵니다.
//		요청 매개변수에서 주문자 정보와 배송비, 세금, 결제 방법, 주문 상태 등의 정보를 읽어와 해당 주문 객체의 속성을 업데이트합니다.
//		요청 매개변수에서 책 ID, 가격, 수량 정보를 배열로 받아와서 주문 상세 정보를 업데이트합니다.
//		업데이트된 주문 정보와 상세 정보를 사용하여 총 주문 금액을 계산하고, 주문을 업데이트합니다.
//		주문 업데이트가 성공하면 해당 주문 정보와 함께 성공 메시지를 표시하여 모든 주문 목록을 표시합니다.
	
	public void deleteOrder() throws ServletException, IOException {
	    // URL 매개변수에서 주문 ID를 가져와 정수형으로 변환합니다.
	    Integer orderId = Integer.parseInt(request.getParameter("id"));
	    
	    orderDao.delete(orderId); // OrderDAO를 사용하여 해당 주문 ID에 해당하는 주문을 삭제합니다.
	    
	    String message = "The order ID " + orderId + " has been deleted."; // 삭제 완료 메시지를 생성합니다.
	    
	    listAllOrder(message); // 모든 주문 목록을 표시하며 삭제 완료 메시지를 함께 전달합니다.
	}

//	public void deleteOrder() throws ServletException, IOException { ... }:
//		URL 매개변수에서 주문 ID를 가져와 정수형으로 변환합니다.
//		OrderDAO를 사용하여 해당 주문 ID에 해당하는 주문을 삭제합니다.
//		주문 삭제가 완료되면 삭제 완료 메시지를 생성합니다.
//		삭제 완료 메시지와 함께 모든 주문 목록을 표시하여 삭제한 주문을 제외한 나머지 주문을 보여줍니다
}
