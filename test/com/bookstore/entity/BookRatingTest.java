package com.bookstore.entity;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {

	@Test
	public void testAverageRating1() {
	    // Book 객체 생성
	    Book book = new Book();
	    
	    // 리뷰를 담을 Set 생성
	    Set<Review> reviews = new HashSet<>();
	    
	    // 첫 번째 리뷰 생성 및 설정 (평점: 5)
	    Review review1 = new Review();
	    review1.setRating(5);
	    reviews.add(review1); // 리뷰를 책에 추가
	    
	    book.setReviews(reviews); // Book 객체에 리뷰 설정
	    
	    // Book 클래스의 getAverageRating() 메서드 호출하여 평균 평점 계산
	    float averageRating = book.getAverageRating();
	    
	    // 예상되는 평균 평점과 계산된 평균 평점을 비교하여 검증
	    assertEquals(5.0, averageRating, 0.0); // 예상 값, 실제 값, 허용 오차 비교
	}

	@Test
	public void testAverageRating2() {
	    // Book 객체 생성
	    Book book = new Book();
	    
	    // Book 객체의 리뷰가 없는 경우를 테스트
	    
	    // Book 클래스의 getAverageRating() 메서드 호출하여 평균 평점 계산
	    float averageRating = book.getAverageRating();
	    
	    // 예상되는 평균 평점과 계산된 평균 평점을 비교하여 검증
	    assertEquals(0.0, averageRating, 0.0); // 예상 값, 실제 값, 허용 오차 비교
	}    

	@Test
	public void testAverageRating3() {
	    // Book 객체 생성
	    Book book = new Book();
	    
	    // 리뷰를 담을 Set 생성
	    Set<Review> reviews = new HashSet<>();
	    
	    // 세 개의 리뷰 생성 및 설정 (평점: 5, 4, 3)
	    Review review1 = new Review();
	    review1.setRating(5);
	    reviews.add(review1); // 첫 번째 리뷰를 책에 추가

	    Review review2 = new Review();
	    review2.setRating(4);
	    reviews.add(review2); // 두 번째 리뷰를 책에 추가
	    
	    Review review3 = new Review();
	    review3.setRating(3);
	    reviews.add(review3); // 세 번째 리뷰를 책에 추가
	    
	    book.setReviews(reviews); // Book 객체에 리뷰 설정
	    
	    // Book 클래스의 getAverageRating() 메서드 호출하여 평균 평점 계산
	    float averageRating = book.getAverageRating();
	    
	    // 예상되는 평균 평점과 계산된 평균 평점을 비교하여 검증
	    assertEquals(4.0, averageRating, 0.0); // 예상 값, 실제 값, 허용 오차 비교
	}


	@Test
	public void testRatingString1() {
	    // 평균 평점을 0.0으로 설정
	    float averageRating = 0.0f;
	    Book book = new Book(); // Book 객체 생성
	    // Book 클래스의 getRatingString() 메서드를 호출하여 평점에 대응하는 문자열을 가져옴
	    String actual =  book.getRatingString(averageRating);
	    
	    // 기대되는 문자열 값
	    String expected = "off,off,off,off,off";
	    
	    // 예상 값과 실제 값을 비교하여 검증
	    assertEquals(expected, actual);
	}

	@Test
	public void testRatingString2() {
	    // 평균 평점을 5.0으로 설정
	    float averageRating = 5.0f;
	    Book book = new Book(); // Book 객체 생성
	    // Book 클래스의 getRatingString() 메서드를 호출하여 평점에 대응하는 문자열을 가져옴
	    String actual =  book.getRatingString(averageRating);
	    
	    // 기대되는 문자열 값
	    String expected = "on,on,on,on,on";
	    
	    // 예상 값과 실제 값을 비교하여 검증
	    assertEquals(expected, actual);
	}

	@Test
	public void testRatingString3() {
	    // 평균 평점을 3.0으로 설정
	    float averageRating = 3.0f;
	    Book book = new Book(); // Book 객체 생성
	    // Book 클래스의 getRatingString() 메서드를 호출하여 평점에 대응하는 문자열을 가져옴
	    String actual =  book.getRatingString(averageRating);
	    
	    // 기대되는 문자열 값
	    String expected = "on,on,on,off,off";
	    
	    // 예상 값과 실제 값을 비교하여 검증
	    assertEquals(expected, actual);
	}

	
	@Test
	public void testRatingString4() {
	    // 평균 평점을 4.5로 설정
	    float averageRating = 4.5f;
	    Book book = new Book(); // Book 객체 생성
	    // Book 클래스의 getRatingString() 메서드를 호출하여 평점에 대응하는 문자열을 가져옴
	    String actual = book.getRatingString(averageRating);
	    
	    // 기대되는 문자열 값
	    String expected = "on,on,on,on,half";
	    
	    // 예상 값과 실제 값을 비교하여 검증
	    assertEquals(expected, actual);
	}

	@Test
	public void testRatingString5() {
	    // 평균 평점을 3.6으로 설정
	    float averageRating = 3.6f;
	    Book book = new Book(); // Book 객체 생성
	    // Book 클래스의 getRatingString() 메서드를 호출하여 평점에 대응하는 문자열을 가져옴
	    String actual = book.getRatingString(averageRating);
	    
	    // 기대되는 문자열 값
	    String expected = "on,on,on,half,off";
	    
	    // 예상 값과 실제 값을 비교하여 검증
	    assertEquals(expected, actual);
	}

}
