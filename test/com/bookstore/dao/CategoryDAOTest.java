package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {

	private static CategoryDAO categoryDao; // CategoryDAO 객체 선언
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDao = new CategoryDAO(); // CategoryDAO 객체 생성
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close(); // 테스트 종료 후 CategoryDAO 연결 종료
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Health");
		Category category = categoryDao.create(newCat); // 새로운 카테고리 생성 및 저장
		
		assertTrue(category != null && category.getCategoryId() > 0); // 카테고리가 생성되고 ID가 양수인지 확인
	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Java Core");
		cat.setCategoryId(1); // 카테고리 ID 설정
		
		Category category = categoryDao.update(cat); // 카테고리 업데이트
		
		assertEquals(cat.getName(), category.getName()); // 업데이트된 카테고리 이름이 예상한대로인지 확인
	}

	@Test
	public void testGet() {
		Integer catId = 23;
		Category cat = categoryDao.get(catId); // 주어진 ID에 해당하는 카테고리 가져오기
		
		assertNotNull(cat); // 가져온 카테고리가 null이 아닌지 확인
	}

	@Test
	public void testDeleteCategory() {
		Integer catId = 11;
		categoryDao.delete(catId); // 카테고리 삭제
		
		Category cat = categoryDao.get(catId); // 해당 ID의 카테고리 가져오기
		
		assertNull(cat); // 가져온 카테고리가 null인지 확인 (삭제되었는지)
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll(); // 모든 카테고리 목록 가져오기
		
		listCategory.forEach(c -> System.out.println(c.getName() + ", ")); // 모든 카테고리 이름 출력
		
		assertTrue(listCategory.size() > 0); // 카테고리 목록이 비어있지 않은지 확인
	}

	@Test
	public void testCount() {
		long totalCategories = categoryDao.count(); // 전체 카테고리 수 가져오기
		
		assertTrue(totalCategories >  0); // 전체 카테고리 수가 0보다 큰지 확인
	}
	
	@Test
	public void testFindByName() {
		String name = "Java Core";
		Category category = categoryDao.findByName(name); // 이름으로 카테고리 찾기
		
		assertNotNull(category); // 해당 이름의 카테고리가 null이 아닌지 확인
	}

	@Test
	public void testFindByNameNotFound() {
		String name = "Java Core1";
		Category category = categoryDao.findByName(name); // 존재하지 않는 이름으로 카테고리 찾기
		
		assertNull(category); // 해당 이름의 카테고리가 null인지 확인 (찾을 수 없는 경우)
	}	
}
