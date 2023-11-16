package com.bookstore.dao;

import java.util.List; // List를 import합니다.
import java.util.Map; // Map을 import합니다.
import java.util.Map.Entry; // Map 엔트리를 import합니다.
import java.util.Set; // Set을 import합니다.

import javax.persistence.EntityManager; // 엔터티 매니저를 import합니다.
import javax.persistence.EntityManagerFactory; // 엔터티 매니저 팩토리를 import합니다.
import javax.persistence.Persistence; // Persistence를 import합니다.
import javax.persistence.Query; // Query를 import합니다.

public class JpaDAO<E> {
    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
    }

    public JpaDAO() {
    }

    // 엔터티를 생성하고 데이터베이스에 저장하는 메서드
    public E create(E entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(entity);

        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }

    // 엔터티를 업데이트하는 메서드
    public E update(E entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entity = entityManager.merge(entity);

        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }

    // 지정된 ID를 기준으로 특정 엔터티를 검색하는 메서드
    public E find(Class<E> type, Object id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        E entity = entityManager.find(type, id);

        if (entity != null) {
            entityManager.refresh(entity);
        }
        entityManager.close();

        return entity;
    }

    // 지정된 ID를 기준으로 특정 엔터티를 삭제하는 메서드
    public void delete(Class<E> type, Object id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Object reference = entityManager.getReference(type, id);
        entityManager.remove(reference);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // 지정된 이름의 쿼리로 엔터티 목록을 가져오는 메서드
    public List<E> findWithNamedQuery(String queryName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery(queryName);
        List<E> result = query.getResultList();

        entityManager.close();

        return result;
    }

    // 지정된 이름과 범위로 엔터티 목록을 가져오는 메서드
    public List<E> findWithNamedQuery(String queryName, int firstResult, int maxResult) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery(queryName);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);

        List<E> result = query.getResultList();

        entityManager.close();

        return result;
    }

    // Object 배열 목록을 가져오는 메서드
    public List<Object[]> findWithNamedQueryObjects(String queryName, int firstResult, int maxResult) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery(queryName);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);

        List<Object[]> result = query.getResultList();

        entityManager.close();

        return result;
    }

    // 지정된 이름, 매개변수명, 값으로 엔터티 목록을 가져오는 메서드
    public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery(queryName);

        query.setParameter(paramName, paramValue);

        List<E> result = query.getResultList();

        entityManager.close();

        return result;
    }

    // 지정된 이름과 매개변수 맵으로 엔터티 목록을 가져오는 메서드
    public List<E> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery(queryName);

        Set<Entry<String, Object>> setParameters = parameters.entrySet();

        for (Entry<String, Object> entry : setParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        List<E> result = query.getResultList();

        entityManager.close();

        return result;
    }

    // 지정된 이름으로 엔터티 수를 가져오는 메서드
    public long countWithNamedQuery(String queryName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery(queryName);

        long result = (long) query.getSingleResult();
        entityManager.close();

        return result;
    }

    // 지정된 이름, 매개변수명, 값으로 엔터티 수를 가져오는 메서드
    public long countWithNamedQuery(String queryName, String paramName, Object paramValue) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery(queryName);
        query.setParameter(paramName, paramValue);

        long result = (long) query.getSingleResult();
        entityManager.close();

        return result;
    }

    // 엔터티 매니저 팩토리를 닫는 메서드
    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
