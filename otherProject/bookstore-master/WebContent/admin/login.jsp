<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Login</title>
	<link rel="stylesheet" href="../css/style.css" >
</head>
<body>
	<div align="center">
		<h1>Book Store Administration</h1>
		<h2>Admin Login</h2>
		
		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>
		
		<form id="loginForm" action="login" method="post">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="email" name="email" size="20" required minlength="5" maxlength="30"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" size="20" required minlength="4" maxlength="32"></td>
				</tr>		
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>		
			</table>
		</form>
	</div>	
</body>
</html>