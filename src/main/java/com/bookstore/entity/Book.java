package com.bookstore.entity;
// Hibernate Tools 5.2.10.Final에 의해 2018년 5월 22일 오전 5시 46분 15초에 생성됨

import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.swing.event.TreeSelectionEvent;

/**
 * hbm2java에 의해 생성된 Book 클래스
 */
@Entity
@Table(name = "book", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@NamedQueries({
    // 모든 책을 선택하기 위한 이름 있는 쿼리
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    // 제목으로 책을 찾기 위한 이름 있는 쿼리
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    // 모든 책의 수를 세기 위한 이름 있는 쿼리
    @NamedQuery(name = "Book.countAll", query = "SELECT COUNT(*) FROM Book b"),
    // 카테고리 ID별 책의 수를 세기 위한 이름 있는 쿼리
    @NamedQuery(name = "Book.countByCategory", query = "SELECT COUNT(b) FROM Book b "
            + "WHERE b.category.categoryId = :catId"),
    // 카테고리 ID별로 책을 찾기 위한 이름 있는 쿼리
    @NamedQuery(name = "Book.findByCategory", query = "SELECT b FROM Book b JOIN "
            + "Category c ON b.category.categoryId = c.categoryId AND c.categoryId = :catId"),
    // 출판 날짜를 기준으로 내림차순으로 새로운 책 목록을 정렬하는 이름 있는 쿼리
    @NamedQuery(name = "Book.listNew", query = "SELECT b FROM Book b ORDER BY b.publishDate DESC"),
    // 키워드를 사용하여 제목, 저자 또는 설명으로 책을 검색하는 이름 있는 쿼리
    @NamedQuery(name = "Book.search", query = "SELECT b FROM Book b WHERE b.title LIKE '%' || :keyword || '%'"
            + " OR b.author LIKE '%' || :keyword || '%'"
            + " OR b.description LIKE '%' || :keyword || '%'")
    //참고로 위 쿼리에서 LIKE 했을때 % 없으면 완전히 동일해야 한다. sql문 생각하면 됨
})
public class Book implements java.io.Serializable {

    private Integer bookId;
    private Category category;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private byte[] image;
    private String base64Image;
    private float price;
    private Date publishDate;
    private Date lastUpdateTime;
    private Set<Review> reviews = new HashSet<Review>(0);
    private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	// 기본 생성자
    public Book() {
    }

	// ID를 사용하는 생성자
    public Book(Integer bookId) {
        super();
        this.bookId = bookId;
    }

	// Book 엔티티를 초기화하는 생성자
    public Book(Category category, String title, String author, String description, String isbn, byte[] image,
            float price, Date publishDate, Date lastUpdateTime) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.image = image;
        this.price = price;
        this.publishDate = publishDate;
        this.lastUpdateTime = lastUpdateTime;
    }

	// 모든 필드를 가진 생성자
    public Book(Category category, String title, String author, String description, String isbn, byte[] image,
            float price, Date publishDate, Date lastUpdateTime, Set<Review> reviews, Set<OrderDetail> orderDetails) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.image = image;
        this.price = price;
        this.publishDate = publishDate;
        this.lastUpdateTime = lastUpdateTime;
        this.reviews = reviews;
        this.orderDetails = orderDetails;
    }

    @Id // 해당 필드를 엔티티의 기본 키로 지정
    @GeneratedValue(strategy = IDENTITY) // 자동 생성되는 기본 키 설정
    @Column(name = "book_id", unique = true, nullable = false) // 데이터베이스 칼럼과 매핑 설정
    // bookId의 Getter와 Setter
    public Integer getBookId() {
        return this.bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @ManyToOne(fetch = FetchType.EAGER)// 다대일(N:1) 관계 설정
    @JoinColumn(name = "category_id", nullable = false)// 외래 키에 대한 매핑
    // category의 Getter와 Setter
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "title", unique = true, nullable = false, length = 128)// 데이터베이스 칼럼과 매핑 설정
    // title의 Getter와 Setter
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false, length = 64)// 데이터베이스 칼럼과 매핑 설정
    // author의 Getter와 Setter
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "description", nullable = false, length = 16777215)// 데이터베이스 칼럼과 매핑 설정
    // description의 Getter와 Setter
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "isbn", nullable = false, length = 15) // 데이터베이스 칼럼과 매핑 설정
    // isbn의 Getter와 Setter
    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name = "image", nullable = false) // 데이터베이스 칼럼과 매핑 설정
    // image의 Getter와 Setter
    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "price", nullable = false, precision = 12, scale = 0) // 데이터베이스 칼럼과 매핑 설정
    // price의 Getter와 Setter
    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date", nullable = false, length = 10) // 데이터베이스 칼럼과 매핑 설정
    // publishDate의 Getter와 Setter
    public Date getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false, length = 19)// 데이터베이스 칼럼과 매핑 설정
    // lastUpdateTime의 Getter와 Setter
    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	 // OneToMany 어노테이션을 사용하여 관계를 표시하고, FetchType을 EAGER로 설정하여 책(book)과 리뷰(Review)의 일대다 관계를 매핑합니다.
	 // "book" 속성을 기준으로 매핑되는 엔티티에 대한 설정입니다.
	
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	 public Set<Review> getReviews() {
	     // 리뷰를 내림차순으로 정렬하는 메서드입니다.
	
	     // TreeSet을 사용하여 Comparator를 구현하여 리뷰(Review)를 내림차순으로 정렬합니다.
	     TreeSet<Review> sortedReviews = new TreeSet<>(new Comparator<Review>() {
	         @Override
	         public int compare(Review review1, Review review2) {
	             // Review 객체의 시간을 비교하여 내림차순으로 정렬합니다.
	             return review2.getReviewTime().compareTo(review1.getReviewTime());
	         }
	     });
	     
	     // 정렬된 리뷰를 기존의 Set에 추가합니다.
	     sortedReviews.addAll(reviews);
	     return sortedReviews; // 정렬된 리뷰를 반환합니다.
	 }
	
	 // 리뷰(Review)의 Set을 설정하는 메서드입니다.
	 public void setReviews(Set<Review> reviews) {
	     this.reviews = reviews; // 주어진 리뷰(Review) Set을 현재의 리뷰 속성에 설정합니다.
	 }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    // orderDetails의 Getter와 Setter
    public Set<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    @Transient //한마디로 일시적으로 필드를 만듬
    //@Transient로 표시된 메서드는 Hibernate에 의해 구문 분석 되지 않음 
	//@Transient는 자바 표준 표시 어노테이션(annotation) 중 하나입니다. 이 어노테이션은 JPA(Java Persistence API)에서 사용되며, 데이터베이스와의 상호 작용 중 특정 필드를 영속화(Persistence)하지 않도록 지시합니다.
	//JPA는 Java 객체와 관계형 데이터베이스 간의 매핑을 단순화하고 개발자가 객체를 데이터베이스에 저장하고 조회할 수 있도록 지원하는 기술입니다. 이때, Java 객체의 필드 중 일부를 데이터베이스에 매핑하지 않고 무시하고 싶을 때 @Transient 어노테이션을 사용합니다.
	//예를 들어, 특정 필드가 데이터베이스 테이블에 저장되지 않아야 하거나, 영속화되지 않아야 할 때 해당 필드에 @Transient 어노테이션을 붙여 JPA에게 그 필드를 무시하도록 지시할 수 있습니다.
    // base64Image의 Getter
    public String getBase64Image() {        // 이미지를 Base64로 변환하여 반환하는 메서드
        this.base64Image = Base64.getEncoder().encodeToString(this.image);
        return this.base64Image;
    }
    
    @Transient
    // base64Image의 Setter
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Transient
    // 평균 평점을 반환하는 메서드
    // 리뷰들의 평균 평점을 계산하는 메서드
    public float getAverageRating() {
        float averageRating = 0.0f;
        float sum = 0.0f;
        
        if (reviews.isEmpty()) {
            return 0.0f;
        }
        
        for (Review review : reviews) {
            sum += review.getRating();
        }
        
        averageRating = sum / reviews.size();
        
        return averageRating;
    }

    @Transient
    // 평점을 별 모양의 문자열로 반환하는 메서드
    public String getRatingStars() {
        float averageRating = getAverageRating();
        
        return getRatingString(averageRating);
    }

    @Transient
    // 주어진 평균 평점에 따라 별 모양의 문자열을 생성하는 메서드
    public String getRatingString(float averageRating) {
        String result = "";
        
        int numberOfStarsOn = (int) averageRating;
        
        for (int i = 1; i <= numberOfStarsOn; i++) {
            result += "on,";
        }
        
        int next = numberOfStarsOn + 1;
        
        if (averageRating > numberOfStarsOn) {
            result += "half,";
            next++;
        }
        
        for (int j = next; j <= 5; j++) {
            result += "off,";
        }
        
        return result.substring(0, result.length() - 1);
    }
    
    
    @Override
    // hashCode 메서드 재정의
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        return result;
    }

    @Override
    // equals 메서드 재정의
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        return true;
    }
}
