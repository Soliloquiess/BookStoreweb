<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>사용자 관리 - 에버그린 서점 관리자 페이지</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
    <jsp:directive.include file="header.jsp" /> <!-- 헤더를 포함합니다 -->

    <div align="center">
        <h2 class="pageheading">사용자 관리</h2> <!-- 사용자 관리 제목 -->
        <h3><a href="user_form.jsp">새 사용자 생성</a></h3> <!-- 새 사용자 생성 링크 -->
    </div>

    <c:if test="${message != null}"> <!-- 메시지가 비어있지 않은지 확인 -->
        <div align="center">
            <h4 class="message">${message}</h4> <!-- 메시지 표시 -->
        </div>
    </c:if>

    <div align="center">
        <table border="1" cellpadding="5"> <!-- 사용자 정보를 표시할 테이블 -->
            <tr>
                <th>인덱스</th>
                <th>ID</th>
                <th>이메일</th>
                <th>전체 이름</th>
                <th>동작</th>
            </tr>
            <c:forEach var="user" items="${listUsers}" varStatus="status"> <!-- 사용자 목록을 순회 -->
                <tr>
                    <td>${status.index + 1}</td> <!-- 인덱스 번호 -->
                    <td>${user.userId}</td> <!-- 사용자 ID -->
                    <td>${user.email}</td> <!-- 사용자 이메일 -->
                    <td>${user.fullName}</td> <!-- 사용자 전체 이름 -->
                    <td>
                        <a href="edit_user?id=${user.userId}">수정</a> &nbsp; <!-- 사용자 수정 링크 -->
                        <a href="javascript:void(0);" class="deleteLink" id="${user.userId}">삭제</a> <!-- 사용자 삭제 링크 -->
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <jsp:directive.include file="footer.jsp" /> <!-- 푸터를 포함합니다 -->

    <script>
        $(document).ready(function() {
            $(".deleteLink").each(function() {
                $(this).on("click", function() {
                    userId = $(this).attr("id"); // 삭제할 사용자 ID 가져오기
                   /*  userId = 1; */
                    if (confirm('ID가 ' + userId + '인 사용자를 삭제하시겠습니까?')) {
                        window.location = 'delete_user?id=' + userId; // 확인 시, delete_user 서블릿으로 이동
                    }                   
                });
            });
        });
        
/*         function confirmDelete(userId){
        	if(confirm('are you sure you want to delete the user with id '+ userId + '?')){
        		window.location = 'delete_user?id='+userId;
        	}
        } */
    </script>
</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Manage Users - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Users Management</h1>
		<h3><a href="user_form.jsp">Create New User</a></h3>
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
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listUsers}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${user.userId}</td>
				<td>${user.email}</td>
				<td>${user.fullName}</td>
				<td>
					<a href="edit_user?id=${user.userId}">Edit</a> &nbsp;
					<a href="javascript:void(0);" class="deleteLink" id="${user.userId}">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	
	<jsp:directive.include file="footer.jsp" />
	
	<script>
		$(document).ready(function() {
			$(".deleteLink").each(function() {
				$(this).on("click", function() {
					userId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the user with ID ' +  userId + '?')) {
						window.location = 'delete_user?id=' + userId;
					}					
				});
			});
		});
	</script>
</body>
</html> --%>