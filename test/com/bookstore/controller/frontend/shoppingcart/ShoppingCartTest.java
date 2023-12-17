package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;

public class ShoppingCartTest {
	private static ShoppingCart cart;
	
	@BeforeClass // 테스트 클래스 내의 모든 테스트 메서드보다 먼저 실행되는 메서드를 설정하는 어노테이션
	public static void setUpBeforeClass() throws Exception {
	    cart = new ShoppingCart(); // 테스트 전에 ShoppingCart 객체 생성

	    Book book = new Book(1); // Book 객체 생성 (책 ID가 1인 Book 객체)
	    book.setPrice(10); // 책의 가격을 10으로 설정

	    cart.addItem(book); // ShoppingCart에 책 추가
	    cart.addItem(book); // 동일한 책을 한 번 더 추가
	}

	@Test // 테스트 메서드임을 나타내는 어노테이션
	public void testAddItem() {

	    Map<Book, Integer> items = cart.getItems(); // ShoppingCart의 아이템들을 가져옴
	    int quantity = items.get(new Book(1)); // ID가 1인 책의 수량을 가져옴

	    assertEquals(2, quantity); // 책의 수량이 기대한대로 2인지 확인
	}

	@Test // 테스트 메서드임을 나타내는 어노테이션
	public void testRemoveItem() {
	    cart.removeItem(new Book(1)); // ID가 1인 책을 제거

	    assertTrue(cart.getItems().isEmpty()); // ShoppingCart가 비어있는지 확인
	}

	@Test // 테스트 메서드임을 나타내는 어노테이션
	public void testRemoveItem2() {
	    Book book2 = new Book(2); // ID가 2인 Book 객체 생성
	    cart.addItem(book2); // ID가 2인 책을 ShoppingCart에 추가

	    cart.removeItem(new Book(2)); // ID가 2인 책을 제거

	    assertNull(cart.getItems().get(book2)); // ID가 2인 책이 ShoppingCart에 없는지 확인
	}

	
	@Test // 테스트 메서드임을 나타내는 어노테이션
	public void testGetTotalQuantity() {
	    Book book3 = new Book(3); // ID가 3인 Book 객체 생성
	    cart.addItem(book3); // ShoppingCart에 책 추가
	    cart.addItem(book3); // 같은 책을 두 번 더 추가하여 수량이 3개가 됨

	    assertEquals(5, cart.getTotalQuantity()); // 모든 책의 총 수량이 기대한대로 5인지 확인
	}

	@Test // 테스트 메서드임을 나타내는 어노테이션
	public void testGetTotalAmount1() {
	    ShoppingCart cart = new ShoppingCart(); // 새로운 ShoppingCart 객체 생성

	    assertEquals(0.0f, cart.getTotalAmount(), 0.0f); // 새로운 ShoppingCart의 총 가격이 0인지 확인
	}

	@Test // 테스트 메서드임을 나타내는 어노테이션
	public void testGetTotalAmount2() {
	    assertEquals(20.0f, cart.getTotalAmount(), 0.0f); // 기존 ShoppingCart의 총 가격이 20인지 확인
	}

	@Test // 테스트 메서드임을 나타내는 어노테이션
	public void testClear() {
	    cart.clear(); // 장바구니 비우기

	    assertEquals(0, cart.getTotalQuantity()); // 장바구니의 총 수량이 0인지 확인
	}

	@Test // 테스트 메서드임을 나타내는 어노테이션
	public void testUpdateCart() {
	    ShoppingCart cart = new ShoppingCart(); // 새로운 ShoppingCart 객체 생성
	    Book book1 = new Book(1); // ID가 1인 Book 객체 생성
	    Book book2 = new Book(2); // ID가 2인 Book 객체 생성

	    cart.addItem(book1); // 책1을 ShoppingCart에 추가
	    cart.addItem(book2); // 책2를 ShoppingCart에 추가

	    int[] bookIds = {1, 2}; // 변경할 책들의 ID 배열
	    int[] quantities = {3, 4}; // 변경될 책들의 수량 배열

	    cart.updateCart(bookIds, quantities); // 책들의 수량을 변경

	    assertEquals(7, cart.getTotalQuantity()); // 변경된 총 수량이 기대한대로 7인지 확인
	}
	
//	위 코드는 JUnit을 사용하여 ShoppingCart 클래스의 clear와 updateCart 메서드를 테스트하는 코드입니다.
//	testClear: clear 메서드를 호출하여 장바구니를 비우고, 장바구니의 총 수량이 0인지 확인합니다.
//	testUpdateCart: updateCart 메서드를 호출하여 새로운 ShoppingCart 객체에 책들을 추가하고, 해당 책들의 수량을 변경한 후 총 수량이 예상한 값과 일치하는지 확인합니다.
}
