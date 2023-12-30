package com.bookstore.service;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUtility {

	// HttpServletRequest에 국가 목록을 생성하여 설정하는 메서드입니다.
	public static void generateCountryList(HttpServletRequest request) {
	    // Locale.getISOCountries()를 통해 ISO 3166-1에 따른 모든 국가 코드를 가져옵니다.
	    String[] countryCodes = Locale.getISOCountries();
		
	    // TreeMap을 사용하여 국가를 정렬하여 저장할 Map을 생성합니다.
	    Map<String, String> mapCountries = new TreeMap<>();
		
	    // 모든 국가 코드에 대해 국가 이름과 코드를 가져와 맵에 추가합니다.
	    for (String countryCode : countryCodes) {
	        // 해당 국가 코드로부터 Locale을 생성합니다.
	    	System.out.println("countrycode1231232131"+ countryCode);
	        Locale locale = new Locale("", countryCode);
	        
	        // Locale 객체를 이용하여 국가 코드와 이름을 가져옵니다.
	        String code = locale.getCountry(); // 국가 코드
	        String name = locale.getDisplayCountry(); // 국가 이름
	        
	        // 맵에 국가 이름(key)과 국가 코드(value)를 추가합니다.
	        mapCountries.put(name, code);
	    }
		
	    // HttpServletRequest의 속성인 "mapCountries"에 국가 목록을 저장합니다.
	    request.setAttribute("mapCountries", mapCountries);
	}	

//	public static void generateCountryList(HttpServletRequest request) { ... }: HttpServletRequest에 국가 목록을 생성하여 설정하는 정적(static) 메서드입니다.
//
//	String[] countryCodes = Locale.getISOCountries();: Locale 클래스의 getISOCountries() 메서드를 사용하여 ISO 3166-1에 따른 모든 국가 코드를 가져옵니다.
//
//	Map<String, String> mapCountries = new TreeMap<>();: TreeMap을 사용하여 국가를 정렬하여 저장할 Map을 생성합니다.
//
//	for (String countryCode : countryCodes) { ... }: 모든 국가 코드에 대해 반복문을 실행합니다.
//
//	Locale locale = new Locale("", countryCode);: 해당 국가 코드로부터 Locale 객체를 생성합니다.
//
//	String code = locale.getCountry();: Locale 객체를 이용하여 국가 코드를 가져옵니다.
//
//	String name = locale.getDisplayCountry();: Locale 객체를 이용하여 국가 이름을 가져옵니다.
//
//	mapCountries.put(name, code);: 맵에 국가 이름(key)과 국가 코드(value)를 추가합니다.
//
//	request.setAttribute("mapCountries", mapCountries);: HttpServletRequest의 속성인 "mapCountries"에 국가 목록을 저장합니다. 이렇게 저장된 속성은 해당 요청을 처리하는 JSP 등에서 사용할 수 있습니다.
    
    
    
	public static void forwardToPage(String page, String message,
	        HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	    // 요청 속성에 메시지 설정
	    request.setAttribute("message", message);
	    
	    // 지정된 페이지로 요청을 전달(forward)
	    request.getRequestDispatcher(page).forward(request, response);        
	}

	public static void showMessageFrontend(String message, 
	        HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	    // forwardToPage 메서드를 호출하여 frontend/message.jsp 페이지로 메시지를 전달
	    forwardToPage("frontend/message.jsp", message, request, response);
	}

	public static void showMessageBackend(String message, 
	        HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	    // forwardToPage 메서드를 호출하여 message.jsp 페이지로 메시지를 전달
	    forwardToPage("message.jsp", message, request, response);
	}

	public static void forwardToPage(String page,
	        HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	    // 지정된 페이지로 요청을 전달(forward)
	    request.getRequestDispatcher(page).forward(request, response);        
	}
	
//	public static void forwardToPage(String page, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { ... }:
//
//		요청 속성에 메시지를 설정하고, 지정된 페이지로 요청을 전달(forward)하는 메서드입니다.
//		public static void showMessageFrontend(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { ... }:
//
//		forwardToPage 메서드를 호출하여 "frontend/message.jsp" 페이지로 메시지를 전달하는 메서드입니다.
//		public static void showMessageBackend(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { ... }:
//
//		forwardToPage 메서드를 호출하여 "message.jsp" 페이지로 메시지를 전달하는 메서드입니다.
//		public static void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { ... }:
//
//		지정된 페이지로 요청을 전달(forward)하는 메서드입니다. 이 메서드는 메시지를 전달하지 않고 페이지만 전환하는 경우 사용될 수 있습니다.
	
}


//package com.bookstore.service;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class CommonUtility {
//
//    // 이 메서드는 지정된 페이지로 메시지를 포함하여 forward하는 역할을 합니다.
//	public static void forwardToPage(String page, String message,
//			HttpServletRequest request, HttpServletResponse response) 
//					throws ServletException, IOException {
//		request.setAttribute("message", message);
//		request.getRequestDispatcher(page).forward(request, response);		
//	}
//	
//	// Frontend에서 메시지를 보여주기 위한 메서드입니다.
//	public static void showMessageFrontend(String message, 
//			HttpServletRequest request, HttpServletResponse response) 
//					throws ServletException, IOException {
//		forwardToPage("frontend/message.jsp", message, request, response);
//	}
//	
//	// Backend에서 메시지를 보여주기 위한 메서드입니다.
//	public static void showMessageBackend(String message, 
//			HttpServletRequest request, HttpServletResponse response) 
//					throws ServletException, IOException {
//		forwardToPage("message.jsp", message, request, response);
//	}
//
//	// 페이지로 forward하는 메서드입니다.
//	public static void forwardToPage(String page,
//			HttpServletRequest request, HttpServletResponse response) 
//					throws ServletException, IOException {
//		request.getRequestDispatcher(page).forward(request, response);		
//	}	
//}


