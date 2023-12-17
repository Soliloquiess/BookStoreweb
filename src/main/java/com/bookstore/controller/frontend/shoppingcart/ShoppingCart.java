package com.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShoppingCart {
	private Map<Book, Integer> cart = new HashMap<>(); // Book을 키로하고 해당 책의 수량(Integer)을 값으로 갖는 HashMap을 장바구니로 사용

	public void addItem(Book book) {
	    if (cart.containsKey(book)) { // 장바구니에 해당 책이 이미 존재하는지 확인
	        Integer quantity = cart.get(book) + 1; // 이미 존재한다면 해당 책의 수량을 가져와 1을 증가시킴
	        cart.put(book, quantity); // 수량을 갱신하여 다시 장바구니에 넣음
	    } else {
	        cart.put(book, 1); // 장바구니에 해당 책이 존재하지 않는다면 새로 추가하고 수량을 1로 설정
	    }
	}

	public void removeItem(Book book) {
	    cart.remove(book); // 장바구니에서 특정 책을 삭제함
	}

	public int getTotalQuantity() {
	    int total = 0; // 총 수량을 담을 변수 초기화

	    Iterator<Book> iterator = cart.keySet().iterator(); // 장바구니의 키 집합에 대한 반복자 생성

	    while (iterator.hasNext()) { // 반복자를 통해 장바구니의 모든 책들에 대해 수량을 누적하여 합산
	        Book next = iterator.next(); // 다음 책을 가져옴
	        Integer quantity = cart.get(next); // 해당 책의 수량을 가져옴
	        total += quantity; // 총 수량에 현재 책의 수량을 더함
	    }

	    return total; // 총 수량 반환
	}

	public float getTotalAmount() {
	    float total = 0.0f; // 총 금액을 저장할 변수 초기화

	    Iterator<Book> iterator = cart.keySet().iterator(); // 장바구니의 키 집합에 대한 반복자 생성

	    while (iterator.hasNext()) { // 모든 책에 대해 반복
	        Book book = iterator.next(); // 다음 책을 가져옴
	        Integer quantity = cart.get(book); // 해당 책의 수량을 가져옴
	        double subTotal = quantity * book.getPrice(); // 각 책의 수량과 가격을 곱하여 소계 계산
	        total += subTotal; // 소계를 총 금액에 더함
	    }        

	    return total; // 총 금액 반환
	}

	public void updateCart(int[] bookIds, int[] quantities) {
	    for (int i = 0; i < bookIds.length; i++) { // bookIds와 quantities 배열 길이만큼 반복
	        Book key = new Book(bookIds[i]); // bookIds 배열의 요소를 키로 갖는 Book 객체 생성
	        Integer value = quantities[i]; // 해당 인덱스의 quantities 값을 가져옴
	        cart.put(key, value); // 장바구니에 해당 Book과 수량을 갱신 또는 추가
	    }
	}

	public void clear() {
	    cart.clear(); // 장바구니 비우기
	}

	public int getTotalItems() {
	    return cart.size(); // 장바구니에 담긴 아이템의 총 개수 반환
	}

	public Map<Book, Integer> getItems() {
	    return this.cart; // 장바구니에 담긴 아이템들을 담고 있는 Map 반환
	}
}
