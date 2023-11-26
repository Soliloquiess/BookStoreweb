<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Manage Categories - Evergreen Bookstore Administration</title>
    <link rel="stylesheet" href="../css/style.css" > <!-- 외부 스타일시트 파일 연결 -->
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script> <!-- jQuery 라이브러리 연결 -->
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script> <!-- jQuery 유효성 검사 라이브러리 연결 -->  
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
		<h3><a href="category_form.jsp">Create New Category</a></h3>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="cat" items="${listCategory}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${cat.categoryId}</td>
				<td>${cat.name}</td>
				<td>
					<a href="edit_category?id=${cat.categoryId}">Edit</a> &nbsp;
					<a href="javascript:void(0);" class="deleteLink" id="${cat.categoryId}">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	
<%-- 	<jsp:directive.include file="footer.jsp" /> --%>
	
	<%@ include file="footer.jsp" %> <!-- footer.jsp 파일 include -->
    
    <!-- JavaScript로 삭제 링크 동작 -->
    <script>
        $(document).ready(function() {
            $(".deleteLink").each(function() {
                $(this).on("click", function() {
                    categoryId = $(this).attr("id"); // 선택된 카테고리 ID 가져오기
                    // 삭제 확인 메시지 표시 후 확인 시 해당 ID의 카테고리 삭제 요청
                    if (confirm('Are you sure you want to delete the category with ID ' +  categoryId + '?')) {
                        window.location = 'delete_category?id=' + categoryId; // 카테고리 삭제 URL로 이동
                    }                   
                });
            });
        });    
    </script>
</body>
</html>