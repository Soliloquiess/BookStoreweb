package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

    public AdminLoginFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // HttpServletRequest로 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 세션 획득
        HttpSession session = httpRequest.getSession(false);

        // 사용자가 로그인했는지 확인
        boolean loggedIn = session != null && session.getAttribute("useremail") != null;
        // 로그인 페이지 URI
        String loginURI = httpRequest.getContextPath() + "/admin/login";
        
//        getContextPath() 메서드는 HttpServletRequest 인터페이스에서 제공되는 메서드로, 현재 웹 애플리케이션의 컨텍스트 경로를 반환합니다.
//        웹 애플리케이션은 서블릿 컨테이너 내에서 실행되며, 각 애플리케이션은 고유한 컨텍스트 경로를 가지고 있습니다. 이 컨텍스트 경로는 웹 애플리케이션이 서버에서 실행될 때 해당 애플리케이션의 루트 URL을 의미합니다.
//        예를 들어, 웹 애플리케이션이 http://localhost:8080/myapp에 배포되었다면, getContextPath()는 /myapp을 반환합니다. 이렇게 반환된 컨텍스트 경로는 링크를 생성하거나 리다이렉트하는 데 사용되며, 웹 애플리케이션의 루트를 기준으로 상대 경로를 사용할 수 있도록 도와줍니다.
        
        // 요청이 로그인 페이지인지 확인
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        // 로그인 페이지인지 확인
        boolean loginPage = httpRequest.getRequestURI().endsWith("login.jsp");

     // 요청이 "/admin"으로 끝나는지 확인
//        boolean adminRequest = httpRequest.getRequestURI().endsWith("/admin");
//
//        if (loggedIn && (adminRequest || loginPage)) {
//            // Admin 페이지로 포워딩
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
//            dispatcher.forward(request, response);
//        } else if (loggedIn || adminRequest) {
//            System.out.println("사용자가 로그인했습니다.");
//            // 다음 필터로 진행
//            chain.doFilter(request, response);
//        } else {
//            System.out.println("사용자가 로그인하지 않았습니다.");
//            // 로그인 페이지로 리다이렉트
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
//            dispatcher.forward(request, response);
//        }

        
        // 만약 로그인이 되어 있고, 로그인 요청 또는 로그인 페이지에 접근하려 할 때
        if (loggedIn && (loginRequest || loginPage)) {
            // Admin 페이지로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);

        // 만약 로그인이 되어 있거나 로그인 요청이 들어왔을 때
        } else if (loggedIn || loginRequest) {
            System.out.println("사용자가 로그인했습니다.");
            // 다음 필터로 진행
            chain.doFilter(request, response);
        // 그 외의 경우 (로그인이 안 되어 있을 때)
        } else {
            System.out.println("사용자가 로그인하지 않았습니다.");
            // 로그인 페이지로 리다이렉트
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
