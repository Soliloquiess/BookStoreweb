package com.bookstore.entity;
// Generated May 22, 2018 5:46:15 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * OrderDetailId generated by hbm2java
 */
@Embeddable // OrderDetailId 클래스가 내장 가능한 엔티티임을 나타냅니다.
public class OrderDetailId implements java.io.Serializable {

	//d원래 여기 bookID랑 orderID 존재
	private Book book; // OrderDetailId 엔티티의 Book 속성입니다.
	private BookOrder bookOrder; // OrderDetailId 엔티티의 BookOrder 속성입니다.
	
	public OrderDetailId() {
	}

	@ManyToOne(fetch = FetchType.LAZY) // 다대일 관계를 맺고, 지연 로딩(Lazy loading)을 사용합니다.
	@JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false) // book_id 컬럼과 조인하며, 읽기 전용으로 설정되어 값을 추가하거나 업데이트하지 않습니다.
	public Book getBook() {
		return this.book; // 해당 OrderDetailId의 Book 객체를 반환합니다.
	}

	public void setBook(Book book) {
		this.book = book; // Book 객체를 설정합니다.
	}

	@ManyToOne(fetch = FetchType.LAZY) // 다대일 관계를 맺고, 지연 로딩(Lazy loading)을 사용합니다.
	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false) // order_id 컬럼과 조인하며, 읽기 전용으로 설정되어 값을 추가하거나 업데이트하지 않습니다.
	public BookOrder getBookOrder() {
		return this.bookOrder; // 해당 OrderDetailId의 BookOrder 객체를 반환합니다.
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder; // BookOrder 객체를 설정합니다.
	}

//	@Embeddable: OrderDetailId 클래스가 내장 가능한 엔티티임을 나타냅니다. 이 클래스는 다른 엔티티에서 내장될 수 있는 값 객체로 사용됩니다.
//	@ManyToOne(fetch = FetchType.LAZY): OrderDetailId 엔티티와 Book, BookOrder 엔티티 사이의 다대일 관계를 맺습니다. FetchType.LAZY로 설정하여, 필요할 때까지 연관된 엔티티를 로딩하지 않고 지연하여 로딩합니다.
//	@JoinColumn(name = "..."): 해당 속성이 외부 테이블의 컬럼과 조인됨을 나타내며, 여기서는 읽기 전용으로 설정되어 값 추가나 업데이트가 되지 않습니다.

	@Override
	public int hashCode() {
	    final int prime = 31; // 해시 코드를 생성하는 데 사용되는 상수 prime을 초기화합니다.
	    int result = 1; // 결과 값을 계산하기 위한 변수 result를 초기화합니다.
	    result = prime * result + ((book == null) ? 0 : book.hashCode()); // Book 객체의 해시 코드를 가져와 result에 반영합니다.
	    result = prime * result + ((bookOrder == null) ? 0 : bookOrder.hashCode()); // BookOrder 객체의 해시 코드를 가져와 result에 반영합니다.
	    return result; // 최종 계산된 해시 코드 값을 반환합니다.
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) // 객체 자체가 같은지 확인합니다.
	        return true;
	    if (obj == null) // 비교할 객체가 null인지 확인합니다.
	        return false;
	    if (getClass() != obj.getClass()) // 비교 객체가 같은 클래스인지 확인합니다.
	        return false;
	    OrderDetailId other = (OrderDetailId) obj; // 비교 대상 객체를 OrderDetailId 타입으로 캐스팅합니다.
	    if (book == null) {
	        if (other.book != null) // 현재 객체와 비교 대상 객체의 Book이 같은지 비교합니다.
	            return false;
	    } else if (!book.equals(other.book)) // Book 객체의 동등성을 확인합니다.
	        return false;
	    if (bookOrder == null) {
	        if (other.bookOrder != null) // 현재 객체와 비교 대상 객체의 BookOrder가 같은지 비교합니다.
	            return false;
	    } else if (!bookOrder.equals(other.bookOrder)) // BookOrder 객체의 동등성을 확인합니다.
	        return false;
	    return true; // 모든 조건을 통과하면 두 객체는 동일하다는 것을 반환합니다.
	}
//	hashCode(): 객체의 해시 코드를 생성하는 메서드로, Book 및 BookOrder 필드의 해시 코드 값을 활용하여 최종 해시 코드를 반환합니다.
//	equals(): 객체 간의 동등성을 확인하는 메서드로, Book 및 BookOrder 필드의 값이 동일한지 비교하여 객체가 같은지 여부를 반환합니다.
}
