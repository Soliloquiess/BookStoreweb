package com.bookstore.entity;
// Generated May 22, 2018 5:46:15 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * OrderDetail generated by hbm2java
 */
@Entity // JPA에서 엔티티 클래스임을 나타냅니다.
@Table(name = "order_detail", catalog = "bookstoredb") // "order_detail" 테이블에 매핑되고 "bookstoredb" 카탈로그에 속합니다.
@NamedQueries({ // 명명된 쿼리를 정의하는 부분입니다.
    @NamedQuery( // 명명된 쿼리를 선언합니다.
        name = "OrderDetail.bestSelling", // 쿼리의 이름을 "OrderDetail.bestSelling"으로 지정합니다.
        query = "SELECT od.book FROM OrderDetail od GROUP by od.book.bookId " // 판매 수량을 기준으로 가장 많이 팔린 상품을 검색하는 쿼리입니다.
                + "ORDER BY SUM(od.quantity) DESC" // 판매 수량을 합산한 후 내림차순으로 정렬합니다.
    ),
    
 // OrderDetail 엔티티에 대한 이름이 "OrderDetail.countByBook"으로 지정된 Named Query입니다.
 // "OrderDetail.countByBook"은 나중에 EntityManager에서 해당 쿼리를 참조할 때 사용됩니다.
 @NamedQuery(name = "OrderDetail.countByBook",
             query = "SELECT COUNT(*) FROM OrderDetail od WHERE od.book.bookId = :bookId")
//위의 코드에서 사용된 @NamedQuery 어노테이션은 JPA (Java Persistence API)에서 Named Query를 정의할 때 사용됩니다. "OrderDetail.countByBook"이라는 이름으로 쿼리가 등록되며, 이 쿼리는 OrderDetail 엔티티의 bookId를 기준으로 연관된 OrderDetail 개체의 수를 가져옵니다. "od"는 OrderDetail 엔티티를 나타내는 별칭(alias)이며, ":bookId"는 나중에 바인딩되는 파라미터입니다.
})

//위 코드는 JPA에서 사용되는 엔티티 클래스를 정의하고, 해당 엔티티에 대한 명명된 쿼리를 정의합니다.
//
//@Entity: JPA에서 엔티티 클래스임을 나타냅니다.
//@Table(name = "order_detail", catalog = "bookstoredb"): "order_detail" 테이블에 매핑되며, "bookstoredb" 카탈로그에 속합니다.
//@NamedQueries({}): 명명된 쿼리를 정의하는 부분입니다.
//@NamedQuery: 명명된 쿼리를 선언합니다.
//name = "OrderDetail.bestSelling": 쿼리의 이름을 "OrderDetail.bestSelling"으로 지정합니다.
//query = "SELECT od.book FROM OrderDetail od GROUP by od.book.bookId ORDER BY SUM(od.quantity) DESC": 판매 수량을 기준으로 가장 많이 팔린 상품을 검색하는 쿼리입니다. 판매 수량을 합산하여 내림차순으로 정렬합니다.

public class OrderDetail implements java.io.Serializable {

	private OrderDetailId id = new OrderDetailId(); // OrderDetailId 클래스를 사용하여 id를 초기화합니다.

	private Book book; // OrderDetail에 연결된 Book 엔티티를 나타냅니다.

	private BookOrder bookOrder; // OrderDetail에 연결된 BookOrder 엔티티를 나타냅니다.

	private int quantity; // 주문 상세의 수량을 나타냅니다.

	private float subtotal;	// 각 주문 상세의 소계 금액을 나타냅니다.

	public OrderDetail() {
	    // 기본 생성자
	}

	public OrderDetail(OrderDetailId id) {
	    this.id = id; // OrderDetailId를 매개변수로 받는 생성자로, id를 설정합니다.
	}

	public OrderDetail(OrderDetailId id, Book book, BookOrder bookOrder, int quantity, float subtotal) {
	    this.id = id; // id, book, bookOrder, quantity, subtotal을 받아 초기화하는 생성자입니다.
	    this.book = book;
	    this.bookOrder = bookOrder;
	    this.quantity = quantity;
	    this.subtotal = subtotal;
	}

//	private OrderDetailId id = new OrderDetailId();: OrderDetailId 클래스를 사용하여 id를 초기화합니다.
//	private Book book;: OrderDetail에 연결된 Book 엔티티를 나타냅니다.
//	private BookOrder bookOrder;: OrderDetail에 연결된 BookOrder 엔티티를 나타냅니다.
//	private int quantity;: 주문 상세의 수량을 나타냅니다.
//	private float subtotal;: 각 주문 상세의 소계 금액을 나타냅니다.
//	생성자들:
//	public OrderDetail(): 기본 생성자입니다.
//	public OrderDetail(OrderDetailId id): OrderDetailId를 매개변수로 받는 생성자로, id를 설정합니다.
//	public OrderDetail(OrderDetailId id, Book book, BookOrder bookOrder, int quantity, float subtotal): id, book, bookOrder, quantity, subtotal을 받아 초기화하는 생성자입니다.

	@EmbeddedId // 복합 기본 키를 사용하는데, 이를 나타내기 위해 사용됩니다.

	@AttributeOverrides({ // 복합 기본 키 클래스의 매핑 정보를 재정의합니다.
	    @AttributeOverride(name = "orderId", column = @Column(name = "order_id", nullable = false)), // orderId를 order_id 컬럼과 매핑합니다.
	    @AttributeOverride(name = "bookId", column = @Column(name = "book_id", nullable = false)) // bookId를 book_id 컬럼과 매핑합니다.
	})
	public OrderDetailId getId() {
	    return this.id; // OrderDetailId 객체를 반환합니다.
	}

	public void setId(OrderDetailId id) {
	    this.id = id; // OrderDetailId를 설정합니다.
	}

	@ManyToOne(fetch = FetchType.EAGER) // ManyToOne 관계를 나타내며, 즉시 로딩 방식을 사용합니다.
	@JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false) // book 엔티티와 조인합니다.
	public Book getBook() {
	    return this.book; // 해당 OrderDetail의 Book 객체를 반환합니다.
	}

	public void setBook(Book book) {
	    this.book = book; // Book 객체를 설정합니다.
	    this.id.setBook(book); // OrderDetailId에 속한 Book을 설정하여 연관 관계를 유지합니다.
	}

//	위 코드는 OrderDetail 클래스에서의 매핑을 보여줍니다.
//
//	@EmbeddedId: 복합 기본 키를 사용하기 위해 표시합니다.
//	@AttributeOverrides: 복합 기본 키 클래스의 매핑 정보를 재정의합니다. orderId는 order_id 컬럼과, bookId는 book_id 컬럼과 매핑됩니다.
//	public OrderDetailId getId() { ... }: OrderDetailId 객체를 반환합니다.
//	public void setId(OrderDetailId id) { ... }: OrderDetailId를 설정합니다.
//	@ManyToOne: Book 엔티티와의 다대일 관계를 나타냅니다. fetch = FetchType.EAGER를 사용하여 즉시 로딩을 설정합니다.
//	@JoinColumn: 조인 컬럼의 설정을 나타냅니다. name 속성은 조인할 컬럼을, insertable, updatable, nullable 등의 속성은 컬럼의 제약을 설정합니다.
//	public Book getBook() { ... }: 해당 OrderDetail의 Book 객체를 반환합니다.
//	public void setBook(Book book) { ... }: Book 객체를 설정하고, OrderDetailId의 Book도 설정하여 연관 관계를 유지합니다.

	@ManyToOne(fetch = FetchType.LAZY) // OrderDetail 엔티티와 다대일 관계를 맺습니다. 지연 로딩(Lazy loading)을 사용합니다.
	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false) // order_id 컬럼과 조인하며, 읽기 전용으로 설정되어 값을 업데이트하지 않습니다.
	public BookOrder getBookOrder() {
	    return this.bookOrder; // 해당 OrderDetail의 BookOrder 객체를 반환합니다.
	}

	public void setBookOrder(BookOrder bookOrder) {
	    this.bookOrder = bookOrder; // BookOrder 객체를 설정합니다.
	    this.id.setBookOrder(bookOrder); // OrderDetailId에 속한 BookOrder를 설정하여 연관 관계를 유지합니다.
	}

	@Column(name = "quantity", nullable = false) // quantity 컬럼을 매핑하며, 널을 허용하지 않습니다.
	public int getQuantity() {
	    return this.quantity; // 해당 OrderDetail의 수량을 반환합니다.
	}

	public void setQuantity(int quantity) {
	    this.quantity = quantity; // 수량을 설정합니다.
	}

	@Column(name = "subtotal", nullable = false, precision = 12, scale = 0) // subtotal 컬럼을 매핑하며, 널을 허용하지 않습니다.
	public float getSubtotal() {
	    return this.subtotal; // 해당 OrderDetail의 소계를 반환합니다.
	}

	public void setSubtotal(float subtotal) {
	    this.subtotal = subtotal; // 소계를 설정합니다.
	}
//	위 코드는 OrderDetail 클래스의 일부로, 각 주석에 따라 필드 및 매핑 정보를 설명합니다.
//
//	@ManyToOne(fetch = FetchType.LAZY): OrderDetail 엔티티와 BookOrder 엔티티 사이의 다대일 관계를 맺습니다. FetchType.LAZY로 설정하여, 필요할 때까지 연관된 엔티티를 로딩하지 않고 지연하여 로딩합니다.
//	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false): order_id 컬럼과 조인하며, 읽기 전용으로 설정되어 있어 값을 추가하거나 업데이트하지 않습니다.
//	public int getQuantity() { ... }: 해당 OrderDetail의 수량을 반환합니다.
//	public void setQuantity(int quantity) { ... }: 수량을 설정합니다.
//	public float getSubtotal() { ... }: 해당 OrderDetail의 소계를 반환합니다.
}
