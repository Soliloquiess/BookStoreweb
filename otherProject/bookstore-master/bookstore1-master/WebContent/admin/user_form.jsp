<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create New User</title>
	<link rel="stylesheet" href="../css/style.css" >
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${user != null}">
				Edit User
			</c:if>
			<c:if test="${user == null}">
				Create New User
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${user != null}">
			<form action="update_user" method="post" style="max-width: 400px; margin: 0 auto;">
			<input type="hidden" name="userId" value="${user.userId}">
		</c:if>
		<c:if test="${user == null}">
			<form action="create_user" method="post" style="max-width: 400px; margin: 0 auto;">
		</c:if>
		
		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td align="left">
					<input type="text" name="email" size="20" value="${user.email}" required minlength="5" maxlength="30" />
				</td>
			</tr>
			<tr>
				<td align="right">Full name:</td>
				<td align="left">
					<input type="text" name="fullname" size="20" value="${user.fullName}" required minlength="3" maxlength="30" />
				</td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left">
					<input type="password" name="password" size="20" value="${user.password}" required minlength="4" maxlength="32" />
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