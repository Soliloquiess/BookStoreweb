package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class CategoryTest {

	public static void main(String[] args) {
		// "Advanced Java"라는 이름으로 새로운 카테고리 객체 생성
		Category newCat = new Category("Advanced Java");
		
		// EntityManagerFactory 생성 ("BookStoreWebsite"라는 이름의 Persistence Unit을 기반으로)
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		// EntityManager 생성
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// 트랜잭션 시작
		entityManager.getTransaction().begin();
		
		// 새로운 카테고리 객체를 영속화 (데이터베이스에 저장)
		entityManager.persist(newCat);
		
		// 트랜잭션 커밋 (변경사항을 데이터베이스에 반영)
		entityManager.getTransaction().commit();
		
		// EntityManager 닫기
		entityManager.close();
		// EntityManagerFactory 닫기
		entityManagerFactory.close();
		
		// 작업 완료 메시지 출력
		System.out.println("A Category object was persisted");
	}

}
