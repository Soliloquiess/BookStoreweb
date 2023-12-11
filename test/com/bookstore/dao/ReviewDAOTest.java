package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {

	private static ReviewDAO reviewDao; // ReviewDAO 객체를 정적으로 선언합니다.

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    reviewDao = new ReviewDAO(); // 테스트 클래스 실행 전에 ReviewDAO 객체를 생성하고 초기화합니다.
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	    reviewDao.close(); // 테스트 클래스 실행 후에 ReviewDAO의 자원을 해제합니다.
	}

	@Test
	public void testCreateReview() {
	    // 리뷰를 생성하는 테스트 메서드입니다.
	    Review review = new Review(); // 새로운 리뷰 객체를 생성합니다.
	    Book book = new Book(); // 책 객체를 생성합니다.
	    book.setBookId(1); // 책의 ID를 설정합니다.

	    Customer customer = new Customer(); // 고객 객체를 생성합니다.
	    customer.setCustomerId(1); // 고객의 ID를 설정합니다.

	    review.setBook(book); // 리뷰에 책 정보를 설정합니다.
	    review.setCustomer(customer); // 리뷰에 고객 정보를 설정합니다.

	    review.setHeadline("Excellent book!"); // 리뷰의 제목을 설정합니다.
	    review.setRating(5); // 리뷰의 평점을 설정합니다.
	    review.setComment("A comprehensive book about Spring framework."); // 리뷰의 코멘트를 설정합니다.

	    Review savedReview = reviewDao.create(review); // ReviewDAO를 사용하여 리뷰를 생성하고 저장합니다.

	    assertTrue(savedReview.getReviewId() > 0); // 저장된 리뷰의 ID가 0보다 큰지 확인하여 성공 여부를 검증합니다.
	}


	@Test
	public void testGet() {
	    // 특정 리뷰를 가져오는 테스트 메서드입니다.
	    Integer reviewId = 1; // 가져올 리뷰의 ID를 지정합니다.
	    Review review = reviewDao.get(reviewId); // ReviewDAO를 사용하여 해당 ID의 리뷰를 가져옵니다.

	    assertNotNull(review); // 가져온 리뷰가 null이 아닌지 검증합니다.
	}

	@Test
	public void testUpdateReview() {
	    // 리뷰를 업데이트하는 테스트 메서드입니다.
	    Integer reviewId = 1; // 업데이트할 리뷰의 ID를 지정합니다.
	    Review review = reviewDao.get(reviewId); // ReviewDAO를 사용하여 해당 ID의 리뷰를 가져옵니다.
	    review.setHeadline("Excellent book"); // 리뷰의 제목을 변경합니다.

	    Review updatedReview = reviewDao.update(review); // ReviewDAO를 사용하여 리뷰를 업데이트하고 저장합니다.

	    assertEquals(review.getHeadline(), updatedReview.getHeadline());
	    // 리뷰의 제목이 업데이트되었는지 확인하여 검증합니다.
	}

	@Test
	public void testDeleteReview() {
	    // 리뷰를 삭제하는 테스트 메서드입니다.
	    int reviewId = 2; // 삭제할 리뷰의 ID를 지정합니다.
	    reviewDao.delete(reviewId); // ReviewDAO를 사용하여 해당 ID의 리뷰를 삭제합니다.

	    Review review = reviewDao.get(reviewId); // ReviewDAO를 사용하여 삭제된 리뷰를 가져옵니다.

	    assertNull(review); // 삭제된 리뷰가 null인지 확인하여 검증합니다.
	}


	@Test
	public void testListAll() {
	    // 모든 리뷰를 가져오는 테스트 메서드입니다.
	    List<Review> listReview = reviewDao.listAll(); // ReviewDAO를 사용하여 모든 리뷰를 가져옵니다.

	    for (Review review : listReview) {
	        // 가져온 모든 리뷰에 대해 각 리뷰의 정보를 출력합니다.
	        System.out.println(review.getReviewId() + " - " + review.getBook().getTitle()
	                + " - " + review.getCustomer().getFirstname()
	                + " - " + review.getHeadline() + " - " + review.getRating());
	    }

	    assertTrue(listReview.size() > 0); // 가져온 리뷰 리스트의 크기가 0보다 큰지 확인하여 검증합니다.
	}

	@Test
	public void testCount() {
	    // 리뷰의 총 개수를 가져오는 테스트 메서드입니다.
	    long totalReviews = reviewDao.count(); // ReviewDAO를 사용하여 리뷰의 총 개수를 가져옵니다.
	    System.out.println("Total Reviews: " + totalReviews); // 리뷰의 총 개수를 출력합니다.

	    assertTrue(totalReviews > 0); // 가져온 리뷰의 총 개수가 0보다 큰지 확인하여 검증합니다.
	}

	@Test
	public void testFindByCustomerAndBookNotFound() {
	    // 고객과 책에 해당하는 리뷰가 없는 경우를 테스트하는 메서드입니다.
	    Integer customerId = 100; // 존재하지 않는 고객의 ID를 설정합니다.
	    Integer bookId = 99; // 존재하지 않는 책의 ID를 설정합니다.

	    Review result = reviewDao.findByCustomerAndBook(customerId, bookId);
	    // ReviewDAO를 사용하여 고객과 책에 해당하는 리뷰를 가져옵니다.

	    assertNull(result); // 가져온 리뷰가 없는지 확인하여 검증합니다.
	}

	
	@Test
	public void testFindByCustomerAndBookFound() {
	    // 특정 고객과 책에 해당하는 리뷰를 찾는 테스트 메서드입니다.
	    Integer customerId = 8; // 고객의 ID를 설정합니다.
	    Integer bookId = 2; // 책의 ID를 설정합니다.

	    Review result = reviewDao.findByCustomerAndBook(customerId, bookId);
	    // ReviewDAO를 사용하여 고객과 책에 해당하는 리뷰를 가져옵니다.

	    assertNotNull(result); // 가져온 리뷰가 null이 아닌지 확인하여 검증합니다.
	}

	@Test
	public void testListMostRecent() {
	    // 가장 최근에 작성된 리뷰를 가져오는 테스트 메서드입니다.
	    List<Review> recentReviews = reviewDao.listMostRecent();
	    // ReviewDAO를 사용하여 가장 최근에 작성된 리뷰들을 가져옵니다.

	    assertEquals(3, recentReviews.size());
	    // 가져온 리뷰 리스트의 크기가 3인지 확인하여 검증합니다.
	}

	
}
