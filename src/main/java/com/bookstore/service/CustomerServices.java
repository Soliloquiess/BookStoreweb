package com.bookstore.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;
import static com.bookstore.service.CommonUtility.*;

public class CustomerServices {
	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * CustomerServices 클래스의 생성자.
	 * @param request HTTP 요청 객체
	 * @param response HTTP 응답 객체
	 */
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
	    super();
	    this.request = request;
	    this.response = response;
	    this.customerDAO = new CustomerDAO();
	}

	/**
	 * 모든 고객 목록을 가져와서 JSP 페이지에 전달하는 메서드.
	 * @param message 고객 목록을 표시할 때 사용할 메시지 (null인 경우 무시)
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void listCustomers(String message) throws ServletException, IOException {
	    // CustomerDAO를 사용하여 모든 고객 정보를 가져옵니다.
	    List<Customer> listCustomer = customerDAO.listAll();
	    
	    // 메시지가 null이 아닌 경우 요청 속성에 메시지를 설정합니다.
	    if (message != null) {
	        request.setAttribute("message", message);
	    }
	    
	    // 고객 목록을 요청 속성에 설정합니다.
	    request.setAttribute("listCustomer", listCustomer);
	    
	    // 고객 목록을 표시할 JSP 페이지의 경로를 지정합니다.
	    String listPage = "customer_list.jsp";
	    
	    // 고객 목록을 표시할 JSP 페이지로 포워딩합니다.
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
	    requestDispatcher.forward(request, response);
	}

	
	/**
	 * 고객 목록을 기본 메시지 없이 표시하기 위한 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void listCustomers() throws ServletException, IOException {
	    // listCustomers(null)을 호출하여 메시지 없이 고객 목록을 표시합니다.
	    listCustomers(null);
	}

	/**
	 * 새로운 고객을 생성하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void createCustomer() throws ServletException, IOException {
	    // HTTP 요청 파라미터에서 이메일 값을 가져옵니다.
	    String email = request.getParameter("email");
	    
	    // 입력된 이메일로 이미 등록된 고객을 찾습니다.
	    Customer existCustomer = customerDAO.findByEmail(email);
	    
	    // 이미 등록된 고객이 있으면 해당 이메일로 고객을 생성할 수 없는 메시지와 함께 고객 목록을 표시합니다.
	    if (existCustomer != null) {
	        String message = "Could not create new customer: the email "
	                + email + " is already registered by another customer.";
	        listCustomers(message);
	    } else {
	        // 새로운 고객 객체를 생성하고 폼으로부터 고객 필드를 업데이트합니다.
	        Customer newCustomer = new Customer();
	        updateCustomerFieldsFromForm(newCustomer);
	        
	        // 새로운 고객을 데이터베이스에 등록합니다.
	        customerDAO.create(newCustomer);
	        
	        // 고객이 성공적으로 생성되었다는 메시지와 함께 고객 목록을 표시합니다.
	        String message = "New customer has been created successfully.";
	        listCustomers(message);
	    }
	}

	
	/**
	 * HTTP 요청에서 양식(form)으로부터 고객 필드를 업데이트하는 메서드.
	 * @param customer 업데이트할 고객 객체
	 */
	private void updateCustomerFieldsFromForm(Customer customer) {
	    // HTTP 요청에서 고객 정보를 얻어옵니다.
	    String email = request.getParameter("email");
	    String firstname = request.getParameter("firstname");
	    String lastname = request.getParameter("lastname");
	    String password = request.getParameter("password");
	    String phone = request.getParameter("phone");
	    String addressLine1 = request.getParameter("address1");
	    String addressLine2 = request.getParameter("address2");
	    String city = request.getParameter("city");
	    String state = request.getParameter("state");
	    String zipCode = request.getParameter("zipCode");
	    String country = request.getParameter("country");
	    
	    // 이메일이 null이 아니고 빈 문자열이 아닌 경우, 고객 객체의 이메일을 업데이트합니다.
	    if (email != null && !email.equals("")) {
	        customer.setEmail(email);
	    }
	    
	    // 고객 객체의 이름과 성을 업데이트합니다.
	    customer.setFirstname(firstname);
	    customer.setLastname(lastname);
	    
	    // 비밀번호가 null이 아니고 빈 문자열이 아닌 경우, 고객 객체의 비밀번호를 업데이트합니다.
		if (password != null & !password.isEmpty()) {
			String encryptedPassword = HashGenerator.generateMD5(password);
			customer.setPassword(encryptedPassword);				
		}
	    
	    // 비밀번호가 null이 아니고 빈 문자열이 아닌 경우, 고객 객체의 비밀번호를 업데이트합니다.
//	    if (password != null && !password.equals("")) {
//	        customer.setPassword(password);
//	    }
	    
	    // 고객 객체의 전화번호, 주소 및 국가 정보를 업데이트합니다.
	    customer.setPhone(phone);
	    customer.setAddressLine1(addressLine1);
	    customer.setAddressLine2(addressLine2);
	    customer.setCity(city);
	    customer.setState(state);
	    customer.setZipcode(zipCode);
	    customer.setCountry(country);        
	}


	/**
	 * 새로운 고객을 등록하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void registerCustomer() throws ServletException, IOException {
	    String email = request.getParameter("email");
	    Customer existCustomer = customerDAO.findByEmail(email);
	    String message = "";

	    // 이미 등록된 이메일이 있는지 확인하고 메시지 설정
	    if (existCustomer != null) {
	        message = "Could not register. The email "
	                + email + " is already registered by another customer.";
	    } else {
	        // 새로운 고객을 생성하고 필드를 업데이트한 후 DB에 등록
	        Customer newCustomer = new Customer();
	        updateCustomerFieldsFromForm(newCustomer);
	        customerDAO.create(newCustomer);

	        message = "You have registered successfully! Thank you.<br/>"
	                + "<a href='login'>Click here</a> to login";
	    }

	    // 메시지를 표시하는 JSP 페이지로 포워딩
	    String messagePage = "frontend/message.jsp";
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
	    request.setAttribute("message", message);
	    requestDispatcher.forward(request, response);
	}

	/**
	 * 고객 정보를 수정하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void editCustomer() throws ServletException, IOException {
	    // 요청으로부터 고객 ID를 가져와서 해당 ID에 해당하는 고객 정보를 가져옴
//	    Integer customerId = Integer.parseInt(request.getParameter("id"));
//	    Customer customer = customerDAO.get(customerId);

	    
	    
	 // 요청에서 'id' 매개변수를 가져와서 정수로 변환합니다.
	    Integer customerId = Integer.parseInt(request.getParameter("id"));

	    // 'customerId'에 해당하는 고객 정보를 데이터베이스에서 가져옵니다.
	    Customer customer = customerDAO.get(customerId);
	    
	    // 만약 고객이 존재하지 않는 경우:
	    if (customer == null) {
	        // 메시지를 생성합니다.
	        String message = "Could not find customer with ID " + customerId;
	        // showMessageBackend() 메서드를 호출하여 해당 메시지를 백엔드에서 보여줍니다.
	        showMessageBackend(message, request, response);
	    } 
	    // 그렇지 않은 경우 (고객이 존재하는 경우):
	    else {
	        // 요청 속성에 'customer'라는 이름으로 고객 정보를 설정합니다.
	        request.setAttribute("customer", customer);	
	        // forwardToPage() 메서드를 사용하여 'customer_form.jsp' 페이지로 포워딩합니다.
	        forwardToPage("customer_form.jsp", request, response);	
	    }

	    
	    
	    
	    
	    // 요청 속성에 해당 고객 정보와 국가 목록을 설정
	    request.setAttribute("customer", customer);
	    CommonUtility.generateCountryList(request);

	    // 고객 정보를 수정하는 JSP 페이지로 포워딩
	    String editPage = "customer_form.jsp";
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
	    requestDispatcher.forward(request, response);
	}


	/**
	 * 고객 정보를 업데이트하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void updateCustomer() throws ServletException, IOException {
	    // 요청으로부터 고객 ID와 이메일을 가져옵니다.
	    Integer customerId = Integer.parseInt(request.getParameter("customerId"));
	    String email = request.getParameter("email");
	    
	    // 동일한 이메일을 가진 고객이 이미 존재하는지 확인합니다.
	    Customer customerByEmail = customerDAO.findByEmail(email);
	    String message = null;

	    // 이미 존재하는 고객이 있고, 해당 고객 ID가 현재 업데이트하려는 고객의 ID와 다를 경우 메시지를 설정합니다.
	    if (customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
	        message = "Could not update the customer ID " + customerId
	                + " because there's an existing customer having the same email.";
	    } else {
	        // 고객 ID를 기반으로 해당 고객 정보를 가져와서 양식으로부터 업데이트한 후 DB를 업데이트합니다.
	        Customer customerById = customerDAO.get(customerId);
	        //customerById로 폼 호출

	        updateCustomerFieldsFromForm(customerById);
	        customerDAO.update(customerById);

	        message = "The customer has been updated successfully.";
	    }
	    
	    // 고객 목록을 표시하는 JSP 페이지로 메시지와 함께 포워딩합니다.
	    listCustomers(message);
	}

	/**
	 * 고객 정보를 삭제하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void deleteCustomer() throws ServletException, IOException {
		//ch35 ver
//		웹사이트에 고객과 관련된 리뷰나 주문이 아직 남아 있는 경우 관리자는 고객을 삭제할 수 없습니다. 본 과제에서는 리뷰 부분만 확인하면 됩니다.
//		따라서 고객 삭제 기능은 고객을 삭제하기 전에 이 확인을 수행해야 합니다. 고객이 게시한 리뷰가 있는 경우 오류 메시지를 표시합니다. 그렇지 않으면 고객이 삭제될 수 있습니다.
//		중요 참고: Customer 클래스에 있는 getReviews() 메소드의 FetchType을 EAGER로 변경하지 마십시오. 게으른 상태로 유지하세요.	
//		->FetchType이 EAGER인 경우 고객 목록 기능은 연결된 Review 개체와 함께 모든 Customer 개체를 로드하기 때문입니다. 이는 애플리케이션 성능에 영향을 미칩니다.
		Integer customerId = Integer.parseInt(request.getParameter("id"));
	    // DAO(Data Access Object)에서 customerId에 해당하는 'Customer' 객체를 가져옵니다.
	    Customer customer = customerDAO.get(customerId);
	    
	    // 만약 가져온 고객(Customer)이 존재한다면, 삭제 가능 여부를 확인합니다.

	 // 만약 가져온 고객(Customer)이 존재한다면, 삭제 가능 여부를 확인합니다.
	    if (customer != null) {
	        // ReviewDAO 인스턴스를 생성하여 해당 고객이 작성한 리뷰의 수를 가져옵니다.
	        ReviewDAO reviewDAO = new ReviewDAO();
	        long reviewCount = reviewDAO.countByCustomer(customerId);
	        
	        // 만약 해당 고객이 작성한 리뷰의 수가 0보다 크다면 삭제할 수 없는 상태입니다.
	        // 리뷰가 있는 경우, 삭제할 수 없음을 나타내는 오류 메시지를 생성합니다.
	        if (reviewCount > 0) {
	            String message = "ID가 " + customerId + "인 고객은 책에 대한 리뷰를 작성하여 삭제할 수 없습니다.";
	            showMessageBackend(message, request, response);
	        } else {
	            // OrderDAO 인스턴스를 생성하여 해당 고객과 관련된 주문의 수를 가져옵니다.
	            OrderDAO orderDAO = new OrderDAO();
	            long orderCount = orderDAO.countByCustomer(customerId);
	            
	            // 만약 해당 고객과 연관된 주문의 수가 0보다 크다면 삭제할 수 없는 상태입니다.
	            if (orderCount > 0) {
	                String message = "ID가 " + customerId + "인 고객은 주문을 하여 삭제할 수 없습니다.";
	                showMessageBackend(message, request, response);
	            } else {
	                // 고객을 삭제하고, 삭제 성공 메시지와 함께 고객 목록을 업데이트합니다.
	                customerDAO.delete(customerId);
	                String message = "고객이 성공적으로 삭제되었습니다.";
	                listCustomers(message);
	            }
	        }
	    } else {
	        // 만약 해당 customerId에 해당하는 고객이 없다면 혹은 이미 삭제된 경우 메시지를 표시합니다.
	    	//// 'customer' 객체가 null인 경우, 해당 ID의 고객을 찾을 수 없거나 이미 삭제된 상태일 수 있음을 나타내는 오류 메시지를 생성합니다.
	        String message = "ID가 " + customerId + "인 고객을 찾을 수 없거나, 다른 관리자에 의해 삭제되었습니다.";
	        showMessageBackend(message, request, response);
	        // 'showMessageBackend' 메서드를 호출하여 오류 메시지를 표시합니다.
	    }
	    
	    //아래는 2023-12-20 이전 버전
	    /*if (customer != null) {
	        // ReviewDAO 객체를 생성합니다.
	        ReviewDAO reviewDAO = new ReviewDAO();
	        // 해당 고객이 작성한 리뷰의 수를 가져옵니다.
	        long reviewCount = reviewDAO.countByCustomer(customerId);
	        
	        if (reviewCount == 0) {
	            // 리뷰가 없는 경우, 해당 customerId를 가진 고객을 삭제합니다.
	            customerDAO.delete(customerId);            
	            // 삭제가 성공했음을 나타내는 메시지를 생성합니다.
	            String message = "고객이 성공적으로 삭제되었습니다.";
	            // 변경된 고객 목록을 표시하기 위해 'listCustomers' 메서드를 호출하여 메시지와 함께 고객 목록을 표시합니다.
	            listCustomers(message);
	        } else {
	            // 리뷰가 있는 경우, 삭제할 수 없음을 나타내는 오류 메시지를 생성합니다.
	            String message = "ID가 " + customerId + "인 고객은 책에 리뷰를 남겨 삭제할 수 없습니다.";
	            // 'showMessageBackend' 메서드를 호출하여 오류 메시지를 표시합니다.
	            showMessageBackend(message, request, response);
	        }
	    } else {
	        // 'customer' 객체가 null인 경우, 해당 ID의 고객을 찾을 수 없거나 이미 삭제된 상태일 수 있음을 나타내는 오류 메시지를 생성합니다.
	        String message = "ID가 " + customerId + "인 고객을 찾을 수 없거나 다른 관리자에 의해 이미 삭제되었을 수 있습니다.";
	        // 'showMessageBackend' 메서드를 호출하여 오류 메시지를 표시합니다.
	        showMessageBackend(message, request, response);
	    }*/
		
		
		//아래는 이전 버전
		/*
		 * // 요청으로부터 고객 ID를 가져와서 해당 고객 정보를 DB에서 삭제합니다. Integer customerId =
		 * Integer.parseInt(request.getParameter("id")); Customer customer =
		 * customerDAO.get(customerId);
		 * 
		 * if (customer != null) { customerDAO.delete(customerId);
		 * 
		 * String message = "The customer has been deleted successfully.";
		 * listCustomers(message); } else { String message =
		 * "Could not find customer with ID " + customerId + ", " +
		 * "or it has been deleted by another admin"; showMessageBackend(message,
		 * request, response); }
		 * 
		 * // 고객이 삭제되었음을 알리는 메시지를 설정하고 고객 목록을 표시하는 JSP 페이지로 포워딩합니다. String message =
		 * "The customer has been deleted successfully."; listCustomers(message);
		 */
	}


	/**
	 * 로그인 페이지를 표시하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void showLogin() throws ServletException, IOException {
	    // 로그인 페이지 경로를 설정하고, 해당 페이지로 포워딩합니다.
	    String loginPage = "frontend/login.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
	    dispatcher.forward(request, response);
	}

	/**
	 * 로그인 작업을 수행하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void doLogin() throws ServletException, IOException {
	    // 요청에서 이메일과 비밀번호를 가져옵니다.
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    // 이메일과 비밀번호를 사용하여 로그인을 확인합니다.
	    Customer customer = customerDAO.checkLogin(email, password);
	    
	    if (customer == null) {
	        // 로그인 실패 시 메시지를 설정하고 로그인 페이지를 다시 표시합니다.
	        String message = "Login failed. Please check your email and password.";
	        request.setAttribute("message", message);
	        showLogin();
	    } else {
	        // 로그인 성공 시 세션에 로그인된 고객 정보를 설정하고, 리다이렉트 URL을 확인하여 해당 페이지로 이동합니다.
	        HttpSession session = request.getSession();
	        session.setAttribute("loggedCustomer", customer);

	        Object objRedirectURL = session.getAttribute("redirectURL");

	        if (objRedirectURL != null) {
	            String redirectURL = (String) objRedirectURL;
	            session.removeAttribute("redirectURL");
	            response.sendRedirect(redirectURL);
	        } else {
	            showCustomerProfile();
	        }
	    }
	}

	
	/**
	 * 고객 프로필 페이지를 표시하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void showCustomerProfile() throws ServletException, IOException {
	    // 고객 프로필 페이지 경로를 설정하고 해당 페이지로 포워딩합니다.
	    String profilePage = "frontend/customer_profile.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
	    dispatcher.forward(request, response);
	}

	/**
	 * 고객 프로필 수정 양식을 표시하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void showCustomerProfileEditForm() throws ServletException, IOException {
	    // 국가 목록을 생성하고 고객 프로필 수정 페이지 경로를 설정합니다.
	    CommonUtility.generateCountryList(request);
	    String editPage = "frontend/edit_profile.jsp";
	    
	    // 고객 프로필 수정 페이지로 포워딩합니다.
	    RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
	    dispatcher.forward(request, response);
	}

	/**
	 * 고객 프로필을 업데이트하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void updateCustomerProfile() throws ServletException, IOException {
	    // 세션에서 로그인된 고객 정보를 가져옵니다.
	    Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
	    
	    // 고객 정보를 양식에서 업데이트하고 데이터베이스에 반영합니다.
	    updateCustomerFieldsFromForm(customer);
	    customerDAO.update(customer);

	    // 고객 프로필 페이지를 표시합니다.
	    showCustomerProfile();
	}


	/**
	 * 새로운 고객 등록을 위한 양식을 표시하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void newCustomer() throws ServletException, IOException {
	    // 국가 목록을 생성하고 고객 등록 양식 페이지 경로를 설정합니다.
	    CommonUtility.generateCountryList(request);

	    // 고객 등록 양식 페이지로 포워딩합니다.
	    String customerForm = "customer_form.jsp";
	    request.getRequestDispatcher(customerForm).forward(request, response);
	}

	/**
	 * 고객 등록 양식을 표시하는 메서드.
	 * @throws ServletException Servlet 예외
	 * @throws IOException IO 예외
	 */
	public void showCustomerRegistrationForm() throws ServletException, IOException {
	    // 국가 목록을 생성하고 고객 등록 양식 페이지 경로를 설정합니다.
	    CommonUtility.generateCountryList(request);

	    // 고객 등록 양식 페이지로 포워딩합니다.
	    String registerForm = "frontend/register_form.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(registerForm);
	    dispatcher.forward(request, response);
	}

}
