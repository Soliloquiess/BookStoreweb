package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {
	private CategoryDAO categoryDAO; // CategoryDAO 객체 선언
	private HttpServletRequest request; // HttpServletRequest 객체 선언
	private HttpServletResponse response; // HttpServletResponse 객체 선언
	
	// CategoryServices 생성자
	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request; // HttpServletRequest 할당
		this.response = response; // HttpServletResponse 할당
		
		categoryDAO = new CategoryDAO(); // CategoryDAO 객체 생성
	}
	
	// 카테고리 목록을 가져와서 JSP 페이지에 표시하는 메서드
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll(); // 모든 카테고리 목록 가져오기
		
		request.setAttribute("listCategory", listCategory); // JSP 페이지에 카테고리 목록 속성으로 설정
		
		if (message != null) {
			request.setAttribute("message", message); // 메시지 속성으로 설정
		}
		
		String listPage = "category_list.jsp"; // 카테고리 목록이 표시될 JSP 페이지
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

		requestDispatcher.forward(request, response); // 요청과 응답 전달
	}
	
	// listCategory 메서드를 오버로딩하여 메시지가 없는 경우를 처리하는 메서드
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}
	
	// 새로운 카테고리를 생성하는 메서드
	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name"); // HTTP 요청에서 카테고리 이름 가져오기
		Category existCategory = categoryDAO.findByName(name); // 이름으로 이미 있는 카테고리 확인
		
		// 이미 있는 경우 메시지를 설정하고 message.jsp로 포워딩
		if (existCategory != null) {
			String message = "Could not create category."
					+ " A category with name " + name + " already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);			
		} else {
			// 새로운 카테고리를 생성하고 메시지와 함께 카테고리 목록 페이지로 이동
			Category newCategory = new Category(name);
			categoryDAO.create(newCategory);
			String message = "New category created successfully.";
			listCategory(message);
		}
	}
	
	// 카테고리 수정 페이지로 이동하는 메서드
//	public void editCategory() throws ServletException, IOException {
//		int categoryId = Integer.parseInt(request.getParameter("id")); // 카테고리 ID 가져오기
//		Category category = categoryDAO.get(categoryId); // 해당 ID의 카테고리 가져오기
//		request.setAttribute("category", category); // JSP 페이지에 카테고리 속성으로 설정
//		
//		String editPage = "category_form.jsp"; // 카테고리 수정 폼이 표시될 JSP 페이지
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
//		requestDispatcher.forward(request, response); // 요청과 응답 전달		
//	}

	// 카테고리 수정 페이지로 이동하는 메서드(카테고리 편집기능 개선)
//	두 명 또는 관리자/관리자가 동시에 카테고리를 관리하는 경우가 있습니다. 다음과 같은 시나리오를 상상해 보십시오.
//	- 첫 번째 관리자가 카테고리 목록을 봅니다.
//	- 두 번째 관리자가 범주 목록을 봅니다.
//	- 첫 번째 관리자가 '카툰' 카테고리를 삭제합니다(ID: 122).
//	- 두 번째 관리자는 삭제된 '만화' 카테고리를 편집하려고 합니다.
//	이 경우 두 번째 관리자에게 의미 있는 오류 메시지가 표시되어야 합니다
	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		
		String destPage = "category_form.jsp";
		
		if (category != null) {
			request.setAttribute("category", category);	
		} else {
			String message = "Could not find category with ID " + categoryId;
			request.setAttribute("message", message);
			destPage = "message.jsp";
		}			
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);		
		
	}
	
	
	// 카테고리 정보를 업데이트하는 메서드
	public void updateCategory() throws ServletException, IOException {
		// HTTP 요청에서 카테고리 ID와 이름 가져오기
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");
		
		// 해당 ID와 이름으로 카테고리 가져오기
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);
		
		// 동일한 이름의 카테고리가 이미 존재하는 경우
		if (categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			// 메시지 설정하고 message.jsp로 포워딩
			String message = "Could not update category."
					+ " A category with name " + categoryName + " already exists.";
			
			request.setAttribute("message", message);			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);			
		} else {
			// 카테고리 이름 업데이트하고 메시지와 함께 카테고리 목록 페이지로 이동
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message = "Category has been updated successfully";
			listCategory(message);
		}
	}


//	public void deleteCategory() throws ServletException, IOException {
//		int categoryId = Integer.parseInt(request.getParameter("id")); // 카테고리 ID 가져오기
//		BookDAO bookDAO = new BookDAO(); // BookDAO 객체 생성
//		long numberOfBooks = bookDAO.countByCategory(categoryId); // 해당 카테고리에 속한 책의 수 가져오기
//		String message;
//		
//		// 카테고리에 속한 책이 있는 경우
//		if (numberOfBooks > 0) {
//			// 메시지 설정 (삭제 불가능) 및 카테고리 목록 페이지로 이동
//			message = "Could not delete the category (ID: %d) because it currently contains some books.";
//			message = String.format(message, numberOfBooks);
//		} else {
//			// 카테고리 삭제 후 메시지 설정 및 카테고리 목록 페이지로 이동
//			categoryDAO.delete(categoryId);		
//			message = "The category with ID " + categoryId + " has been removed successfully.";
//		}
//		
//		listCategory(message); // 카테고리 목록 페이지로 이동
//		
//	}
	
	// 카테고리 삭제 메서드
	//관리자가 다른 관리자가 삭제한 범주를 삭제하려고 할 때 오류 메시지를 표시하도록 범주 삭제 기능을 업데이트합니다. 오류 메시지는 다음과 같습니다. ID가 [cat_id]인 카테고리를 찾을 수 없거나 삭제되었을 수 있습니다.' [cat_id]를 실제 값으로 바꿉니다.
	//삭제 메서드 개선
	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		String message;
		
		Category category = categoryDAO.get(categoryId);
		
		if (category == null) {
			message = "Could not find category with ID " + categoryId
					+ ", or it might have been deleted";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}
		
		BookDAO bookDAO = new BookDAO();
		long numberOfBooks = bookDAO.countByCategory(categoryId);
				
		if (numberOfBooks > 0) {
			message = "Could not delete the category (ID: %d) because it currently contains some books.";
			message = String.format(message, numberOfBooks);
		} else {
			categoryDAO.delete(categoryId);		
			message = "The category with ID " + categoryId + " has been removed successfully.";
		}
		
		listCategory(message);
		
	}
	
}
