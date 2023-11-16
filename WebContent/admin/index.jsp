<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  <!--  JSP 페이지 언어 및 콘텐츠 유형을 설정하고 UTF-8 문자 인코딩 지정 -->
   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   <!--  JSTL fmt 태그 라이브러리를 사용하기 위한 선언 -->

<!DOCTYPE html>  <!--  HTML 문서 유형을 선언합니다. -->
<html>
<head>
    <meta charset="UTF-8">  <!-- 콘텐츠 문자 집합을 UTF-8로 설정합니다. -->
    <title>Evergreen Bookstore Administration</title>   <!--  페이지 제목을 설정합니다. -->
    <link rel="stylesheet" href="../css/style.css">   <!--  외부 CSS 파일을 연결합니다. -->
</head>
<body>
    <jsp:directive.include file="header.jsp" />   <!--  header.jsp 파일을 include하여 삽입합니다. -->

    <div align="center">
        <h2 class="pageheading">Administrative Dashboard</h2>   <!--  관리 대시보드의 제목을 설정합니다. -->
    </div>

    <div align="center">
        <hr width="60%" />   <!--  가로 줄을 60% 너비로 설정합니다. -->
        <h2 class="pageheading">Quick Actions:</h2>   <!--  빠른 작업 섹션의 제목을 설정합니다. -->
        <b>
            <a href="new_book">New Book</a> &nbsp;   <!--  '새 책 추가' 링크를 만듭니다. -->
            <a href="user_form.jsp">New User</a> &nbsp;   <!--  '새 사용자 추가' 링크를 만듭니다. -->
            <a href="category_form.jsp">New Category</a> &nbsp;   <!--  '새 카테고리 추가' 링크를 만듭니다. -->
            <a href="new_customer">New Customer</a>   <!--  '새 고객 추가' 링크를 만듭니다. -->
        </b>
    </div>

    <div align="center">
        <hr width="60%" />   <!--  가로 줄을 60% 너비로 설정합니다. -->
        <h2 class="pageheading">Recent Sales:</h2>   <!--  최근 판매 섹션의 제목을 설정합니다. -->
        <table border="1">   <!--  경계가 있는 테이블을 생성합니다. -->
            <tr>
                <th>Order ID</th>   <!--  주문 ID를 나타냅니다. -->
                <th>Ordered By</th>   <!--  주문자를 나타냅니다. -->
                <th>Book Copies</th>   <!--  책 복사본을 나타냅니다. -->
                <th>Total</th>   <!--  총합을 나타냅니다. -->
                <th>Payment Method</th>   <!--  결제 방법을 나타냅니다. -->
                <th>Status</th>   <!--  상태를 나타냅니다. -->
                <th>Order Date</th>   <!--  주문 날짜를 나타냅니다. -->
            </tr>
            <c:forEach items="${listMostRecentSales}" var="order" varStatus="status">   <!--  최근 판매 목록을 순회하는 JSTL forEach 반복문을 시작합니다. -->
            <tr>
                <td><a href="view_order?id=${order.orderId}">${order.orderId}</a></td>   <!--  주문 ID에 대한 링크를 생성하고 표시합니다. -->
                <td>${order.customer.fullname}</td>   <!--  주문자의 전체 이름을 표시합니다. -->
                <td>${order.bookCopies}</td>   <!--  책 복사본 수를 표시합니다. -->
                <td><fmt:formatNumber value="${order.total}" type="currency" /></td>   <!--  총액을 통화 형식으로 포맷팅하여 표시합니다. -->
                <td>${order.paymentMethod}</td>   <!--  결제 방법을 표시합니다. -->
                <td>${order.status}</td>   <!--  주문 상태를 표시합니다. -->
                <td>${order.orderDate}</td>   <!--  주문 날짜를 표시합니다. -->
            </tr>
            </c:forEach>   <!--  forEach 반복문을 종료합니다. -->
        </table>   <!--  테이블을 닫습니다.
    </div>

    <!-- 아래에 최근 리뷰, 통계 등을 표시하는 코드가 이어집니다. -->

	<div align="center">
		<hr width="60%" />
		<h2 class="pageheading">Recent Reviews:</h2>
		<table border="1">
			<tr>
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
			</tr>
			<c:forEach items="${listMostRecentReviews}" var="review">
			<tr>
				<td>${review.book.title}</td>
				<td>${review.rating}</td>
				<td><a href="edit_review?id=${review.reviewId}">${review.headline}</a></td>
				<td>${review.customer.fullname}</td>
				<td>${review.reviewTime}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<div align="center">
		<hr width="60%" />
		<h2 class="pageheading">Statistics:</h2>
		Total Users: ${totalUsers} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Books: ${totalBooks} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Customers: ${totalCustomers} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Reviews: ${totalReviews} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Orders: ${totalOrders}
		
		<hr width="60%" />
	</div>		
	<jsp:directive.include file="footer.jsp" />
</body>
</html>