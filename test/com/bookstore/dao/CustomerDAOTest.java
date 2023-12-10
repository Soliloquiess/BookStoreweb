package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDao; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    // CustomerDAO 객체를 초기화합니다.
	    customerDao = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	    // CustomerDAO의 리소스를 해제합니다.
	    customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
	    // 새로운 고객을 생성합니다.
	    Customer customer = new Customer();
	    customer.setEmail("customer11@gmail.com");
	    customer.setFirstname("Peter");
	    customer.setLastname("Drucker");
	    customer.setCity("New York");
	    customer.setState("New York");
	    customer.setCountry("United States");
	    customer.setAddressLine1("100 North Avenue");
	    customer.setAddressLine2("Clifton Park");
	    customer.setPassword("secret");
	    customer.setPhone("18001900");
	    customer.setZipcode("100000");
	    
	    // 생성된 고객 정보를 데이터베이스에 저장하고 반환된 저장된 고객 정보를 가져옵니다.
	    Customer savedCustomer = customerDao.create(customer);
	    
	    // 저장된 고객의 ID가 0보다 큰지 확인하여 고객이 성공적으로 저장되었는지 테스트합니다.
	    assertTrue(savedCustomer.getCustomerId() > 0);
	}


	@Test
	public void testGet() {
	    // 특정 ID에 해당하는 고객을 가져옵니다.
	    Integer customerId = 16;
	    Customer customer = customerDao.get(customerId);
	    
	    // 가져온 고객 정보가 null이 아닌지 확인합니다.
	    assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
	    // 특정 ID에 해당하는 고객을 가져옵니다.
	    Customer customer = customerDao.get(16);
	    String firstname = "Tommy";
	    
	    // 고객 정보를 업데이트합니다.
	    customer.setFirstname(firstname);
	    customer.setPassword("123456");
	    
	    // 업데이트된 고객 정보를 가져옵니다.
	    Customer updatedCustomer = customerDao.update(customer);
	    
	    // 업데이트된 고객 정보의 이름이 기대하는 값과 일치하는지 확인합니다.
	    assertTrue(updatedCustomer.getFirstname().equals(firstname));
	}

	@Test
	public void testDeleteCustomer() {
	    // 특정 ID에 해당하는 고객을 삭제합니다.
	    Integer customerId = 16;
	    customerDao.delete(customerId);
	    
	    // 삭제된 고객 정보를 가져와서 null인지 확인합니다.
	    Customer customer = customerDao.get(customerId);
	    assertNull(customer);
	}


	@Test
	public void testListAll() {
	    // 모든 고객 목록을 가져옵니다.
	    List<Customer> listCustomers = customerDao.listAll();
	    
	    // 고객 목록을 반복하며 각 고객의 이름을 출력합니다.
	    for (Customer customer : listCustomers) {
	        System.out.println(customer.getFirstname());
	    }
	    
	    // 가져온 고객 목록이 비어 있지 않은지 확인합니다.
	    assertFalse(listCustomers.isEmpty());
	}

	@Test
	public void testCount() {
	    // 전체 고객 수를 가져옵니다.
	    long totalCustomers = customerDao.count();
	    
	    // 가져온 전체 고객 수가 기대하는 값과 일치하는지 확인합니다.
	    assertEquals(7, totalCustomers);
	}

	@Test
	public void testFindByEmail() {
	    // 특정 이메일을 가진 고객을 검색합니다.
	    String email = "tom@gmail.com";
	    Customer customer = customerDao.findByEmail(email);
	    
	    // 검색된 고객 정보가 null이 아닌지 확인합니다.
	    assertNotNull(customer);
	}


	@Test
	public void testCheckLoginSuccess() {
	    // 성공적인 로그인을 테스트합니다.
	    String email = "billy.jane@gmail.com";
	    String password = "secret";
	    
	    // 주어진 이메일과 비밀번호로 로그인을 시도하고 고객 정보를 가져옵니다.
	    Customer customer = customerDao.checkLogin(email, password);
	    
	    // 가져온 고객 정보가 null이 아닌지 확인합니다.
	    assertNotNull(customer);
	}

	@Test
	public void testCheckLoginFail() {
	    // 실패하는 로그인을 테스트합니다.
	    String email = "abc@gmail.com";
	    String password = "secret";
	    
	    // 주어진 이메일과 비밀번호로 로그인을 시도하고 고객 정보를 가져옵니다.
	    Customer customer = customerDao.checkLogin(email, password);
	    
	    // 가져온 고객 정보가 null인지 확인하여 로그인 실패를 테스트합니다.
	    assertNull(customer);
	}

}

//package com.bookstore.dao;
//
//import static org.junit.Assert.*;
//
//import java.util.List;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.bookstore.entity.Customer;
//
//public class CustomerDAOTest {
//	private static CustomerDAO customerDao; 
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		customerDao = new CustomerDAO();
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		customerDao.close();
//	}
//
//	@Test
//	public void testCreateCustomer() {
//		Customer customer = new Customer();
//		customer.setEmail("customer11@gmail.com");
//		customer.setFirstname("Peter");
//		customer.setLastname("Drucker");
//		customer.setCity("New York");
//		customer.setState("New York");
//		customer.setCountry("United States");
//		customer.setAddressLine1("100 North Avenue");
//		customer.setAddressLine2("Clifton Park");
//		customer.setPassword("secret");
//		customer.setPhone("18001900");
//		customer.setZipcode("100000");
//		
//		Customer savedCustomer = customerDao.create(customer);
//		
//		assertTrue(savedCustomer.getCustomerId() > 0);
//	}
//
//	@Test
//	public void testGet() {
//		Integer customerId = 16;
//		Customer customer = customerDao.get(customerId);
//		
//		assertNotNull(customer);
//	}
//	
//	@Test
//	public void testUpdateCustomer() {
//		Customer customer = customerDao.get(16);
//		String firstname = "Tommy";
//		customer.setFirstname(firstname);
//		customer.setPassword("123456");
//		
//		Customer updatedCustomer = customerDao.update(customer);
//		
//		assertTrue(updatedCustomer.getFirstname().equals(firstname));
//	}
//
//	@Test
//	public void testDeleteCustomer() {
//		Integer customerId = 16;
//		customerDao.delete(customerId);
//		Customer customer = customerDao.get(customerId);
//		
//		assertNull(customer);		
//		
//	}
//
//	@Test
//	public void testListAll() {
//		List<Customer> listCustomers = customerDao.listAll();
//		
//		for (Customer customer : listCustomers) {
//			System.out.println(customer.getFirstname());
//		}
//		
//		assertFalse(listCustomers.isEmpty());
//	}
//	
//	@Test
//	public void testCount() {
//		long totalCustomers = customerDao.count();
//		
//		assertEquals(7, totalCustomers);
//		
//	}
//	
//	@Test
//	public void testFindByEmail() {
//		String email = "tom@gmail.com";
//		Customer customer = customerDao.findByEmail(email);
//		
//		assertNotNull(customer);
//		
//	}
//
//	@Test
//	public void testCheckLoginSuccess() {
//		String email = "billy.jane@gmail.com";
//		String password = "secret";
//		
//		Customer customer = customerDao.checkLogin(email, password);
//		
//		assertNotNull(customer);
//		
//	}
//	
//	@Test
//	public void testCheckLoginFail() {
//		String email = "abc@gmail.com";
//		String password = "secret";
//		
//		Customer customer = customerDao.checkLogin(email, password);
//		
//		assertNull(customer);
//		
//	}	
//}
