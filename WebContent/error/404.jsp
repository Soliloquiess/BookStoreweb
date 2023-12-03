<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP 지시자: 페이지의 언어와 인코딩 설정 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- HTML 4.01 Transitional을 위한 문서 유형 선언 -->

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- 문서의 문자 인코딩을 정의하는 메타 태그 -->
    <title>Page Not Found Error</title>
</head>
<body>
    <div align="center">
        <div>
            <!-- JSP 표현식을 사용한 이미지 (BookstoreLogo.png)의 자리 표시자 -->
            <img src="${pageContext.request.contextPath}/images/BookstoreLogo.png" />
        </div>
        <div>
            <h2>죄송합니다, 요청하신 페이지를 찾을 수 없습니다.</h2>
        </div>
        <div>
            <!-- JavaScript를 사용하여 이전 페이지로 이동하는 링크 -->
            <a href="javascript:history.go(-1);">뒤로 가기</a>
        </div>
    </div>
</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Not Found Error</title>
</head>
<body>
<div align="center">
	<div>
		<img src="${pageContext.request.contextPath}/images/BookstoreLogo.png" />
	</div>
	<div>
		<h2>Sorry, the requested page could not be found.</h2>
	</div>	
	<div>
		<a href="javascript:history.go(-1);">Go Back</a>
	</div>
</div>
</body>
</html> --%>