<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Manage Orders - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Book Orders Management</h2>		
	</div>
	
	<c:if test="${message != null}"> <!-- message 변수가 null이 아닌 경우에만 실행 -->
	    <div align="center"> <!-- 가운데 정렬을 위한 div 요소 시작 -->
	        <h4 class="message">${message}</h4> <!-- message 변수의 내용을 포함하는 h4 제목 -->
	    </div> <!-- 가운데 정렬을 위한 div 요소 종료 -->
	</c:if>

	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered by</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrder}" varStatus="status"> <!-- listOrder의 각 요소(order)에 대해 반복 -->
		    <tr> <!-- 테이블의 한 행(row) 시작 -->
		        <td>${status.index + 1}</td> <!-- 순번을 나타내는 열(column) -->
		        <td>${order.orderId}</td> <!-- 주문 ID를 나타내는 열 -->
		        <td>${order.customer.fullname}</td> <!-- 고객의 전체 이름을 나타내는 열 -->
		        <td>${order.bookCopies}</td> <!-- 주문된 책의 사본 수를 나타내는 열 -->
		        <td><fmt:formatNumber value="${order.total}" type="currency" /></td> <!-- 주문 총액을 통화 형식으로 표시하는 열 -->
		        
		        <td>${order.paymentMethod}</td> <!-- 결제 방법을 나타내는 열 -->
		        <td>${order.status}</td> <!-- 주문 상태를 나타내는 열 -->
		        <td>${order.orderDate}</td> <!-- 주문 날짜를 나타내는 열 -->
		        <td>
		            <a href="view_order?id=${order.orderId}">Details</a> &nbsp; <!-- 주문 세부 정보를 보기 위한 링크 -->
		            <a href="edit_order?id=${order.orderId}">Edit</a> &nbsp; <!-- 주문을 수정하기 위한 링크 -->
		            <a href="javascript:void(0);" class="deleteLink" id="${order.orderId}">Delete</a> <!-- 주문을 삭제하기 위한 링크 -->
		        </td>
		    </tr> <!-- 테이블의 한 행(row) 종료 -->
		</c:forEach> <!-- forEach 반복문 종료 -->

		</table>
	</div>
	
	
	<jsp:directive.include file="footer.jsp" />
	
	<script>
	    $(document).ready(function() { // 문서가 준비되면 실행되는 JavaScript 함수
	        $(".deleteLink").each(function() { // deleteLink 클래스를 갖는 요소마다 반복문 실행
	            $(this).on("click", function() { // 해당 요소를 클릭했을 때 실행되는 함수
	                orderId = $(this).attr("id"); // 클릭한 요소의 id 속성 값을 가져와 orderId 변수에 저장
	                if (confirm('Are you sure you want to delete the order with ID ' +  orderId + '?')) { // 삭제 여부를 묻는 확인 대화상자 표시
	                    window.location = 'delete_order?id=' + orderId; // 확인을 선택하면 해당 주문을 삭제하는 페이지로 이동
	                }                   
	            });
	        });
	    }); 
	</script>

</body>
</html>