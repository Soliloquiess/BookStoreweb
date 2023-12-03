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
		customerDao = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
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
		
		Customer savedCustomer = customerDao.create(customer);
		
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		Integer customerId = 16;
		Customer customer = customerDao.get(customerId);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDao.get(16);
		String firstname = "Tommy";
		customer.setFirstname(firstname);
		customer.setPassword("123456");
		
		Customer updatedCustomer = customerDao.update(customer);
		
		assertTrue(updatedCustomer.getFirstname().equals(firstname));
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId = 16;
		customerDao.delete(customerId);
		Customer customer = customerDao.get(customerId);
		
		assertNull(customer);		
		
	}

	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDao.listAll();
		
		for (Customer customer : listCustomers) {
			System.out.println(customer.getFirstname());
		}
		
		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testCount() {
		long totalCustomers = customerDao.count();
		
		assertEquals(7, totalCustomers);
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "tom@gmail.com";
		Customer customer = customerDao.findByEmail(email);
		
		assertNotNull(customer);
		
	}

	@Test
	public void testCheckLoginSuccess() {
		String email = "billy.jane@gmail.com";
		String password = "secret";
		
		Customer customer = customerDao.checkLogin(email, password);
		
		assertNotNull(customer);
		
	}
	
	@Test
	public void testCheckLoginFail() {
		String email = "abc@gmail.com";
		String password = "secret";
		
		Customer customer = customerDao.checkLogin(email, password);
		
		assertNull(customer);
		
	}	
}
