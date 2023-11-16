<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="../css/style.css" >
<title>
	<c:if test="${category != null}">
		Edit Category
	</c:if>
	<c:if test="${category == null}">
		Create New Category
	</c:if>
</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${category != null}">
				Edit Category
			</c:if>
			<c:if test="${category == null}">
				Create New Category
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${category != null}">
			<form action="update_category" method="post" style="max-width: 300px; margin: 0 auto;">
			<input type="hidden" name="categoryId" value="${category.categoryId}">
		</c:if>
		<c:if test="${category == null}">
			<form action="create_category" method="post" style="max-width: 300px; margin: 0 auto;">
		</c:if>
		
		<table class="form">
			<tr>
				<td align="right">Name:</td>
				<td align="left">
					<input type="text" name="name" size="20" value="${category.name}" required minlength="3" maxl />
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button type="button" onclick="history.go(-1);">Cancel</button>
				</td>
			</tr>				
		</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>