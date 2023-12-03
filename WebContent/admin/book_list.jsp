<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Manage Books - Evergreen Bookstore Administration</title>
    <link rel="stylesheet" href="../css/style.css" > <!-- 스타일 시트 연결 -->
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script> <!-- jQuery 라이브러리 연결 -->
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script> <!-- 유효성 검사 라이브러리 연결 -->
</head>
<body>
    <jsp:directive.include file="header.jsp" /> <!-- header.jsp 파일 include -->
    
    <div align="center">
        <h2 class="pageheading">Books Management</h1> <!-- 책 관리 페이지 제목 -->
        <h3><a href="new_book">Create New Book</a></h3> <!-- 새 책 생성 링크 -->
    </div>
    
    <!-- 메시지가 있으면 표시 -->
    <c:if test="${message != null}">
    <div align="center">
        <h4 class="message">${message}</h4>
    </div>
    </c:if>
    
    <div align="center">
        <!-- 책 목록 테이블 -->
        <table border="1" cellpadding="5">
            <tr>
                <th>Index</th>
                <th>ID</th>
                <th>Image</th>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Price</th>
                <th>Last Updated</th>
                <th>Actions</th>
            </tr>
            <!-- 책 목록을 순회하며 테이블 로우 생성 -->
            <c:forEach var="book" items="${listBooks}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td> <!-- 인덱스 번호 표시 -->
                <td>${book.bookId}</td> <!-- 책 ID 표시 -->
                
                <td>
                    <img src="data:image/jpg;base64,${book.base64Image}" width="84" height="110" /> <!-- 책 이미지 표시 -->
                </td>
                
                <td>${book.title}</td> <!-- 책 제목 표시 -->
                <td>${book.author}</td> <!-- 책 저자 표시 -->
                <td>${book.category.name}</td> <!-- 책 카테고리 표시 -->
                <td>$${book.price}</td> <!-- 책 가격 표시 -->
                <td><fmt:formatDate pattern='MM/dd/yyyy' value='${book.lastUpdateTime}' /></td> <!-- 책 마지막 업데이트 날짜 포맷팅 -->
                <td>
                    <a href="edit_book?id=${book.bookId}">Edit</a> &nbsp; <!-- 책 편집 링크 -->
                    <a href="javascript:void(0);" class="deleteLink" id="${book.bookId}">Delete</a> <!-- 책 삭제 링크 -->
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
    
    <jsp:directive.include file="footer.jsp" /> <!-- footer.jsp 파일 include -->
    
    <script>
        // JavaScript 시작
        $(document).ready(function() {
            // 'deleteLink' 클래스를 가진 요소들을 찾아 반복적으로 처리
            $(".deleteLink").each(function() {
                $(this).on("click", function() {
                    // 삭제할 책의 ID를 가져옴
                    bookId = $(this).attr("id");
                    // 사용자에게 확인 메시지 표시 후 확인 시 해당 책 삭제 요청
                    if (confirm('Are you sure you want to delete the book with ID ' +  bookId + '?')) {
                        window.location = 'delete_book?id=' + bookId;
                    }                   
                });
            });
        });
    </script>
</body>
</html>



<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Manage Books - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Books Management</h1>
		<h3><a href="new_book">Create New Book</a></h3>
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
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${listBooks}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${book.bookId}</td>
				
				<td>
					<img src="data:image/jpg;base64,${book.base64Image}" width="84" height="110" />
				</td>
				
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.category.name}</td>
				<td>$${book.price}</td>
				<td><fmt:formatDate pattern='MM/dd/yyyy' value='${book.lastUpdateTime}' /></td>
				<td>
					<a href="edit_book?id=${book.bookId}">Edit</a> &nbsp;
					<a href="javascript:void(0);" class="deleteLink" id="${book.bookId}">Delete</a>
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
					bookId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the book with ID ' +  bookId + '?')) {
						window.location = 'delete_book?id=' + bookId;
					}					
				});
			});
		});
	</script>
</body>
</html> --%>