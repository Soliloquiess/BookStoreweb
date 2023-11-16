package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

    public static void main(String[] args) {
        // 새로운 사용자(Users) 객체 생성 및 정보 설정
        Users user1 = new Users();
        user1.setEmail("yacho@gmail.com");
        user1.setFullName("Mr President");
        user1.setPassword("power");
        
        // EntityManagerFactory를 사용하여 데이터베이스 연결 설정
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        // 트랜잭션 시작
        entityManager.getTransaction().begin();
        
        // 새로운 사용자 정보를 데이터베이스에 영속화(Persist) - 저장
        entityManager.persist(user1);
        
        // 트랜잭션 커밋(저장)
        entityManager.getTransaction().commit();
        
        // EntityManager와 EntityManagerFactory 닫기
        entityManager.close();
        entityManagerFactory.close();
        
        // 사용자(Users) 객체가 데이터베이스에 영구적으로 저장되었음을 콘솔에 출력
        System.out.println("A Users object was persisted");
    }

}


//설명:
//
//UsersTest 클래스는 JPA를 사용하여 데이터베이스에 새로운 사용자를 추가하는 메인 메서드를 포함하는 테스트 클래스입니다.
//
//Users user1 = new Users();: 새로운 Users 객체를 생성하고, user1 객체에 사용자 정보(이메일, 전체 이름, 비밀번호)를 설정합니다.
//
//EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite"); : Persistence 클래스를 사용하여 EntityManagerFactory를 생성하고, BookStoreWebsite라는 이름의 영속성 유닛(Persistence Unit)을 참조하여 데이터베이스 연결을 설정합니다.
//
//EntityManager entityManager = entityManagerFactory.createEntityManager();: EntityManagerFactory를 사용하여 EntityManager를 생성합니다. EntityManager는 실제 데이터베이스 작업을 수행하기 위한 인터페이스입니다.
//
//entityManager.getTransaction().begin();: 데이터베이스 작업을 위한 트랜잭션을 시작합니다.
//
//entityManager.persist(user1);: EntityManager를 사용하여 user1 객체를 데이터베이스에 영속화(Persist)합니다. 이는 새로운 사용자 정보를 데이터베이스에 저장하는 작업입니다.
//
//entityManager.getTransaction().commit();: 데이터베이스 작업이 완료되었으므로, 트랜잭션을 커밋하여 변경사항을 데이터베이스에 반영합니다.
//
//entityManager.close(); 및 entityManagerFactory.close();: EntityManager와 EntityManagerFactory를 닫아 데이터베이스 연결을 정리합니다.
//
//마지막으로, "A Users object was persisted"라는 메시지를 출력하여 콘솔에 새로운 사용자 정보가 데이터베이스에 성공적으로 저장되었음을 표시합니다.
//
//
//
//

