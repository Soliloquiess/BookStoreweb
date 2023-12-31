package com.bookstore.entity;
// Generated May 22, 2018 5:46:15 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Customer generated by hbm2java
 */
@Entity // 해당 클래스를 JPA Entity로 지정하여 데이터베이스의 테이블과 매핑하는 역할을 합니다.
@Table(name = "customer", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
// "customer"라는 테이블명과 "bookstoredb"라는 카탈로그(데이터베이스)에서 엔티티를 매핑합니다.
// "email" 컬럼에 대한 고유 제약 조건을 설정하여 중복되지 않는 고유한 이메일이어야 합니다.
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c ORDER BY c.registerDate DESC"),
    // "Customer.findAll"이라는 이름으로 모든 고객을 등록 날짜(registerDate)를 기준으로 내림차순으로 조회하는 쿼리를 정의합니다.
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    // "Customer.findByEmail"이라는 이름으로 이메일을 기준으로 고객을 조회하는 쿼리를 정의합니다.
    @NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c.email) FROM Customer c"),
    // "Customer.countAll"이라는 이름으로 모든 고객의 수를 조회하는 쿼리를 정의합니다.
    @NamedQuery(name = "Customer.checkLogin", query = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :pass")
    // "Customer.checkLogin"이라는 이름으로 이메일과 패스워드를 기준으로 로그인을 확인하는 쿼리를 정의합니다.
})

//이 코드 블록은 Customer 엔티티를 정의하고, 해당 엔티티와 관련된 명명된 쿼리를 정의합니다. @Entity 어노테이션은 해당 클래스를 JPA 엔티티로 지정하며, @Table 어노테이션은 데이터베이스 테이블과의 매핑을 정의합니다. @NamedQueries 어노테이션은 여러 명명된 쿼리들을 정의하고 있습니다. 이 쿼리들은 엔티티에 대한 다양한 검색 및 조작 작업을 가능하게 합니다.

//@Entity: 해당 클래스를 JPA Entity로 지정합니다. 데이터베이스와 매핑되는 엔티티임을 나타냅니다.
//@Table(name = "customer", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "email")): 데이터베이스 테이블 이름과 스키마를 정의합니다. uniqueConstraints는 email 컬럼에 대한 고유 제약 조건을 정의합니다.
//@NamedQueries({ ... }): 엔티티에 정적 쿼리를 정의합니다. 각각의 @NamedQuery 어노테이션은 이름과 쿼리를 매핑합니다.
//Customer.findAll: 모든 고객을 등록 날짜를 기준으로 내림차순으로 조회하는 쿼리를 정의합니다.
//Customer.findByEmail: 이메일을 기준으로 고객을 조회하는 쿼리를 정의합니다.
//Customer.countAll: 모든 고객의 수를 조회하는 쿼리를 정의합니다.
//Customer.checkLogin: 이메일과 패스워드를 기준으로 로그인을 확인하는 쿼리를 정의합니다.
//이러한 어노테이션들은 Customer 엔티티를 데이터베이스와 연결하고, 특정 작업을 수행하기 위한 쿼리를 정의합니다.

public class Customer implements java.io.Serializable {

	private Integer customerId;
	private String email;
	private String firstname;
	private String lastname;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String phone;
	private String zipcode;
	private String password;
	private Date registerDate;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<BookOrder> bookOrders = new HashSet<BookOrder>(0);

	public Customer() {
	}

	public Customer(String email, String firstname, String lastname, String address1, String address2, 
			String city, String state, String country, String phone,
			String zipcode, String password, Date registerDate) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.addressLine1 = address1;
		this.addressLine2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.phone = phone;
		this.zipcode = zipcode;
		this.password = password;
		this.registerDate = registerDate;
	}
	
//	
//	public Customer(String email, String firstname, String lastname, String address , String address1, String address2, 
//			String city, String state, String country, String phone,
//			String zipcode, String password, Date registerDate) {
//		this.email = email;
//		this.firstname = firstname;
//		this.lastname = lastname;
////		this.addressLine = address;
//		this.addressLine1 = address1;
//		this.addressLine2 = address2;
//		this.city = city;
//		this.state = state;
//		this.country = country;
//		this.phone = phone;
//		this.zipcode = zipcode;
//		this.password = password;
//		this.registerDate = registerDate;
//	}

	public Customer(String email, String firstname, String lastname, String address1, String address2, 
			String city, String state, String country, String phone,
			String zipcode, String password, Date registerDate, Set<Review> reviews, Set<BookOrder> bookOrders) {
		this(email, firstname, lastname, address1, address2, city, state,
				country, phone, zipcode, password, registerDate);
		this.reviews = reviews;
		this.bookOrders = bookOrders;
	}

	@Id // 해당 어노테이션은 엔티티 클래스의 기본 키(primary key) 필드를 지정합니다.
	@GeneratedValue(strategy = IDENTITY) // 데이터베이스가 자동으로 고유 키 값을 생성하도록 지정합니다.

	@Column(name = "customer_id", unique = true, nullable = false)
	// 데이터베이스 테이블의 'customer_id' 칼럼과 해당 필드를 매핑하며, 고유하고 널을 허용하지 않는 필드입니다.
	public Integer getCustomerId() {
	    return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
	    this.customerId = customerId;
	}

	@Column(name = "email", unique = true, nullable = false, length = 64)
	// 'email' 칼럼과 해당 필드를 매핑하며, 중복되지 않는 고유한 이메일 주소를 저장하는 필드입니다.
	public String getEmail() {
	    return this.email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}
//해당 코드는 Customer 엔티티 클래스의 기본 키와 이메일 필드를 정의합니다. @Id 어노테이션은 엔티티의 기본 키(primary key) 필드임을 나타내며, @GeneratedValue 어노테이션은 데이터베이스에서 해당 키 값을 자동으로 생성하도록 지정합니다. @Column 어노테이션은 엔티티 클래스의 필드를 데이터베이스 테이블의 칼럼과 매핑하며, 각 필드의 제약 조건을 설정합니다. customer_id는 고유하며 널을 허용하지 않는 필드로, email은 중복되지 않는 고유한 이메일 주소를 저장하는 필드로 정의되어 있습니다.

	@Column(name = "firstname", nullable = false, length = 30)
	// 'firstname'은 데이터베이스의 칼럼과 해당 필드를 매핑하며, 널 값을 허용하지 않는 30자 이내의 이름 필드입니다.
	public String getFirstname() {
	    return this.firstname;
	}

	public void setFirstname(String firstname) {
	    this.firstname = firstname;
	}

	@Transient
	// 'getFullname()' 메서드는 임시(transient)로 데이터베이스와 관련 없이 사용되는 필드로, 이름과 성을 조합한 전체 이름을 반환합니다.
	public String getFullname() {
	    return this.firstname + " " + this.lastname;
	}

	@Column(name = "lastname", nullable = false, length = 30)
	// 'lastname'은 데이터베이스의 칼럼과 해당 필드를 매핑하며, 널 값을 허용하지 않는 30자 이내의 성(성할머니, 성어머니 등) 필드입니다.
	public String getLastname() {
	    return this.lastname;
	}

	public void setLastname(String lastname) {
	    this.lastname = lastname;
	}
	
//해당 코드는 Customer 엔티티 클래스에서 이름과 성을 저장하는 데 사용되는 필드와 메서드입니다. @Column 어노테이션은 데이터베이스의 칼럼과 해당 필드를 매핑하며, 각각 이름과 성을 저장하는 데 사용됩니다. 또한 @Transient 어노테이션은 임시로 데이터베이스에 저장되지 않고, 오직 메모리 내에서만 사용되는 필드나 메서드임을 나타냅니다. getFullname() 메서드는 이름과 성을 조합하여 전체 이름을 반환하는 메서드로, 데이터베이스와는 무관한 임시 필드입니다.

	//@Column(name = "address_line1", nullable = false, length = 128)
	@Column(name = "addressLine1", nullable = false, length = 128)
	// 'addressLine1'은 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 128자 이내의 주소 필드입니다.
	public String getAddressLine1() {
	    return this.addressLine1;
	}

	public void setAddressLine1(String address1) {
	    this.addressLine1 = address1;
	}

	//@Column(name = "address_line2", nullable = false, length = 128)
	@Column(name = "addressLine2", nullable = false, length = 128)
	// 'addressLine2'은 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 128자 이내의 주소 필드입니다.
	public String getAddressLine2() {
	    return this.addressLine2;
	}

	public void setAddressLine2(String address2) {
	    this.addressLine2 = address2;
	}

	@Column(name = "city", nullable = false, length = 32)
	// 'city'는 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 32자 이내의 도시 필드입니다.
	public String getCity() {
	    return this.city;
	}

	public void setCity(String city) {
	    this.city = city;
	}

//위 코드는 주소 관련 필드를 저장하는 Customer 엔티티 클래스의 일부를 나타냅니다. 각각의 @Column 어노테이션은 데이터베이스의 칼럼과 매핑되는 필드를 정의하고, 해당 필드들은 각각 주소 라인 1, 주소 라인 2, 도시 정보를 저장합니다. 널 값을 허용하지 않는 문자열 형식의 필드로, 각각 최대 길이 제한이 있는 것으로 보입니다. 주석 내용은 이를 설명하고 있습니다.

	@Column(name = "state", nullable = false, length = 45)
	// 'state'는 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 45자 이내의 주(state) 필드입니다.
	public String getState() {
	    return state;
	}

	public void setState(String state) {
	    this.state = state;
	}

	@Column(name = "country", nullable = false, length = 4)
	// 'country'는 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 4자 이내의 국가(country) 코드 필드입니다.
	public String getCountry() {
	    return this.country;
	}

	public void setCountry(String country) {
	    this.country = country;
	}

	@Transient
	// 'getCountryName'은 국가 코드를 기반으로 국가명을 반환하는 메서드로, 임시 데이터로 취급하여 데이터베이스에 저장되지 않습니다.
	public String getCountryName() {
	    return new Locale("", this.country).getDisplayCountry();
	}

	@Column(name = "phone", nullable = false, length = 15)
	// 'phone'은 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 15자 이내의 전화번호 필드입니다.
	public String getPhone() {
	    return this.phone;
	}

	public void setPhone(String phone) {
	    this.phone = phone;
	}

//위 코드는 Customer 엔티티 클래스의 일부로, 각각의 어노테이션은 데이터베이스의 칼럼과 매핑되는 필드를 정의합니다. state와 country 필드는 널 값을 허용하지 않는 문자열 필드이며, 각각 최대 길이 제한이 있는 것으로 보입니다. getCountryName 메서드는 국가 코드를 기반으로 국가명을 반환하며, @Transient 어노테이션이 붙어 데이터베이스에 저장되지 않는 임시 필드로 보입니다. phone 필드도 널 값을 허용하지 않는 문자열 필드로 보이며 최대 길이 제한이 있습니다. 주석 내용은 각 필드와 메서드의 역할과 속성을 설명하고 있습니다.

	@Column(name = "zipcode", nullable = false, length = 24)
	// 'zipcode'는 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 24자 이내의 우편번호(zip code) 필드입니다.
	public String getZipcode() {
	    return this.zipcode;
	}

	public void setZipcode(String zipcode) {
	    this.zipcode = zipcode;
	}

	@Column(name = "password", nullable = false, length = 16)
	// 'password'는 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 16자 이내의 비밀번호(password) 필드입니다.
	public String getPassword() {
	    return this.password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date", nullable = false, length = 19)
	// 'registerDate'는 데이터베이스의 칼럼과 매핑되는, 널 값을 허용하지 않는 날짜/시간(register date) 필드입니다.
	public Date getRegisterDate() {
	    return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
	    this.registerDate = registerDate;
	}

//	위 코드는 Customer 엔티티 클래스의 일부로, 각각의 어노테이션은 데이터베이스의 칼럼과 매핑되는 필드를 정의합니다. zipcode는 널 값을 허용하지 않는 문자열 필드이며 최대 길이 제한이 있는 것으로 보입니다. password 필드 역시 널 값을 허용하지 않는 문자열 필드이며 최대 길이 제한이 있는 것으로 보입니다. registerDate 필드는 날짜와 시간 정보를 저장하는 필드로 보이며 널 값을 허용하지 않으며, TemporalType.TIMESTAMP로 지정된 데이터베이스의 날짜/시간 타입과 매핑되는 것으로 보입니다. 주석 내용은 각 필드의 역할과 데이터베이스와의 매핑 정보를 설명하고 있습니다.
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	// 'reviews'는 여러 리뷰(Review)를 포함하는, 'Customer' 엔티티와 매핑된 연관 관계 필드입니다.
	public Set<Review> getReviews() {
	    return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
	    this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	// 'bookOrders'는 여러 도서 주문(BookOrder)을 포함하는, 'Customer' 엔티티와 매핑된 연관 관계 필드입니다.
	public Set<BookOrder> getBookOrders() {
	    return this.bookOrders;
	}

	public void setBookOrders(Set<BookOrder> bookOrders) {
	    this.bookOrders = bookOrders;
	}
//위 코드는 Customer 엔티티 클래스의 일부로, @OneToMany 어노테이션은 1:N 연관 관계를 정의합니다. getReviews() 메서드와 setReviews() 메서드는 'Customer' 엔티티와 매핑된 리뷰(Review)의 집합인 'reviews'를 가져오고 설정합니다. 비슷하게, getBookOrders() 메서드와 setBookOrders() 메서드는 'Customer' 엔티티와 매핑된 도서 주문(BookOrder)의 집합인 'bookOrders'를 가져오고 설정합니다. 이들은 지연 로딩(FetchType.LAZY)으로 설정되어 있으며, 'customer' 필드에 대한 매핑 정보를 mappedBy 속성으로 정의하고 있습니다.

}
