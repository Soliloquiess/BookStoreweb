<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="Register as a Customer" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	
	<div class="row">&nbsp;</div>
	<!-- 고객으로 등록하는 폼을 나타내는 HTML 코드입니다. -->
	<div class="row">		
	    <!-- 등록 제목을 가운데 정렬하여 표시합니다. -->
	    <div class="col text-center"><h2>Register as a Customer</h2></div>
	</div>
	<!-- 공백을 추가합니다. -->
	<div class="row">&nbsp;</div>
	<!-- 고객 등록 폼을 위한 HTML 폼 엘리먼트입니다. -->
	<form action="register_customer" method="post" style="max-width: 800px; margin: 0 auto;">
	    <!-- 공통된 고객 양식을 포함합니다. -->
	    <jsp:directive.include file="../common/customer_form.jsp" />
	</form>


	<jsp:directive.include file="footer.jsp" />
</div>
</body>
<script type="text/javascript" src="js/customer-form.js"></script>
</html>