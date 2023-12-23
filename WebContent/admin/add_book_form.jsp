<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book to Order</title>
</head>
<body>
	<div align="center">
		<h2>Add book to Order ID: ${order.orderId}</h2>
		<form action="add_book_to_order" method="post">
		<table>
			<tr>
				<td>Select a book: </td>
				<td>
					<select name="bookId">
						<c:forEach items="${listBook}" var="book" varStatus="status">
							<option value="${book.bookId}">${book.title} - ${book.author}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>Number of Copies</td>
				<td>
					<select name="quantity">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
			    <!-- 테이블의 셀들을 병합하여 하나의 셀로 만듭니다. -->
			    <td colspan="2" align="center">
			        <!-- 셀 안의 내용을 가운데 정렬합니다. -->
			        
			        <!-- "Add" 라벨을 가진 서브밋(input type="submit") 버튼을 생성합니다. -->
			        <input type="submit" value="Add" />
			        
			        <!-- 공백을 나타내는 HTML 엔터티(&nbsp;)를 사용하여 간격을 추가합니다. -->
			        <!-- "Cancel" 라벨을 가진 버튼을 생성하고, 클릭 시 자바스크립트로 현재 창을 닫도록 설정합니다. -->
			        <input type="button" value="Cancel" onclick="javascript: self.close();" />
			    </td>
			</tr>

		</table>
		</form>
		
	</div>
</body>
</html>