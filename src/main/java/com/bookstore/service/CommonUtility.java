package com.bookstore.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUtility {

    // 이 메서드는 지정된 페이지로 메시지를 포함하여 forward하는 역할을 합니다.
	public static void forwardToPage(String page, String message,
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher(page).forward(request, response);		
	}
	
	// Frontend에서 메시지를 보여주기 위한 메서드입니다.
	public static void showMessageFrontend(String message, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		forwardToPage("frontend/message.jsp", message, request, response);
	}
	
	// Backend에서 메시지를 보여주기 위한 메서드입니다.
	public static void showMessageBackend(String message, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		forwardToPage("message.jsp", message, request, response);
	}

	// 페이지로 forward하는 메서드입니다.
	public static void forwardToPage(String page,
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		request.getRequestDispatcher(page).forward(request, response);		
	}	
}


//package com.bookstore.service;
//
//import java.util.Locale;
//import java.util.Map;
//import java.util.TreeMap;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class CommonUtility {
//
//    // HttpServletRequest에 국가 목록을 생성하여 설정하는 메서드입니다.
//    public static void generateCountryList(HttpServletRequest request) {
//        String[] countryCodes = Locale.getISOCountries();
//		
//        Map<String, String> mapCountries = new TreeMap<>();
//		
//        // 모든 국가 코드에 대해 국가 이름과 코드를 가져와 맵에 추가합니다.
//        for (String countryCode : countryCodes) {
//            Locale locale = new Locale("", countryCode);
//            String code = locale.getCountry();
//            String name = locale.getDisplayCountry();
//			
//            mapCountries.put(name, code);
//        }
//		
//        request.setAttribute("mapCountries", mapCountries);
//    }	
//}
