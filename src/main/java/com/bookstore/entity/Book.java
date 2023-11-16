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

    public Book() {
    }
    
    public Book(Integer bookId) {
        super();
        this.bookId = bookId;
    }

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

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "book_id", unique = true, nullable = false)
    // bookId의 Getter와 Setter
    public Integer getBookId() {
        return this.bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    // category의 Getter와 Setter
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "title", unique = true, nullable = false, length = 128)
    // title의 Getter와 Setter
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false, length = 64)
    // author의 Getter와 Setter
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "description", nullable = false, length = 16777215)
    // description의 Getter와 Setter
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "isbn", nullable = false, length = 15)
    // isbn의 Getter와 Setter
    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name = "image", nullable = false)
    // image의 Getter와 Setter
    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "price", nullable = false, precision = 12, scale = 0)
    // price의 Getter와 Setter
    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date", nullable = false, length = 10)
    // publishDate의 Getter와 Setter
    public Date getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false, length = 19)
    // lastUpdateTime의 Getter와 Setter
    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    // reviews의 Getter와 Setter
    public Set<Review> getReviews() {
        // 리뷰를 내림차순으로 정렬
        TreeSet<Review> sortedReviews = new TreeSet<>(new Comparator<Review>() {
            @Override
            public int compare(Review review1, Review review2) {
                return review2.getReviewTime().compareTo(review1.getReviewTime());
            }
        });
        
        sortedReviews.addAll(reviews);
        return sortedReviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    // orderDetails의 Getter와 Setter
    public Set<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    @Transient
    // base64Image의 Getter
    public String getBase64Image() {
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
