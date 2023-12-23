<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book to Order</title>
</head>
<body>
	<div align="center">
    <!-- 내용을 가운데 정렬하는 div 요소를 생성합니다. -->
    
    <h2>
        <!-- 제목을 표시하는 h2 태그를 생성합니다. -->
        
        The book <i>${book.title}</i> has been added to the order ID <b>${order.orderId}</b>
        <!-- 텍스트 내에 동적으로 채워지는 책 제목과 주문 ID를 출력합니다. -->
        <!-- ${book.title}과 ${order.orderId}는 서버 측에서 동적으로 제공되는 데이터입니다. -->
    </h2>
    
    	<!-- "Close" 라벨을 가진 버튼을 생성하고, 클릭 시 현재 창을 닫는 자바스크립트 기능을 수행합니다. -->
    	<input type="button" value="Close" onclick="javascript: self.close();" />
	</div>
	
	<script>
	    // 윈도우의 언로드(unload) 이벤트가 발생할 때 실행되는 함수를 정의합니다.
	    window.onunload = function() {
	        // 부모 창(window.opener)을 새로고침하여 변경된 정보를 반영합니다.
	        window.opener.location.reload();
	    }
	</script>
</body>
</html>