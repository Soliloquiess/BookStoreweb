package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderDAOTest {

	private static OrderDAO orderDAO; // OrderDAO 객체를 선언합니다.

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    orderDAO = new OrderDAO(); // OrderDAO 객체를 생성하여 초기화합니다.
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	    orderDAO.close(); // OrderDAO 객체를 닫습니다.
	}

	@Test
	public void testCreateBookOrder2() {
	    // BookOrder 및 관련 객체를 생성하여 OrderDAO를 사용하여 데이터베이스에 주문을 추가하는 테스트를 수행합니다.

	    BookOrder order = new BookOrder(); // BookOrder 객체를 생성합니다.
	    Customer customer = new Customer(); // Customer 객체를 생성합니다.
	    customer.setCustomerId(2); // Customer ID를 설정합니다.

	    order.setCustomer(customer); // 주문에 Customer를 설정합니다.
	    order.setFirstname("Peter"); // 주문자의 이름을 설정합니다.

	    order.setLastname("Drucker");
	    
	    order.setPhone("123456789"); // 주문자의 전화번호를 설정합니다.
	    order.setAddressLine1("123 South Street, New York, USA"); // 주문자의 주소를 설정합니다.
	    //
	    order.setAddressLine2("busan, USA"); // 주문자의 주소를 설정합니다.
	    order.setCity("New York");
	    order.setState("New York");
	    order.setCountry("US");
	    order.setPaymentMethod("paypal");
	    order.setZipcode("123456");

	    Set<OrderDetail> orderDetails = new HashSet<>(); // OrderDetail 객체의 Set을 생성합니다.
	    OrderDetail orderDetail1 = new OrderDetail(); // OrderDetail 객체를 생성합니다.

	    Book book1 = new Book(2); // Book 객체를 생성하고 Book ID를 설정합니다.
	    orderDetail1.setBook(book1); // OrderDetail에 Book을 설정합니다.
	    orderDetail1.setQuantity(2); // 주문 수량을 설정합니다.
	    orderDetail1.setSubtotal(50.5f); // 소계를 설정합니다.
	    orderDetail1.setBookOrder(order); // OrderDetail에 주문을 설정합니다.

	    orderDetails.add(orderDetail1); // OrderDetail을 Order에 추가합니다.

	    Book book2 = new Book(12); // 다른 Book 객체를 생성하고 Book ID를 설정합니다.
	    OrderDetail orderDetail2 = new OrderDetail(); // 다른 OrderDetail 객체를 생성합니다.
	    orderDetail2.setBook(book2); // OrderDetail에 다른 Book을 설정합니다.
	    orderDetail2.setQuantity(1); // 다른 주문 수량을 설정합니다.
	    orderDetail2.setSubtotal(40f); // 다른 소계를 설정합니다.
	    orderDetail2.setBookOrder(order); // 다른 OrderDetail에 주문을 설정합니다.

	    orderDetails.add(orderDetail2); // 다른 OrderDetail을 Order에 추가합니다.

	    order.setOrderDetails(orderDetails); // Order에 OrderDetail을 설정합니다.

	    orderDAO.create(order); // OrderDAO를 사용하여 주문을 생성합니다.

	    assertTrue(order.getOrderId() > 0 && order.getOrderDetails().size() == 2); // 생성된 주문 및 주문 상세 정보가 예상대로 추가되었는지 테스트합니다.
	    //orderDetails의 크기는 getOrderDetails(set)은 2와 같아야
	}
//위 코드는 JUnit 테스트 케이스로, testCreateBookOrder2() 메서드를 사용하여 데이터베이스에 주문을 생성하는 테스트를 수행합니다. 주문과 관련된 객체들을 생성하고, OrderDAO를 사용하여 해당 주문을 데이터베이스에 추가합니다. 그 후에는 추가된 주문이 올바르게 생성되었는지 여부를 검증합니다.
	
	@Test
	public void testCreateBookOrder() {
	    // 새로운 책 주문을 생성하는 JUnit 테스트를 위한 메서드입니다.

	    // 새 BookOrder 객체를 생성합니다.
	    BookOrder order = new BookOrder();

	    // 새 Customer 객체를 생성하고 Customer ID를 설정합니다.
	    Customer customer = new Customer();
	    customer.setCustomerId(3);

	    // BookOrder에 Customer를 설정합니다.
	    order.setCustomer(customer);

	    // 주문자의 정보를 설정합니다.
	    order.setFirstname("Nam");
	    order.setLastname("Ha Minh");
	    order.setPhone("123456789");
	    order.setAddressLine1("123 South Street");
	    order.setAddressLine2("Clifton Park");
	    order.setCity("New York");
	    order.setState("New York");
	    order.setCountry("US");
	    order.setPaymentMethod("paypal");
	    order.setZipcode("123456");

	    // 주문에 대한 상세 정보를 포함하는 OrderDetail Set을 생성합니다.
	    Set<OrderDetail> orderDetails = new HashSet<>();

	    // 새 OrderDetail 객체를 생성합니다.
	    OrderDetail orderDetail = new OrderDetail();

	    // 주문에 포함될 Book 객체를 생성하고, Book ID를 설정합니다.
	    Book book = new Book(1);

	    // OrderDetail에 Book 및 주문 수량, 소계, 주문에 대한 BookOrder를 설정합니다.
	    orderDetail.setBook(book);
	    
	    //OrderDetailId orderDetailId = new OrderDetailId();
	    //orderDetailId.setQuantity(2);
	    //orderDetailId.setSubtotal(60.5f)
	    
	    orderDetail.setQuantity(2);
	    orderDetail.setSubtotal(68.0f);
	    orderDetail.setBookOrder(order);

	    // 생성된 OrderDetail 객체를 OrderDetails에 추가합니다.
	    orderDetails.add(orderDetail);

	    // 생성된 OrderDetail Set을 Order에 설정합니다.
	    order.setOrderDetails(orderDetails);

	    // 세금, 배송비, 소계, 총액을 설정합니다.
	    order.setTax(6.8f);
	    order.setShippingFee(2.0f);
	    order.setSubtotal(68.0f);
	    order.setTotal(76.8f);

	    // OrderDAO를 사용하여 주문을 생성합니다.
	    orderDAO.create(order);

	    // 생성된 주문의 ID가 0보다 큰지 확인합니다.
	    assertTrue(order.getOrderId() > 0);
	}
//위 코드는 testCreateBookOrder()라는 JUnit 테스트 메서드로, 주문을 생성하는 테스트를 수행합니다. 새로운 BookOrder 객체를 생성하고 관련 정보를 설정한 후, OrderDAO를 사용하여 해당 주문을 데이터베이스에 추가합니다. 그리고 추가된 주문의 ID가 0보다 큰지 확인하여 주문이 성공적으로 생성되었는지 테스트합니다.

	@Test
	public void testUpdateBookOrderShippingAddress() {
	    // 특정 주문의 배송 주소를 업데이트하는 JUnit 테스트입니다.
	    Integer orderId = 9; // 테스트할 주문 ID를 설정합니다.
	    
	    // OrderDAO를 사용하여 특정 ID의 주문을 가져옵니다.
	    BookOrder order = orderDAO.get(orderId);
	    
	    // 새로운 배송 주소를 설정합니다.
	    order.setAddressLine1("New Shipping Address");
	    
	    // OrderDAO를 사용하여 주문 정보를 업데이트합니다.
	    orderDAO.update(order);
	    
	    // 업데이트된 주문 정보를 가져옵니다.
	    BookOrder updatedOrder = orderDAO.get(orderId);
	    
	    // 새로 설정한 배송 주소와 업데이트된 주문의 배송 주소를 비교합니다.
	    assertEquals(order.getAddressLine1(), updatedOrder.getAddressLine1());
	}

	@Test
	public void testUpdateBookOrderDetail() {
	    // 특정 주문의 주문 세부 정보를 업데이트하는 JUnit 테스트입니다.
	    Integer orderId = 25; // 테스트할 주문 ID를 설정합니다.
	    
	    // OrderDAO를 사용하여 특정 ID의 주문을 가져옵니다.
	    BookOrder order = orderDAO.get(orderId);
	    
	    // 주문의 OrderDetail을 순환하면서 특정 Book ID를 가진 OrderDetail의 수량과 소계를 업데이트합니다.
	    Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
	    while (iterator.hasNext()) {
	        OrderDetail orderDetail = iterator.next();
	        if (orderDetail.getBook().getBookId() == 9) {
	            orderDetail.setQuantity(3);
	            orderDetail.setSubtotal(120);
	        }
	    }
	    
	    // OrderDAO를 사용하여 주문 정보를 업데이트합니다.
	    orderDAO.update(order);
	    
	    // 업데이트된 주문 정보를 가져옵니다.
	    BookOrder updatedOrder = orderDAO.get(orderId);
	    
	    // 업데이트된 OrderDetail의 수량과 소계를 검증하기 위한 변수들을 초기화합니다.
	    iterator = order.getOrderDetails().iterator();
	    int expectedQuantity = 3;
	    float expectedSubtotal = 120;
	    int actualQuantity = 0;
	    float actualSubtotal = 0;
	    
	    // 특정 Book ID를 가진 OrderDetail의 업데이트된 수량과 소계를 가져옵니다.
	    while (iterator.hasNext()) {
	        OrderDetail orderDetail = iterator.next();
	        if (orderDetail.getBook().getBookId() == 9) {
	            actualQuantity = orderDetail.getQuantity();
	            actualSubtotal = orderDetail.getSubtotal();
	        }
	    }
	    
	    // 예상 수량과 소계가 업데이트되었는지 검증합니다.
	    assertEquals(expectedQuantity, actualQuantity);
	    assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
	}
//	testUpdateBookOrderShippingAddress() 메서드는 특정 주문의 배송 주소를 업데이트하고 업데이트된 정보를 확인합니다.
//	testUpdateBookOrderDetail() 메서드는 특정 주문의 주문 세부 정보 중 하나를 업데이트하고 업데이트된 정보를 확인합니다.
	@Test
	public void testGet() {
	    // 주문 ID를 지정합니다.
	    Integer orderId = 25;
	    
	    // OrderDAO를 사용하여 특정 ID의 주문 정보를 가져옵니다.
	    BookOrder order = orderDAO.get(orderId);
	    
	    // 주문 정보의 여러 필드들을 출력하여 확인합니다.
	    System.out.println(order.getFirstname());
	    System.out.println(order.getLastname());
	    System.out.println(order.getPhone());
	    System.out.println(order.getAddressLine1());
	    System.out.println(order.getAddressLine2());
	    System.out.println(order.getCity());
	    System.out.println(order.getState());
	    System.out.println(order.getCountry());
	    System.out.println(order.getZipcode());
	    System.out.println(order.getStatus());
	    System.out.println(order.getSubtotal());
	    System.out.println(order.getShippingFee());
	    System.out.println(order.getTax());
	    System.out.println(order.getTotal());
	    System.out.println(order.getPaymentMethod());
	    
	    // 주문에 속한 OrderDetail의 수를 검증합니다.
	    assertEquals(1, order.getOrderDetails().size());
	}

	@Test
	public void testGetByIdAndCustomerNull() {
	    // 검색할 주문 ID와 속한 고객 ID를 설정합니다.
	    Integer orderId = 10;
	    Integer customerId = 99;
	    
	    // OrderDAO를 사용하여 특정 ID와 속한 고객 ID의 주문 정보를 가져옵니다.
	    BookOrder order = orderDAO.get(orderId, customerId);
	    
	    // 가져온 주문 정보가 null인지 확인하여 검증합니다.
	    assertNull(order);
	}

	@Test
	public void testGetByIdAndCustomerNotNull() {
	    // 검색할 주문 ID와 속한 고객 ID를 설정합니다.
	    Integer orderId = 10;
	    Integer customerId = 8;
	    
	    // OrderDAO를 사용하여 특정 ID와 속한 고객 ID의 주문 정보를 가져옵니다.
	    BookOrder order = orderDAO.get(orderId, customerId);
	    
	    // 가져온 주문 정보가 null이 아닌지 확인하여 검증합니다.
	    assertNotNull(order);
	}

//	testGet() 메서드는 특정 주문 ID에 해당하는 주문 정보를 가져와 출력하고, 그 내용을 확인합니다.
//	testGetByIdAndCustomerNull() 메서드는 특정 주문 ID와 속한 고객 ID에 해당하는 주문 정보를 가져올 때, 가져온 결과가 null인지 확인합니다.
//	testGetByIdAndCustomerNotNull() 메서드는 특정 주문 ID와 속한 고객 ID에 해당하는 주문 정보를 가져올 때, 가져온 결과가 null이 아닌지 확인합니다.
	
	@Test
	public void testDeleteOrder() {
		int orderId = 9;
		orderDAO.delete(orderId);
		
		BookOrder order = orderDAO.get(orderId);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<BookOrder> listOrders = orderDAO.listAll();
		
		for (BookOrder order : listOrders) {
			System.out.println(order.getOrderId() + " - " + order.getCustomer().getFirstname()
					+ " - " + order.getTotal() + " - " + order.getStatus());
			for (OrderDetail detail : order.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubtotal();
				System.out.println("\t" + book.getTitle() + " - " + quantity + " - " + subtotal);
			}
		}
		
		assertTrue(listOrders.size() > 0);
	}

	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId = 99;
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId = 10;
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.size() > 0);
	}	
	
	@Test
	public void testCount() {
		long totalOrders = orderDAO.count();
		assertEquals(2, totalOrders);
	}

	@Test
	public void testListMostRecentSales() {
		List<BookOrder> recentOrders = orderDAO.listMostRecentSales();
		
		assertEquals(3, recentOrders.size());
	}
}
