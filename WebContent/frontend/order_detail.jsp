<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="My Order Details" />
	</jsp:include>	
</head>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	
<c:if test="${order == null}">
    <!-- 'order'가 null인지 확인합니다. -->
    <!-- 'order'가 null인 경우 아래의 내용을 실행합니다. -->
    
    <div class="row">
        <!-- 새로운 행(row)을 생성합니다. -->
        
        <div class="col text-center">
            <!-- 한 개의 열(column)을 생성하고 텍스트를 가운데 정렬합니다. -->
            
            <h2>Sorry, you are not authorized to view this order</h2>
            <!-- 주문을 볼 권한이 없음을 알리는 메시지를 출력합니다. -->
        </div>
    </div>
</c:if>

<c:if test="${order != null}">
    <!-- 'order'가 null이 아닌지 확인합니다. -->
    <!-- 'order'가 null이 아닌 경우 아래의 내용을 실행합니다. -->
    
    <div class="row">
        <!-- 새로운 행(row)을 생성합니다. -->
        
        <div class="col text-center">
            <!-- 한 개의 열(column)을 생성하고 텍스트를 가운데 정렬합니다. -->
            
            <h2>Your Order ID: ${order.orderId}</h2>
            <!-- 주문 ID를 출력합니다. -->
        </div>
    </div>
    
    <jsp:directive.include file="../common/common_order_detail.jsp" />
    <!-- 공통 주문 세부 정보를 포함시키는 JSP 파일을 include합니다. -->
</c:if>

	<jsp:directive.include file="footer.jsp" />
</div>	
</body>
</html>