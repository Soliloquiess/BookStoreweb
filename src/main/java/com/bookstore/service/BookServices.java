package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

import static com.bookstore.service.CommonUtility.*;
public class BookServices {
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BookServices(HttpServletRequest request, HttpServletResponse response) {
	    super();
	    this.request = request; // 받은 request를 인스턴스 변수에 할당
	    this.response = response; // 받은 response를 인스턴스 변수에 할당
	    bookDAO = new BookDAO(); // BookDAO 객체 생성 및 할당
	    categoryDAO = new CategoryDAO(); // CategoryDAO 객체 생성 및 할당
	}

	// 메서드 오버로드: message가 없는 경우를 위한 listBooks() 메서드 호출
	public void listBooks() throws ServletException, IOException {
	    listBooks(null); // message가 null인 경우 listBooks(null) 호출
	}

	// 책 목록을 요청 속성에 설정하고 메시지가 있으면 설정한 후 book_list.jsp로 포워딩
	public void listBooks(String message) throws ServletException, IOException {
	    List<Book> listBooks = bookDAO.listAll(); // 모든 책 목록을 가져오기
	    request.setAttribute("listBooks", listBooks); // 책 목록을 요청 속성에 설정
	    
	    if (message != null) {
	        request.setAttribute("message", message); // 메시지가 있으면 요청 속성에 설정
	    }
	    
	    String listPage = "book_list.jsp"; // 보여줄 페이지 지정
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage); // 요청 디스패처 생성
	    requestDispatcher.forward(request, response); // 지정된 페이지로 포워딩
	}

	// 새 책을 생성하기 위한 폼을 보여줌
	public void showBookNewForm() throws ServletException, IOException {
	    List<Category> listCategory = categoryDAO.listAll(); // 모든 카테고리 목록을 가져오기
	    request.setAttribute("listCategory", listCategory); // 카테고리 목록을 요청 속성에 설정
	    
	    String newPage = "book_form.jsp"; // 보여줄 페이지 지정
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage); // 요청 디스패처 생성
	    requestDispatcher.forward(request, response); // 지정된 페이지로 포워딩
	}


	public void createBook() throws ServletException, IOException {
	    // 요청에서 'title' 매개변수를 가져옴
	    String title = request.getParameter("title");
	    
	    // 동일한 제목을 가진 책이 이미 존재하는지 확인
	    Book existBook = bookDAO.findByTitle(title);
	    
	    // 동일한 제목의 책이 이미 존재하는 경우 처리
	    if (existBook != null) {
	        String message = "Could not create new book because the title '"
	                + title + "' already exists.";
	        listBooks(message); // 메시지와 함께 책 목록을 보여줌
	        return;
	    }
	    
	    // 새 Book 객체를 생성하고 필드를 읽어와서 설정
	    Book newBook = new Book();
	    readBookFields(newBook); // 책 필드를 읽어와서 설정하는 메서드 호출
	    
	    // 생성된 Book 객체를 DAO를 통해 저장
	    Book createdBook = bookDAO.create(newBook);
	    
	    // 책이 성공적으로 생성되면 성공 메시지와 함께 책 목록을 보여줌
	    if (createdBook.getBookId() > 0) {
	        String message = "A new book has been created successfully.";
	        listBooks(message); // 메시지와 함께 책 목록을 보여줌
	    }
	}

	// Book 객체의 필드를 읽어와 설정하는 메서드
	public void readBookFields(Book book) throws ServletException, IOException {
	    // 요청에서 다양한 매개변수를 읽어와 Book 객체의 필드로 설정
	    String title = request.getParameter("title");
	    String author = request.getParameter("author");
	    String description = request.getParameter("description");
	    String isbn = request.getParameter("isbn");
	    float price = Float.parseFloat(request.getParameter("price"));
	    
	    // 출판일을 설정하기 위해 문자열을 날짜로 변환하는 과정
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date publishDate = null;
	    
	    try {
	        // 출판일을 요청에서 받아와 날짜 객체로 파싱
	        publishDate = dateFormat.parse(request.getParameter("publishDate"));
	    } catch (ParseException ex) {
	        ex.printStackTrace();
	        throw new ServletException("Error parsing publish date (format is yyyy-MM-dd)");
	    }
	            
	    // Book 객체의 필드 설정
	    book.setTitle(title);
	    book.setAuthor(author);
	    book.setDescription(description);
	    book.setIsbn(isbn);
	    book.setPublishDate(publishDate);
	    
	    // 카테고리 ID를 가져와서 해당 카테고리 설정
	    Integer categoryId = Integer.parseInt(request.getParameter("category"));
	    Category category = categoryDAO.get(categoryId);
	    book.setCategory(category);
	    
	    book.setPrice(price); // 가격 설정
	    
	    // 책 이미지를 읽어와서 바이트 배열로 설정
	    Part part = request.getPart("bookImage");
	    
	    if (part != null && part.getSize() > 0) {
	        long size = part.getSize();
	        byte[] imageBytes = new byte[(int) size];
	        
	        InputStream inputStream = part.getInputStream();
	        inputStream.read(imageBytes);
	        inputStream.close();
	        
	        book.setImage(imageBytes);
	    }
	}

	// 책 편집을 위해 해당 책 정보를 가져와서 편집 폼으로 포워딩
	// 2명 또는 관리자/관리자가 동시에 도서를 관리하는 경우가 있습니다. 다음 시나리오를 상상해 보세요.

	//- 첫 번째 관리자가 도서목록을 조회합니다.
	//- 두 번째 관리자가 도서목록을 조회합니다.
	//- 첫 번째 관리자는 'Java 8 in Action' 도서(ID: 76)를 삭제합니다.
	//- 두 번째 관리자는 삭제된 도서 'Java 8 in Action'을 편집하려고 합니다.
	//이 경우 두 번째 관리자에게 의미 있는 오류 메시지가 표시됩니다.

	public void editBook() throws ServletException, IOException {
	    // 요청에서 bookId 파라미터를 가져와 Integer로 변환하여 bookId 변수에 할당
	    Integer bookId = Integer.parseInt(request.getParameter("id"));
	    
	    // BookDAO를 사용하여 해당 bookId에 해당하는 Book 객체 가져오기
	    Book book = bookDAO.get(bookId);
	    
	    // 기본적으로 보여줄 페이지 지정
	    String destPage = "book_form.jsp";
	    
	    // 만약 book 객체가 null이 아니라면 (해당 bookId에 해당하는 Book 객체가 존재한다면)
	    if (book != null) {
	        // 모든 카테고리 목록을 가져오기
	        List<Category> listCategory = categoryDAO.listAll();
	        
	        // 요청 속성에 현재 편집 중인 book 객체와 카테고리 목록을 설정
	        request.setAttribute("book", book);
	        request.setAttribute("listCategory", listCategory);
	    } else { // 만약 book 객체가 null이라면 (해당 bookId에 해당하는 Book 객체를 찾을 수 없다면)
	        // 목적 페이지를 'message.jsp'로 변경
	        destPage = "message.jsp";
	        
	        // 사용자에게 보여줄 메시지 설정 (해당 bookId를 가진 책을 찾을 수 없다는 메시지)
	        String message = "Could not find book with ID " + bookId;
	        
	        // 요청 속성에 메시지 설정
	        request.setAttribute("message", message);			
	    }
	    
	    // 목적 페이지로 포워딩
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
	    requestDispatcher.forward(request, response);		
	}

	
	
	// 책 편집을 위해 해당 책 정보를 가져와서 편집 폼으로 포워딩
//	public void editBook() throws ServletException, IOException {
//	    Integer bookId = Integer.parseInt(request.getParameter("id")); // 요청에서 bookId 가져오기
//	    Book book = bookDAO.get(bookId); // 해당 bookId에 해당하는 Book 객체 가져오기
//	    List<Category> listCategory = categoryDAO.listAll(); // 모든 카테고리 목록 가져오기
//	    
//	    request.setAttribute("book", book); // 요청 속성에 book 객체 설정
//	    request.setAttribute("listCategory", listCategory); // 요청 속성에 카테고리 목록 설정
//	    
//	    String editPage = "book_form.jsp"; // 보여줄 편집 페이지 지정
//	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage); // 요청 디스패처 생성
//	    requestDispatcher.forward(request, response); // 지정된 페이지로 포워딩
//	}

	// 책 정보를 업데이트하고 목록을 다시 보여줌
	public void updateBook() throws ServletException, IOException {
	    Integer bookId = Integer.parseInt(request.getParameter("bookId")); // 요청에서 bookId 가져오기
	    String title = request.getParameter("title"); // 요청에서 새로운 제목 가져오기
	    
	    Book existBook = bookDAO.get(bookId); // 해당 bookId에 해당하는 Book 객체 가져오기
	    Book bookByTitle = bookDAO.findByTitle(title); // 새로운 제목으로 다른 Book 객체 가져오기
	    
	    // 다른 책이 동일한 제목을 가진 경우 업데이트 방지
	    if (bookByTitle != null && !existBook.equals(bookByTitle)) {
	        String message = "Could not update book because there's another book having the same title.";
	        listBooks(message); // 메시지와 함께 책 목록을 보여줌
	        return;
	    }
	    
	    readBookFields(existBook); // Book 객체의 필드 업데이트
	    
	    bookDAO.update(existBook); // 업데이트된 Book 객체 저장
	    
	    String message = "The book has been updated successfully.";
	    listBooks(message); // 메시지와 함께 책 목록을 보여줌
	}

	// 책을 삭제하고 삭제가 성공했음을 메시지와 함께 목록을 보여줌
	
//	- 첫 번째 관리자가 도서목록을 조회합니다.
//	- 두 번째 관리자가 도서목록을 조회합니다.
//	- 첫 번째 관리자는 'Java 8 in Action' 도서(ID: 76)를 삭제합니다.
//	- 두 번째 관리자는 삭제된 도서 'Java 8 in Action'을 삭제하려고 합니다.
//	이 경우 두 번째 관리자에게 의미 있는 오류 메시지가 표시됩니다.
	
	public void deleteBook() throws ServletException, IOException {
		//관리자는 리뷰가 있는 책을 삭제할 수 없습니다. 책과 관련된 리뷰 및 주문이 없는 경우에만 책을 삭제할 수 있습니다. 이 과제에서는 책을 삭제하기 전에 리뷰를 확인하는 코드를 작성합니다.
//BookServices 클래스의 deleteBook() 메서드를 업데이트합니다.
//Book 인스턴스로 즉시 초기화되는 컬렉션이기 때문에 Book 개체의 필드 리뷰에 간단히 액세스하세요. 그런 다음 컬렉션의 크기/비어 있음을 확인하십시오.

		// 요청(request)에서 'id' 매개변수를 가져와서 Integer로 변환합니다.
		Integer bookId = Integer.parseInt(request.getParameter("id"));

		// DAO(Data Access Object)에서 bookId에 해당하는 'Book' 객체를 가져옵니다.
		Book book =  bookDAO.get(bookId);

		// 'book' 객체가 null인지 확인합니다.
		if (book == null) {
		    // 'book' 객체가 null인 경우, 해당 ID의 책을 찾을 수 없거나 이미 삭제된 상태일 수 있음을 나타내는 오류 메시지를 생성합니다.
		    String message = "ID가 " + bookId + "인 책을 찾을 수 없거나 이미 삭제되었을 수 있습니다.";
		    
		    // 'showMessageBackend' 메서드를 호출하여 오류 메시지를 표시합니다.
		    showMessageBackend(message, request, response);
		} else {
		    // 'book' 객체가 null이 아닌 경우

		    // 책에 리뷰가 있는지 확인합니다.
		    if (!book.getReviews().isEmpty()) {
		        // 책에 리뷰가 있는 경우, 해당 책을 삭제할 수 없음을 알리는 오류 메시지를 생성합니다.
		        String message = "ID가 " + bookId + "인 책은 리뷰가 있어 삭제할 수 없습니다.";
		        
		        // 'showMessageBackend' 메서드를 호출하여 오류 메시지를 표시합니다.
		        showMessageBackend(message, request, response);
		    } else {
		    	
		    	
		    	// 2023-12-20에 추가된 부분입니다.

		    	// OrderDAO 인스턴스를 생성합니다.
		    	OrderDAO orderDAO = new OrderDAO();

		    	// 해당 책(bookId)과 연관된 주문 개수를 가져오는데 OrderDAO 클래스의 countOrderDetailByBook 메서드를 호출하여 수행합니다.
		    	long countByOrder = orderDAO.countOrderDetailByBook(bookId);

		    	// 연관된 주문 개수가 0보다 크면 해당 책(bookId)과 연관된 주문이 있으므로 삭제할 수 없는 상태입니다.
		    	if (countByOrder > 0) {
		    	    String message = "ID가 " + bookId + "인 책과 연관된 주문이 있어 삭제할 수 없습니다.";
		    	    showMessageBackend(message, request, response);
		    	} else {
		    	    // 연관된 주문이 없는 경우 해당 책(bookId)을 성공적으로 삭제합니다.
		    	    String message = "책이 성공적으로 삭제되었습니다.";
		    	    bookDAO.delete(bookId); // bookDAO를 이용하여 bookId에 해당하는 책을 삭제합니다.
		    	    listBooks(message); // 책 목록을 업데이트하여 메시지와 함께 표시합니다.
		    	}
//위의 코드는 2023년 12월 20일에 추가된 코드 부분으로, 특정 책(bookId)과 관련된 주문 개수를 확인하여 해당 책이 주문과 연관이 있는지 확인하고 삭제 가능 여부를 결정합니다. 만약 연관된 주문이 있다면 삭제할 수 없으며, 연관된 주문이 없는 경우 해당 책을 성공적으로 삭제하고 책 목록을 업데이트하여 메시지와 함께 표시합니다.
		    	
		    	
		    	///////////////////////
		        // 2023-12-20이전코드. 책에 리뷰가 없는 경우

		        // 책을 삭제했다는 메시지를 생성합니다.
				/*
				 * String message = "책이 성공적으로 삭제되었습니다.";
				 * 
				 * // 'bookId'에 해당하는 책을 DAO를 통해 삭제합니다. bookDAO.delete(bookId);
				 * 
				 * // 삭제 후 변경된 책 목록을 표시하기 위해 'listBooks' 메서드를 호출하여 메시지와 함께 책 목록을 표시합니다.
				 * listBooks(message);
				 */
		        
		    }
		}
		
		//아래는 이전 버전. 더불어 commonUtility 안 쓰고 직접 포워딩 해서 보내는 버전
		/*
		 * // 요청에서 bookId 파라미터를 가져와 Integer로 변환하여 bookId 변수에 할당 Integer bookId =
		 * Integer.parseInt(request.getParameter("id"));
		 * 
		 * // BookDAO를 사용하여 해당 bookId에 해당하는 Book 객체 가져오기 Book book =
		 * bookDAO.get(bookId);
		 * 
		 * // 만약 book 객체가 null이라면 (해당 bookId에 해당하는 Book 객체를 찾을 수 없거나 이미 삭제된 경우) if (book
		 * == null) { // 사용자에게 보여줄 메시지 설정 (해당 bookId를 가진 책을 찾을 수 없다는 메시지) String message
		 * = "Could not find book with ID " + bookId +
		 * ", or it might have been deleted";
		 * 
		 * // 요청 속성에 메시지 설정 request.setAttribute("message", message);
		 * 
		 * // message.jsp 페이지로 포워딩하여 메시지를 사용자에게 보여줌
		 * request.getRequestDispatcher("message.jsp").forward(request, response);
		 * 
		 * } else { // 만약 book 객체가 null이 아니라면 (해당 bookId에 해당하는 Book 객체를 찾은 경우) // 책 삭제
		 * 성공 메시지 설정 String message = "The book has been deleted successfully.";
		 * 
		 * // 해당 bookId를 가진 책을 실제로 삭제함 bookDAO.delete(bookId);
		 * 
		 * // 삭제 성공 메시지와 함께 책 목록 페이지를 보여줌 listBooks(message); }
		 */
	}

	
	
//	// 책을 삭제하고 삭제가 성공했음을 메시지와 함께 목록을 보여줌
//	public void deleteBook() throws ServletException, IOException {
//	    Integer bookId = Integer.parseInt(request.getParameter("id")); // 요청에서 bookId 가져오기
//	    
//	    bookDAO.delete(bookId); // 해당 bookId에 해당하는 책 삭제
//	    
//	    String message = "The book has been deleted successfully.";
//	    listBooks(message); // 메시지와 함께 책 목록을 보여줌
//	}

	// 특정 카테고리별로 책 목록을 가져와서 해당 카테고리의 책 목록 페이지로 포워딩
//	다음 시나리오를 상상해 보세요.
//	- 방문자가 웹사이트를 방문하여 '만화'라는 카테고리를 봅니다.
//	- 관리자/관리자가 '만화' 카테고리를 삭제했습니다.
//	- 방문자가 만화 카테고리를 클릭합니다.
//	이 경우 해당 카테고리는 더 이상 사용할 수 없으므로 웹사이트는 방문자에게 의미 있는 오류 메시지를 표시해야 합니다.

	public void listBooksByCategory() throws ServletException, IOException {
	    // 1. 요청(request)에서 'id' 매개변수를 가져와서 정수형으로 변환합니다.
	    int categoryId = Integer.parseInt(request.getParameter("id"));

	    // 2. 해당 ID에 해당하는 카테고리를 데이터베이스에서 가져옵니다.
	    Category category = categoryDAO.get(categoryId);
	    List<Book> listBooks = bookDAO.listByCategory(categoryId); // 해당 categoryId에 해당하는 책 목록 가져오기
	    List<Category> listCategory = categoryDAO.listAll(); //카데고리 페이지 누르면 카테고리 목록들이 더처럼 위에 뜨게 

	    // 3. 가져온 카테고리가 없는 경우의 조건문입니다.
	    if (category == null) {
	        // 4. 해당하는 카테고리가 없을 때 보여줄 에러 메시지를 설정합니다.
	        String message = "Sorry, the category ID " + categoryId + " is not available.";
	        // 5. 에러 메시지를 request에 설정하여 에러 페이지로 전달합니다.
	        request.setAttribute("message", message);
	        request.getRequestDispatcher("frontend/message.jsp").forward(request, response);
	        
	        return; // 6. 메서드 실행을 중단하고 종료합니다.
	    }
	    
	    // 7. 해당 카테고리에 속하는 도서들의 목록을 데이터베이스에서 가져옵니다. 위에 있음
	    //List<Book> listBooks = bookDAO.listByCategory(categoryId);
	    
	    // 8. 도서 목록과 카테고리 정보를 request에 설정합니다.
	    request.setAttribute("listCategory", listCategory); // 요청 속성에 책 목록 설정
	    request.setAttribute("listBooks", listBooks); // 요청 속성에 책 목록 설정
	    request.setAttribute("category", category); // 요청 속성에 카테고리 설정

	    
	    
	    // 9. 도서 목록을 보여줄 페이지 경로를 설정합니다.
	    String listPage = "frontend/books_list_by_category.jsp";
	    // 10. 설정한 페이지로 request와 response를 전달하여 해당 페이지로 이동합니다.
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
	    requestDispatcher.forward(request, response);        
	}

	

	// 특정 카테고리별로 책 목록을 가져와서 해당 카테고리의 책 목록 페이지로 포워딩	
	//	public void listBooksByCategory() throws ServletException, IOException {
//	    int categoryId = Integer.parseInt(request.getParameter("id")); // 요청에서 categoryId 가져오기
//	    List<Book> listBooks = bookDAO.listByCategory(categoryId); // 해당 categoryId에 해당하는 책 목록 가져오기
//	    Category category = categoryDAO.get(categoryId); // 해당 categoryId에 해당하는 카테고리 정보 가져오기
//	    List<Category> listCategory = categoryDAO.listAll(); //카데고리 페이지 누르면 카테고리 목록들이 더처럼 위에 뜨게 
//	    
//	    // request.setAttribute로 객체로 만들어줘야 함. 그래야만 데이터가 정상으로 들어간다.
//	    request.setAttribute("listCategory", listCategory); // 요청 속성에 책 목록 설정
//	    request.setAttribute("listBooks", listBooks); // 요청 속성에 책 목록 설정
//	    request.setAttribute("category", category); // 요청 속성에 카테고리 설정
//	    
//	    String listPage = "frontend/books_list_by_category.jsp"; // 보여줄 페이지 지정 frontend/ 까지 꼭 넣어야 함.
//	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage); // 요청 디스패처 생성
//	    requestDispatcher.forward(request, response); // 지정된 페이지로 포워딩
//	}

	// 책 상세 정보를 가져와서 상세 페이지로 포워딩
	
//	다음 시나리오를 고려하십시오.
//
//	- 방문자가 웹사이트에 접속하여 '라이프스타일' 카테고리의 책을 보고 있습니다.
//	- 방문자는 '주 4시간 근무'라는 제목의 책을 ​​본다.
//	- 관리자가 백엔드에서 해당 도서를 삭제합니다.
//	- 방문자가 삭제된 도서 '주 4시간 근무'를 클릭합니다.
//	이 경우 웹사이트는 방문자에게 친숙한 오류 메시지를 표시해야 합니다.

	public void viewBookDetail() throws ServletException, IOException {
	    // 요청 파라미터에서 책 ID를 가져와 Integer로 변환
	    Integer bookId = Integer.parseInt(request.getParameter("id"));
	    // bookDAO를 사용하여 해당 ID에 해당하는 Book 객체를 가져옴
	    Book book = bookDAO.get(bookId);
	    // 모든 카테고리를 가져오기 위해 categoryDAO를 사용하여 리스트를 가져옴
	    List<Category> listCategory = categoryDAO.listAll();
	     
	    // JSP 페이지의 목적지 설정
	    String destPage = "frontend/book_detail.jsp";
	    
	    // 만약 book 객체가 null이 아니라면
	    if (book != null) {
	        // 요청 속성에 카테고리 리스트를 설정
	        request.setAttribute("listCategory", listCategory);
	        // 요청 속성에 Book 객체를 설정
	        request.setAttribute("book", book);
	        
	    } else { // 만약 book 객체가 null이면
	        // 목적지 페이지를 에러 메시지를 보여줄 frontend/message.jsp로 변경
	        destPage = "frontend/message.jsp";
	        // 에러 메시지 작성
	        String message = "Sorry, the book with ID " + bookId + " is not available.";
	        // 요청 속성에 메시지를 설정
	        request.setAttribute("message", message);            
	    }

	    // 요청을 특정 목적지로 전달하기 위해 RequestDispatcher를 가져옴
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
	    // 요청과 응답을 특정 목적지로 전달함
	    requestDispatcher.forward(request, response);
	}

	
//	public void viewBookDetail() throws ServletException, IOException {
//	    Integer bookId = Integer.parseInt(request.getParameter("id")); // 요청에서 bookId 가져오기
//	    Book book = bookDAO.get(bookId); // 해당 bookId에 해당하는 Book 객체 가져오기
//	    List<Category> listCategory = categoryDAO.listAll();
//	    
//	    request.setAttribute("listCategory", listCategory); // 요청 속성에 Book 객체 설정
//	    request.setAttribute("book", book); // 요청 속성에 Book 객체 설정
//
//	    String detailPage = "frontend/book_detail.jsp"; // 보여줄 상세 페이지 지정
//	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailPage); // 요청 디스패처 생성
//	    requestDispatcher.forward(request, response); // 지정된 페이지로 포워딩
//	}

	// 키워드를 이용해 책을 검색하고 검색 결과 페이지로 포워딩
	public void search() throws ServletException, IOException {
	    String keyword = request.getParameter("keyword"); // 요청에서 키워드 가져오기
	    List<Book> result = null; 
	    
	    // 키워드에 따라 검색 결과를 가져오기
	    if (keyword.equals("")) {
	        result = bookDAO.listAll(); // 키워드가 없는 경우 모든 책 목록 가져오기
	    } else {
	        result = bookDAO.search(keyword); // 키워드로 책 검색하기
	    }
	    
	    request.setAttribute("keyword", keyword); // 요청 속성에 키워드 설정
	    request.setAttribute("result", result); // 요청 속성에 검색 결과 설정
	    
	    String resultPage = "frontend/search_result.jsp"; // 검색 결과 페이지 지정
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage); // 요청 디스패처 생성
	    requestDispatcher.forward(request, response); // 지정된 페이지로 포워딩
	}

}
