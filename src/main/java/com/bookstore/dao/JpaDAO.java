package com.bookstore.dao; // com.bookstore.dao 패키지에 속한 클래스입니다.

import java.util.List; // List를 import합니다.
import java.util.Map; // Map을 import합니다.
import java.util.Map.Entry; // Map 엔트리를 import합니다.
import java.util.Set; // Set을 import합니다.

import javax.persistence.EntityManager; // 엔터티 매니저를 import합니다.
import javax.persistence.EntityManagerFactory; // 엔터티 매니저 팩토리를 import합니다.
import javax.persistence.Persistence; // Persistence를 import합니다.
import javax.persistence.Query; // Query를 import합니다.

// JpaDAO 클래스 선언
public class JpaDAO<E> {

    private static EntityManagerFactory entityManagerFactory; // 엔터티 매니저 팩토리를 담는 변수 선언

    // 정적 초기화 블록: 엔터티 매니저 팩토리 생성
    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
    }

    // 기본 생성자
    public JpaDAO() {
    }

    // 엔터티를 생성하고 데이터베이스에 저장하는 메서드
    public E create(E entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성
        entityManager.getTransaction().begin(); // 트랜잭션 시작

        entityManager.persist(entity); // 엔터티 저장
        entityManager.flush(); // 변경사항을 데이터베이스에 즉시 반영
        entityManager.refresh(entity); // 데이터베이스에서 엔터티를 다시 로드하여 영속성 컨텍스트와 일치하도록 갱신

        entityManager.getTransaction().commit(); // 트랜잭션 커밋
        entityManager.close(); // 엔터티 매니저 종료

        return entity; // 저장된 엔터티 반환
    }

    // 엔터티를 업데이트하는 메서드
    public E update(E entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성
        entityManager.getTransaction().begin(); // 트랜잭션 시작

        entity = entityManager.merge(entity); // 준영속 상태의 엔터티를 영속 상태로 만듦

        entityManager.getTransaction().commit(); // 트랜잭션 커밋
        entityManager.close(); // 엔터티 매니저 종료

        return entity; // 업데이트된 엔터티 반환
    }

    // 지정된 ID를 기준으로 특정 엔터티를 검색하는 메서드
    public E find(Class<E> type, Object id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성
        E entity = entityManager.find(type, id); // 엔터티 검색

        if (entity != null) {
            entityManager.refresh(entity); // 검색된 엔터티를 데이터베이스에서 다시 로드하여 영속성 컨텍스트와 일치하도록 갱신
        }
        entityManager.close(); // 엔터티 매니저 종료

        return entity; // 검색된 엔터티 반환
    }

    // 지정된 ID를 기준으로 특정 엔터티를 삭제하는 메서드
    public void delete(Class<E> type, Object id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성
        entityManager.getTransaction().begin(); // 트랜잭션 시작

        Object reference = entityManager.getReference(type, id); // 엔터티에 대한 참조를 가져옴
        entityManager.remove(reference); // 엔터티 삭제

        entityManager.getTransaction().commit(); // 트랜잭션 커밋
        entityManager.close(); // 엔터티 매니저 종료
    }

    // 지정된 이름의 쿼리로 엔터티 목록을 가져오는 메서드
    public List<E> findWithNamedQuery(String queryName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성

        Query query = entityManager.createNamedQuery(queryName); // 이름으로 쿼리 생성
        List<E> result = query.getResultList(); // 결과 목록 가져오기

        entityManager.close(); // 엔터티 매니저 종료

        return result; // 결과 목록 반환
    }

 // 지정된 이름과 범위로 엔터티 목록을 가져오는 메서드
    public List<E> findWithNamedQuery(String queryName, int firstResult, int maxResult) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성

        Query query = entityManager.createNamedQuery(queryName); // 이름으로 쿼리 생성
        query.setFirstResult(firstResult); // 검색 결과 목록에서 첫 번째 결과의 인덱스 지정
        query.setMaxResults(maxResult); // 검색 결과의 최대 개수 지정

        List<E> result = query.getResultList(); // 결과 목록 가져오기

        entityManager.close(); // 엔터티 매니저 종료

        return result; // 결과 목록 반환
    }

    // Object 배열 목록을 가져오는 메서드
    public List<Object[]> findWithNamedQueryObjects(String queryName, int firstResult, int maxResult) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성

        Query query = entityManager.createNamedQuery(queryName); // 이름으로 쿼리 생성
        query.setFirstResult(firstResult); // 검색 결과 목록에서 첫 번째 결과의 인덱스 지정
        query.setMaxResults(maxResult); // 검색 결과의 최대 개수 지정

        List<Object[]> result = query.getResultList(); // 결과 목록 가져오기

        entityManager.close(); // 엔터티 매니저 종료

        return result; // 결과 목록 반환
    }

    // 지정된 이름, 매개변수명, 값으로 엔터티 목록을 가져오는 메서드
    public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성
        Query query = entityManager.createNamedQuery(queryName); // 이름으로 쿼리 생성

        query.setParameter(paramName, paramValue); // 쿼리 매개변수 설정

        List<E> result = query.getResultList(); // 결과 목록 가져오기

        entityManager.close(); // 엔터티 매니저 종료

        return result; // 결과 목록 반환
    }

    // 지정된 이름과 매개변수 맵으로 엔터티 목록을 가져오는 메서드
    public List<E> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성
        Query query = entityManager.createNamedQuery(queryName); // 이름으로 쿼리 생성

        Set<Entry<String, Object>> setParameters = parameters.entrySet(); // 매개변수 집합 얻기

        for (Entry<String, Object> entry : setParameters) {
            query.setParameter(entry.getKey(), entry.getValue()); // 매개변수 설정
           // query.setParameter(paramName, paramValue); // 매개변수 설정
        }

        List<E> result = query.getResultList(); // 결과 목록 가져오기

        entityManager.close(); // 엔터티 매니저 종료

        return result; // 결과 목록 반환
    }

    // 지정된 이름으로 엔터티 수를 가져오는 메서드
    public long countWithNamedQuery(String queryName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성
        Query query = entityManager.createNamedQuery(queryName); // 이름으로 쿼리 생성

        long result = (long) query.getSingleResult(); // 결과 값 가져오기
        entityManager.close(); // 엔터티 매니저 종료

        return result; // 결과 값 반환
    }

    // 지정된 이름, 매개변수명, 값으로 엔터티 수를 가져오는 메서드
    public long countWithNamedQuery(String queryName, String paramName, Object paramValue) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // 엔터티 매니저 생성
        Query query = entityManager.createNamedQuery(queryName); // 이름으로 쿼리 생성
        query.setParameter(paramName, paramValue); // 매개변수 설정

        long result = (long) query.getSingleResult(); // 결과 값 가져오기
        entityManager.close(); // 엔터티 매니저 종료

        return result; // 결과 값 반환
    }


 // 엔터티 매니저 팩토리를 닫는 메서드
    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close(); // 엔터티 매니저 팩토리 종료
        }
    }
}
