<%@ page language="java" contentType="text/html; charset=UTF-8" %> <!-- JSP 페이지 언어 및 콘텐츠 유형을 설정하고 UTF-8 문자 인코딩 지정 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> <!-- 콘텐츠 문자 집합을 UTF-8로 설정합니다. -->
    <title>Evergreen Bookstore Administration</title> <!-- 페이지 제목을 설정합니다. -->
	<link rel="stylesheet" href="../css/style.css" >
    
</head>
<body>

    <!-- Include the header -->
    <jsp:directive.include file="header.jsp" /> <!-- header.jsp 파일을 include하여 삽입합니다. -->
    
    <div align="center">
        <h3>${message}</h3> <!-- 메시지를 표시하는 부분입니다. -->
    </div>
        
    <!-- Include the footer -->
    <jsp:directive.include file="footer.jsp" /> <!-- footer.jsp 파일을 include하여 삽입합니다. -->

</body>
</html>


<%-- 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Bookstore Administration</title>
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h3>${message}</h3>
	</div>
		
	<jsp:directive.include file="footer.jsp" />

</body>
</html> --%>