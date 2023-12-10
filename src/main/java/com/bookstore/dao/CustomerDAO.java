package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	
	@Override
	public Customer create(Customer customer) {
	    // 등록 날짜를 현재 날짜로 설정합니다.
	    customer.setRegisterDate(new Date());
	    // 슈퍼 클래스의 create 메서드를 호출하여 새로운 고객을 생성하고 결과를 반환합니다.
	    return super.create(customer);
	}

	@Override
	public Customer get(Object id) {
	    // 슈퍼 클래스 메서드를 사용하여 ID에 해당하는 고객을 검색합니다.
	    return super.find(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
	    // 슈퍼 클래스 메서드를 사용하여 ID에 해당하는 고객을 삭제합니다.
	    super.delete(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() {
	    // 명명된 쿼리를 사용하여 모든 고객의 목록을 검색합니다.
	    return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
	    // 명명된 쿼리를 사용하여 전체 고객 수를 카운트합니다.
	    return super.countWithNamedQuery("Customer.countAll");
	}

	// 이메일로 고객을 검색합니다.
	public Customer findByEmail(String email) {
	    // 명명된 쿼리를 사용하여 해당 이메일을 가진 고객을 검색합니다.
	    List<Customer> result = super.findWithNamedQuery("Customer.findByEmail", "email", email);
	    
	    // 결과 목록이 비어 있지 않으면 첫 번째로 발견된 고객을 반환하고, 그렇지 않으면 null을 반환합니다.
	    if (!result.isEmpty()) {
	        return result.get(0);
	    }
	    
	    return null;
	}

	// 고객 로그인 정보를 확인합니다.
	public Customer checkLogin(String email, String password) {
	    // 쿼리에 사용할 이메일과 비밀번호 매개변수를 담은 맵을 생성합니다.
	    Map<String, Object> parameters = new HashMap<>();
		//String encryptedPassword = HashGenerator.generateMD5(password); //비밀번호 MD5 저장화
	    parameters.put("email", email);
	    parameters.put("pass", password);
	    
	    // 명명된 쿼리를 사용하여 제공된 이메일과 비밀번호에 해당하는 고객을 검색합니다.
	    List<Customer> result = super.findWithNamedQuery("Customer.checkLogin", parameters);
	    
	    // 결과 목록이 비어 있지 않으면 첫 번째로 발견된 고객을 반환하고, 그렇지 않으면 null을 반환합니다.
//	    if (!result.isEmpty()) {
//	        return result.get(0);
//	    }
	    
	    //아 이렇게 하면 되긴 한다 예아
	    Customer customer = findByEmail(email);

	    if (customer != null) {
	        // 제공된 비밀번호를 해싱하여 저장된 비밀번호와 비교합니다.
	        String hashedPassword = HashGenerator.generateMD5(password);

	        // 해시된 비밀번호를 저장된 비밀번호와 비교하여 일치하는지 확인합니다.
	        if (hashedPassword.equals(customer.getPassword())) {
	            return customer; // 로그인 성공
	        }
	    }
	    
	    return null;
	}

	
}



//package com.bookstore.dao;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.bookstore.entity.Customer;
//
//public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {
//
//	
//	@Override
//	public Customer create(Customer customer) {
//		customer.setRegisterDate(new Date());
//		return super.create(customer);
//	}
//
//	@Override
//	public Customer get(Object id) {
//		return super.find(Customer.class, id);
//	}
//
//	@Override
//	public void delete(Object id) {
//		super.delete(Customer.class, id);
//	}
//
//	@Override
//	public List<Customer> listAll() {
//		return super.findWithNamedQuery("Customer.findAll");
//	}
//
//	@Override
//	public long count() {
//		return super.countWithNamedQuery("Customer.countAll");
//	}
//
//	public Customer findByEmail(String email) {
//		List<Customer> result = super.findWithNamedQuery("Customer.findByEmail", "email", email);
//		
//		if (!result.isEmpty()) {
//			return result.get(0);
//		}
//		
//		return null;
//	}
//	
//	public Customer checkLogin(String email, String password) {
//		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("email", email);
//		parameters.put("pass", password);
//		
//		List<Customer> result = super.findWithNamedQuery("Customer.checkLogin", parameters);
//		
//		if (!result.isEmpty()) {
//			return result.get(0);
//		}
//		
//		return null;
//	}
//	
//}

