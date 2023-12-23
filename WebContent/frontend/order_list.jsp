<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="My Order History" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	<div class="row">&nbsp;</div>
	
	<div class="row">
		<div class="col text-center"><h2>My Order History</h2></div>
	</div>			
	
	<c:if test="${fn:length(listOrders) == 0}">
	    <!-- 'fn:length(listOrders) == 0' 조건을 확인합니다. -->
	    <!-- 'listOrders' 변수의 길이가 0인 경우 아래의 내용을 실행합니다. -->
	    
	    <div class="row">
	        <!-- 새로운 행(row)을 생성합니다. -->
	        
	        <div class="col text-center">
	            <!-- 한 개의 열(column)을 생성하고 텍스트를 가운데 정렬합니다. -->
	            
	            <h3>You have not placed any orders.</h3>
	            <!-- 주문을 한 번도 하지 않았음을 알리는 메시지를 출력합니다. -->
	        </div>
	    </div>
	</c:if>
		
	<c:if test="${fn:length(listOrders) > 0}">
	<div align="center">
		<table class="table table-bordered table-striped table-hover table-responsive-sm">
			<thead class="thead-dark">
				<tr>
					<th>Index</th>
					<th>Order ID</th>
					<th>Quantity</th>
					<th>Total Amount</th>
					<th>Order Date</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${listOrders}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>				
					<td>${order.bookCopies}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency" /></td>				
					<td>${order.orderDate}</td>
					<td>${order.status}</td>
					<td>
						<a href="show_order_detail?id=${order.orderId}">View Details</a> &nbsp;
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
	
	<jsp:directive.include file="footer.jsp" />
</div>	
</body>
</html>