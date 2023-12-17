package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update_cart") // "/update_cart" URL 패턴으로 이 서블릿을 매핑한다.

public class UpdateCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // 직렬화 버전 UID로 객체 직렬화 시 사용되는 ID

    public UpdateCartServlet() {
        super(); // 부모 클래스의 기본 생성자 호출
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] arrayBookIds = request.getParameterValues("bookId"); // bookId 매개변수로 전달된 모든 값들을 배열로 가져옴
        String[] arrayQuantities = new String[arrayBookIds.length]; // bookId 배열의 길이만큼의 새로운 문자열 배열 생성

        for (int i = 1; i <= arrayQuantities.length; i++) { // 배열의 길이만큼 반복
            String aQuantity = request.getParameter("quantity" + i); // "quantity" + i에 해당하는 매개변수 값을 가져옴
            arrayQuantities[i - 1] = aQuantity; // 배열에 해당 값을 할당
        }

//        String[] arrayBookIds = request.getParameterValues("bookId");: HTTP 요청에서 "bookId" 매개변수로 전달된 모든 값을 배열로 가져옵니다. "bookId" 매개변수는 여러 개의 도서 식별자(bookId)가 전달될 수 있습니다. 이를 배열로 받아옵니다.
//        String[] arrayQuantities = new String[arrayBookIds.length];: "bookId" 배열의 길이만큼의 새로운 문자열 배열을 생성합니다. 이 배열은 "quantity" 매개변수 값들을 저장할 용도로 사용됩니다. "quantity" 매개변수는 각각의 도서에 대한 수량을 나타냅니다.
//        for (int i = 1; i <= arrayQuantities.length; i++) {: 배열의 길이만큼 반복하는 반복문을 시작합니다. 이 반복문은 "quantity" 매개변수들에 대한 값을 가져와 배열에 저장하는 역할을 합니다. 여기서 i는 1부터 배열의 길이까지 증가하며, 각 도서별로 "quantity1", "quantity2", ..., "quantityN"에 해당하는 값을 가져오기 위해 사용됩니다.
//        String aQuantity = request.getParameter("quantity" + i);: HTTP 요청에서 "quantity" + i에 해당하는 매개변수 값을 가져옵니다. 이는 각각의 도서에 대한 수량을 나타냅니다.
//        arrayQuantities[i - 1] = aQuantity;: 가져온 "quantity" 매개변수 값을 배열에 할당합니다. 배열의 인덱스는 0부터 시작하므로, i의 값에서 1을 뺀 값(i - 1)에 해당하는 배열 위치에 값을 저장합니다. 이를 통해 각 도서별로 받아온 수량 값을 배열에 저장합니다.
//        즉, 이 코드는 각각의 도서에 대한 식별자(bookId)와 수량(quantity)을 HTTP 요청에서 배열로 받아와 배열에 저장하는 과정을 수행합니다.
        
     int[] bookIds = Arrays.stream(arrayBookIds).mapToInt(Integer::parseInt).toArray();
     // 문자열 배열(arrayBookIds)을 int 배열(bookIds)로 변환합니다.
     // Arrays.stream을 사용하여 각 문자열을 Integer.parseInt를 통해 int로 변환하고, toArray()로 int 배열로 저장합니다.

     int[] quantities = Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
     // 문자열 배열(arrayQuantities)을 int 배열(quantities)로 변환합니다.
     // Arrays.stream을 사용하여 각 문자열을 Integer.parseInt를 통해 int로 변환하고, toArray()로 int 배열로 저장합니다.

     ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
     // HttpSession에서 "cart" 속성값을 가져옵니다. 이를 ShoppingCart 객체로 형변환하여 cart 변수에 저장합니다.

     cart.updateCart(bookIds, quantities);
     // 가져온 bookIds와 quantities 배열을 사용하여, 장바구니(cart)를 업데이트합니다.
     // 이를 통해 장바구니의 아이템들의 수량이나 내용을 갱신합니다.

     String cartPage = request.getContextPath().concat("/view_cart");
     // 장바구니 페이지로의 경로를 설정합니다. getContextPath()로 컨텍스트 경로를 가져온 후, "/view_cart"를 추가하여 설정합니다.

     response.sendRedirect(cartPage);
     // 클라이언트에게 장바구니 페이지로 리다이렉트하도록 응답합니다.
     // 이를 통해 사용자가 장바구니를 업데이트한 후에 장바구니 페이지로 이동하게 됩니다.

    }
}

//@WebServlet("/update_cart") 어노테이션은 "/update_cart" URL 패턴으로 이 서블릿을 매핑하는 것을 나타냅니다. doPost 메서드는 HTTP POST 요청을 처리하며, 요청으로부터 "bookId"와 "quantity" 매개변수를 받아 장바구니를 업데이트합니다. 코드의 주석은 각 줄별로 코드의 기능과 동작을 설명하고 있습니다.
