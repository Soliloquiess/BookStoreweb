package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.BookOrder;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	@Override
	public BookOrder create(BookOrder order) {
	    order.setOrderDate(new Date());    // 주문일을 현재 날짜로 설정
	    order.setStatus("Processing");      // 주문 상태를 "Processing"으로 설정
	    
	    return super.create(order);         // 상위 클래스의 create 메서드를 호출하여 주문을 데이터베이스에 생성하고, 생성된 주문을 반환
	}

	@Override
	public BookOrder update(BookOrder order) {
	    return super.update(order);         // 주문을 데이터베이스에서 업데이트하고, 업데이트된 주문을 반환
	}

	@Override
	public BookOrder get(Object orderId) {
	    return super.find(BookOrder.class, orderId);  // 데이터베이스에서 주문을 orderId를 기반으로 조회하여 반환
	}

//	create(BookOrder order): 새로운 주문을 생성하는 메서드입니다. 이 메서드는 입력으로 받은 주문(Order) 객체에 주문일(orderDate)을 현재 날짜로 설정하고, 주문 상태(status)를 "Processing"으로 설정한 후, 상위 클래스의 create 메서드를 호출하여 데이터베이스에 해당 주문을 생성합니다. 그리고 생성된 주문 객체를 반환합니다.
//	update(BookOrder order): 주문을 업데이트하는 메서드입니다. 입력으로 받은 주문 객체를 데이터베이스에서 업데이트하고, 업데이트된 주문 객체를 반환합니다.
//	get(Object orderId): orderId를 기반으로 주문을 데이터베이스에서 조회하여 해당 주문 객체를 반환합니다. 이 메서드는 orderId를 받아 데이터베이스에서 주문을 찾아 반환합니다. 보통 데이터베이스의 Primary Key(기본 키)인 orderId를 사용하여 특정 주문을 가져올 때 사용됩니다.

	public BookOrder get(Integer orderId, Integer customerId) {
	    // 매개변수로 받은 orderId와 customerId를 매핑할 맵 객체 생성
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("orderId", orderId);
	    parameters.put("customerId", customerId);
	    
	    // Named Query를 사용하여 orderId와 customerId로 주문을 조회하는 메서드 호출
	    List<BookOrder> result = super.findWithNamedQuery("BookOrder.findByIdAndCustomer", parameters);
	    
	    // 조회 결과가 비어있지 않으면 첫 번째 결과를 반환, 그렇지 않으면 null 반환
	    if (!result.isEmpty()) {
	        return result.get(0);
	    }
	    return null;
	}
	
//	get(Integer orderId, Integer customerId): orderId와 customerId를 기준으로 주문(Order)을 조회하는 메서드입니다.
//	매개변수로 받은 orderId와 customerId를 매핑할 맵 객체를 생성합니다.
//	생성한 맵 객체에 orderId와 customerId를 키-값 형태로 저장합니다.
//	super.findWithNamedQuery("BookOrder.findByIdAndCustomer", parameters); 부분은 Named Query를 사용하여 orderId와 customerId를 기준으로 주문을 조회합니다.
//	조회 결과가 존재하면 첫 번째 결과를 반환하고, 비어있으면 null을 반환합니다.
	
	@Override
	public void delete(Object orderId) {
	    // 주어진 orderId를 기반으로 주문(Order)을 데이터베이스에서 삭제
	    super.delete(BookOrder.class, orderId);     
	}
//	delete(Object orderId): 주어진 orderId를 기반으로 주문(Order)을 데이터베이스에서 삭제하는 메서드입니다.
//	super.delete(BookOrder.class, orderId);는 상위 클래스의 delete 메서드를 호출하여 orderId에 해당하는 주문을 데이터베이스에서 삭제합니다.

	@Override
	public List<BookOrder> listAll() {
	    // BookOrder 엔티티의 모든 주문을 조회하여 리스트로 반환
	    return super.findWithNamedQuery("BookOrder.findAll");
	}

	@Override
	public long count() {   
	    // BookOrder 엔티티의 전체 주문 수를 조회하여 반환
	    return super.countWithNamedQuery("BookOrder.countAll");
	}

	public List<BookOrder> listByCustomer(Integer customerId) {
	    // 특정 customerId를 가진 고객의 주문 목록을 조회하여 리스트로 반환
	    return super.findWithNamedQuery("BookOrder.findByCustomer", "customerId", customerId);
	}

	public List<BookOrder> listMostRecentSales() {
	    // 최근에 발생한 주문들 중에서 최신 3개를 조회하여 리스트로 반환
	    return super.findWithNamedQuery("BookOrder.findAll", 0, 3);
	}

	// OrderDAO 클래스에 구현되는 메서드입니다. OrderDAO 클래스가 어떤 클래스를 상속하고 있기 때문에 super 키워드를 사용하여 상위 클래스의 메서드를 호출합니다.

	public long countOrderDetailByBook(int bookId) {
	    // super 키워드를 사용하여 OrderDAO 클래스가 상속한 상위 클래스의 countWithNamedQuery 메서드를 호출합니다.
	    // "OrderDetail.countByBook"이라는 이름의 Named Query를 사용하여 bookId를 바인딩하고 해당 책(bookId)과 연관된 OrderDetail 개체의 수를 반환합니다.
	    return super.countWithNamedQuery("OrderDetail.countByBook", "bookId", bookId);
	}
	//countOrderDetailByBook(int bookId) :해당 메서드는 OrderDAO 클래스에 속하는 메서드로, 상속된 클래스의 메서드를 활용하여 "OrderDetail.countByBook"이라는 이름의 Named Query를 호출합니다. 이 쿼리는 bookId를 바인딩하여 해당 책(bookId)과 연관된 OrderDetail 개체의 수를 반환합니다.
	
	
	// OrderDAO 클래스에 속한 메서드로, 상위 클래스(super)의 countWithNamedQuery 메서드를 호출하여 사용합니다.
	public long countByCustomer(int customerId) {
	    // 상위 클래스의 countWithNamedQuery 메서드를 호출하여 "BookOrder.countByCustomer"이라는 이름의 Named Query를 실행합니다.
	    // 이 쿼리는 customerId를 바인딩하여 해당 고객과 연관된 주문의 수를 반환합니다.
	    return super.countWithNamedQuery("BookOrder.countByCustomer", "customerId", customerId);
	}
//해당 메서드는 OrderDAO 클래스에 속한 메서드로, 상위 클래스의 countWithNamedQuery 메서드를 호출하여 "BookOrder.countByCustomer"라는 이름의 Named Query를 실행합니다. 이 쿼리는 customerId를 바인딩하여 해당 고객과 연관된 주문의 수를 반환합니다.
	
//	listAll(): 데이터베이스에서 모든 주문을 조회하는 메서드입니다. super.findWithNamedQuery("BookOrder.findAll")를 통해 "BookOrder.findAll"이라는 이름의 Named Query를 실행하여 모든 주문을 리스트로 반환합니다.
//
//	count(): 데이터베이스에 저장된 전체 주문의 수를 조회하는 메서드입니다. super.countWithNamedQuery("BookOrder.countAll")를 통해 "BookOrder.countAll"이라는 이름의 Named Query를 실행하여 전체 주문의 수를 반환합니다.
//
//	listByCustomer(Integer customerId): 특정 customerId를 가진 고객의 주문 목록을 조회하는 메서드입니다. super.findWithNamedQuery("BookOrder.findByCustomer", "customerId", customerId)를 통해 "BookOrder.findByCustomer"이라는 Named Query를 실행하여 특정 customerId에 해당하는 주문 목록을 리스트로 반환합니다.
//
//	listMostRecentSales(): 최근에 발생한 주문들 중에서 최신 3개를 조회하는 메서드입니다. super.findWithNamedQuery("BookOrder.findAll", 0, 3)를 통해 "BookOrder.findAll"이라는 Named Query를 실행하여 최근 3개의 주문을 리스트로 반환합니다.	
}
