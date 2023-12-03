package com.bookstore.service; // com.bookstore.service 패키지에 속한 클래스입니다.

import java.io.IOException; // IOException을 import합니다.
import java.util.List; // List를 import합니다.

import javax.servlet.RequestDispatcher; // RequestDispatcher를 import합니다.
import javax.servlet.ServletException; // ServletException을 import합니다.
import javax.servlet.http.HttpServletRequest; // HttpServletRequest를 import합니다.
import javax.servlet.http.HttpServletResponse; // HttpServletResponse를 import합니다.

import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.UserDAO; // UserDAO를 import합니다.
import com.bookstore.entity.Users; // Users 엔터티를 import합니다.

// UserServices 클래스 선언
public class UserServices {
    private UserDAO userDAO; // UserDAO 객체를 선언합니다.
    private HttpServletRequest request; // HttpServletRequest 객체를 선언합니다.
    private HttpServletResponse response; // HttpServletResponse 객체를 선언합니다.

    // 생성자: HttpServletRequest 및 HttpServletResponse를 매개변수로 받아 초기화합니다.
    public UserServices(HttpServletRequest request, HttpServletResponse response) {
        this.request = request; // 전달된 request를 할당합니다.
        this.response = response; // 전달된 response를 할당합니다.
        userDAO = new UserDAO(); // UserDAO 객체를 초기화합니다.
    }

    // 사용자 목록을 가져오는 메소드
    public void listUser() throws ServletException, IOException {
        listUser(null); // 아래의 메소드로 위임하며, message는 null로 설정합니다.
    }

    // 메시지가 있는 사용자 목록을 가져오는 메소드
    public void listUser(String message) throws ServletException, IOException {
        List<Users> listUsers = userDAO.listAll(); // 모든 사용자 목록을 가져옵니다.

        request.setAttribute("listUsers", listUsers); // 사용자 목록을 request에 저장합니다.
        
        // 기본 메시지를 설정하고, message가 null이 아닌 경우 해당 메시지로 설정합니다.
        //request.setAttribute("message", "New User created success");
        if (message != null) {
            request.setAttribute("message", message);
        }

        String listPage = "user_list.jsp"; // 사용자 목록을 보여줄 페이지를 지정합니다.
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage); // RequestDispatcher 생성합니다.

        requestDispatcher.forward(request, response); // 사용자 목록 페이지로 포워딩합니다.
    }

    // 사용자 생성 메소드
    public void createUser() throws ServletException, IOException {
        // 사용자로부터 이메일, 이름, 비밀번호를 받아옵니다.
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String password = request.getParameter("password");

        Users existUser = userDAO.findByEmail(email); // 이메일로 사용자를 검색합니다.

        // 이미 존재하는 사용자인 경우 에러 메시지를 전달하고 해당 페이지로 포워딩합니다.
        if (existUser != null) {
            String message = "Could not create user. A user with email " + email + " already exists";
            request.setAttribute("message", message);	//메시지 세팅
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");	//사용자를 메시지페이지로 리디렉션(jsp)
            dispatcher.forward(request, response);	//요청 디스패쳐 호출 
        } else {
            Users newUser = new Users(email, fullName, password); // 새 사용자 객체 생성
            userDAO.create(newUser); // 사용자를 생성합니다.
            listUser("New user created successfully"); // 생성된 사용자 목록을 보여줍니다.
        }
        
        
//        String email = request.getParameter("email");, String fullName = request.getParameter("fullname");, String password = request.getParameter("password");: HTTP 요청에서 사용자가 입력한 이메일, 이름, 비밀번호를 가져옵니다.
//        Users existUser = userDAO.findByEmail(email);: 가져온 이메일을 사용하여 해당 이메일을 가진 사용자를 데이터베이스에서 찾습니다. 이때, userDAO는 데이터베이스와 상호작용하여 사용자 정보를 가져오는 DAO(Data Access Object) 클래스일 것입니다.
//        if (existUser != null) { ... } else { ... }: 찾아낸 사용자 객체가 null이 아닌 경우 (이미 해당 이메일을 사용하는 사용자가 존재하는 경우)와 그렇지 않은 경우를 처리합니다.
//        existUser != null: 이미 존재하는 사용자인 경우, 에러 메시지를 생성하고 이를 message라는 이름으로 설정한 뒤, 이를 현재 요청 객체(request)의 속성(attribute)으로 추가합니다. 그리고 사용자에게 메시지를 표시할 message.jsp로 요청을 포워딩합니다. RequestDispatcher를 사용하여 현재 요청과 응답을 새로운 페이지로 전달합니다.
//        existUser == null: 해당 이메일을 가진 사용자가 존재하지 않는 경우, 새로운 Users 객체를 생성하고 이 정보를 이용하여 새로운 사용자를 생성합니다. 그 후 listUser() 메서드를 호출하여 "New user created successfully"라는 메시지와 함께 생성된 사용자 목록을 보여줍니다.
//        이 코드는 이메일을 통해 사용자를 검색하고, 이미 존재하는 사용자인지를 확인한 뒤, 존재하지 않는 경우 새로운 사용자를 생성하고, 이미 존재하는 경우 사용자에게 알림을 제공하는 간단한 사용자 생성 기능을 수행합니다.
    }


    // 사용자 편집 메소드 개선판
	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId );

		String destPage = "user_form.jsp";
		
		if (user == null) {
			destPage = "message.jsp";
			String errorMessage = "Could not find user with ID " + userId;
			request.setAttribute("message", errorMessage);
		} else {
			// 패스워드를 null로 설정하여 기본적으로 패스워드를 비워두도록 합니다.
			// 비워둔 경우, 사용자의 패스워드는 업데이트되지 않습니다.
			// 이는 암호화된 패스워드 기능과 함께 작동하기 위한 것입니다.
			// 사용자의 패스워드를 빈 값으로 설정하여 기존 암호화된 패스워드를 유지합니다.
			user.setPassword(null);

			request.setAttribute("user", user);			
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
		
	}
    
//    
// // 사용자 편집 메소드
//    public void editUser() throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("id")); // 사용자 ID를 가져옵니다.
//        Users user = userDAO.get(userId); // 해당 ID의 사용자 정보를 가져옵니다.
//
//        String editPage = "user_form.jsp"; // 사용자 정보를 편집할 페이지를 지정합니다.
//        request.setAttribute("user", user); // 사용자 정보를 request에 저장합니다.
//
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage); // RequestDispatcher를 생성합니다.
//        requestDispatcher.forward(request, response); // 사용자 정보 편집 페이지로 포워딩합니다.
//    }

    // 사용자 업데이트 메소드
    public void updateUser() throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId")); // 사용자 ID를 가져옵니다.
        String email = request.getParameter("email"); // 사용자 이메일을 가져옵니다.
        String fullName = request.getParameter("fullname"); // 사용자 이름을 가져옵니다.
        String password = request.getParameter("password"); // 사용자 비밀번호를 가져옵니다.

        Users userById = userDAO.get(userId); // 해당 ID의 사용자 정보를 가져옵니다.
        Users userByEmail = userDAO.findByEmail(email); // 이메일로 사용자 정보를 가져옵니다.

        // 이메일이 이미 존재하는 경우 또는 업데이트하려는 사용자가 현재 사용자와 다른 경우 에러 메시지를 표시합니다.
        if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
            String message = "Could not update user. User with email " + email + " already exists.";
            request.setAttribute("message", message);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
            requestDispatcher.forward(request, response);
        } else {
        	
        	// 사용자의 이메일을 업데이트합니다.
        	userById.setEmail(email);

        	// 사용자의 전체 이름을 업데이트합니다.
        	userById.setFullName(fullName);

        	// 만약 패스워드가 null이 아니고 비어있지 않다면, 새로운 패스워드로 업데이트합니다.
        	if (password != null & !password.isEmpty()) {
        	    // 입력된 패스워드를 MD5로 암호화하여 저장합니다.
        	    String encryptedPassword = HashGenerator.generateMD5(password);
        	    userById.setPassword(encryptedPassword);
        	}

        	// 사용자 정보를 데이터베이스에 업데이트합니다.
        	userDAO.update(userById);

        	// 사용자가 성공적으로 업데이트되었음을 알리는 메시지를 설정하고 사용자 목록을 다시 표시합니다.
        	String message = "사용자가 성공적으로 업데이트되었습니다";
        	listUser(message);

//            Users user = new Users(userId, email, fullName, password); // 새로운 사용자 객체를 생성합니다.
//            userDAO.update(user); // 사용자 정보를 업데이트합니다.
//
//            String message = "User has been updated successfully"; // 성공 메시지를 설정합니다.
//            listUser(message); // 사용자 목록 페이지로 이동하며 메시지를 전달합니다.
        }
    }

//    // 사용자 삭제 메소드
//    public void deleteUser() throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("id")); // 사용자 ID를 가져옵니다.
//        userDAO.delete(userId); // 해당 ID의 사용자를 삭제합니다.
//
//        String message = "User has been deleted successfully"; // 성공 메시지를 설정합니다.
//        listUser(message); // 사용자 목록 페이지로 이동하며 메시지를 전달합니다.
//    }
//
//
//    // 사용자 삭제 메소드
//	// 관리자가 다른 관리자가 삭제한 사용자를 삭제하려고 하면 웹 사이트에 다음 메시지가 표시됩니다. 'ID가 [user_id]인 사용자를 찾을 수 없거나 다른 관리자가 삭제했을 수 있습니다.' [user_id]를 실제 값으로 바꿉니다.
//
//	public void deleteUser() throws ServletException, IOException {
//		int userId = Integer.parseInt(request.getParameter("id"));
//		
//		Users user = userDAO.get(userId);
//		String message = "User has been deleted successfully";
//		
//		if (user == null) {
//			message = "Could not find user with ID " + userId
//					+ ", or it might have been deleted by another admin";
//			
//			request.setAttribute("message", message);
//			request.getRequestDispatcher("message.jsp").forward(request, response);			
//		} else {
//			userDAO.delete(userId);
//			listUser(message);
//		}		
//	}


    // 사용자 삭제 메소드
	//시스템에는 삭제할 수 없는 기본 관리자 계정이 있어야 합니다. 이 사용자의 ID는 1입니다. 관리 사용자가 이 기본 계정을 삭제하려고 하면 애플리케이션에 다음 메시지가 표시됩니다. '기본 관리자 사용자 계정을 삭제할 수 없습니다.'

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		
		String message = "User has been deleted successfully";
		
		//사용자 관련 코드 추가(어드민 계정)
		if (userId == 1) {
			message = "The default admin user account cannot be deleted.";
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}
		// 나머지 코드
		Users user = userDAO.get(userId);
		
		if (user == null) {
			message = "Could not find user with ID " + userId
					+ ", or it might have been deleted by another admin";
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);			
		} else {
			userDAO.delete(userId);
			listUser(message);
		}		

	}

    // 로그인 메소드
    public void login() throws ServletException, IOException {
        String email = request.getParameter("email"); // 사용자 이메일을 가져옵니다.
        String password = request.getParameter("password"); // 사용자 비밀번호를 가져옵니다.

        boolean loginResult = userDAO.checkLogin(email, password); // 로그인을 확인합니다.

        // 로그인 성공 시 관리자 페이지로 이동합니다. 실패 시 에러 메시지를 표시합니다.
        if (loginResult) {
            // 로그인 성공 시 세션에 사용자 이메일을 저장합니다.
            request.getSession().setAttribute("useremail", email);

            // 로그인 성공 시, 관리자 페이지로 제어를 전달합니다.
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        } else {
            String message = "Login failed!"; // 로그인 실패 메시지를 설정합니다.
            request.setAttribute("message", message);

            // 로그인 실패 시, 다시 로그인 페이지로 제어를 전달합니다.
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }
}


/*
 * RequestDispatcher는 서블릿 내에서 다른 자원(서블릿, JSP 페이지 또는 HTML 파일 등)으로 제어를 전달하는 데 사용되는
 * 인터페이스입니다.
 * 
 * getRequestDispatcher() 메서드는 현재 HTTP 요청과 HTTP 응답을 그대로 유지하면서 다른 자원으로 제어를 넘기는데
 * 사용됩니다. 이를 통해 서블릿이나 JSP 페이지 등의 자원으로 제어를 전달하고 해당 자원에서 추가적인 처리를 할 수 있습니다.
 * 
 * java Copy code RequestDispatcher dispatcher =
 * request.getRequestDispatcher("login.jsp"); dispatcher.forward(request,
 * response); 위의 코드에서 getRequestDispatcher("login.jsp")는 "login.jsp"라는 이름의 JSP
 * 페이지로 제어를 넘기기 위한 RequestDispatcher 객체를 얻습니다. 여기서 "login.jsp"는 경로 또는 URL이 될 수
 * 있습니다. 보통은 상대 경로를 사용하여 해당 웹 애플리케이션 내의 리소스에 접근합니다.
 * 
 * dispatcher.forward(request, response)는 현재 요청과 응답 객체를 "login.jsp"로 전달하여,
 * 클라이언트에게 전송할 응답을 해당 JSP 페이지에서 생성하고 출력하도록 제어를 넘깁니다. 이후 클라이언트에게 응답이 반환될 때까지 제어는
 * 해당 JSP 페이지에 있게 됩니다.
 * 
 * 즉, 이 부분의 코드는 사용자가 로그인하는 데 사용되는 데이터를 "login.jsp" 페이지로 전송하고, 해당 페이지에서는 로그인에 대한
 * 추가적인 처리를 수행한 후 사용자에게 결과를 보여줄 수 있도록 제어를 넘겨주는 역할을 합니다.
 */

/*
 * package com.bookstore.service;
 * 
 * import java.io.IOException; import java.util.List;
 * 
 * import javax.servlet.RequestDispatcher; import
 * javax.servlet.ServletException; import javax.servlet.http.HttpServletRequest;
 * import javax.servlet.http.HttpServletResponse;
 * 
 * import com.bookstore.dao.UserDAO; import com.bookstore.entity.Users;
 * 
 * public class UserServices { private UserDAO userDAO; private
 * HttpServletRequest request; private HttpServletResponse response;
 * 
 * public UserServices(HttpServletRequest request, HttpServletResponse response)
 * { this.request = request; this.response = response; userDAO = new UserDAO();
 * }
 * 
 * public void listUser() throws ServletException, IOException { listUser(null);
 * }
 * 
 * public void listUser(String message) throws ServletException, IOException {
 * List<Users> listUsers = userDAO.listAll();
 * 
 * request.setAttribute("listUsers", listUsers);
 * 
 * if (message != null) { request.setAttribute("message", message); }
 * 
 * String listPage = "user_list.jsp"; RequestDispatcher requestDispatcher =
 * request.getRequestDispatcher(listPage);
 * 
 * requestDispatcher.forward(request, response);
 * 
 * }
 * 
 * public void createUser() throws ServletException, IOException { String email
 * = request.getParameter("email"); String fullName =
 * request.getParameter("fullname"); String password =
 * request.getParameter("password");
 * 
 * Users existUser = userDAO.findByEmail(email);
 * 
 * if (existUser != null) { String message =
 * "Could not create user. A user with email " + email + " already exists";
 * request.setAttribute("message", message); RequestDispatcher dispatcher =
 * request.getRequestDispatcher("message.jsp"); dispatcher.forward(request,
 * response);
 * 
 * } else { Users newUser = new Users(email, fullName, password);
 * userDAO.create(newUser); listUser("New user created successfully"); }
 * 
 * }
 * 
 * public void editUser() throws ServletException, IOException { int userId =
 * Integer.parseInt(request.getParameter("id")); Users user = userDAO.get(userId
 * );
 * 
 * String editPage = "user_form.jsp"; request.setAttribute("user", user);
 * 
 * RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
 * requestDispatcher.forward(request, response);
 * 
 * }
 * 
 * public void updateUser() throws ServletException, IOException { int userId =
 * Integer.parseInt(request.getParameter("userId")); String email =
 * request.getParameter("email"); String fullName =
 * request.getParameter("fullname"); String password =
 * request.getParameter("password");
 * 
 * Users userById = userDAO.get(userId);
 * 
 * Users userByEmail = userDAO.findByEmail(email);
 * 
 * if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
 * String message = "Could not update user. User with email " + email +
 * " already exists."; request.setAttribute("message", message);
 * 
 * RequestDispatcher requestDispatcher =
 * request.getRequestDispatcher("message.jsp");
 * requestDispatcher.forward(request, response);
 * 
 * } else { Users user = new Users(userId, email, fullName, password);
 * userDAO.update(user);
 * 
 * String message = "User has been updated successfully"; listUser(message); }
 * 
 * }
 * 
 * public void deleteUser() throws ServletException, IOException { int userId =
 * Integer.parseInt(request.getParameter("id")); userDAO.delete(userId);
 * 
 * String message = "User has been deleted successfully"; listUser(message); }
 * 
 * public void login() throws ServletException, IOException { String email =
 * request.getParameter("email"); String password =
 * request.getParameter("password");
 * 
 * boolean loginResult = userDAO.checkLogin(email, password);
 * 
 * if (loginResult) { request.getSession().setAttribute("useremail", email);
 * 
 * RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
 * dispatcher.forward(request, response);
 * 
 * } else { String message = "Login failed!"; request.setAttribute("message",
 * message);
 * 
 * RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
 * dispatcher.forward(request, response); } } }
 */