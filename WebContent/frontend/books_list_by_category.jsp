<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
    <!-- 페이지 헤더를 포함 -->
    <!-- 페이지 제목으로 카테고리 이름 전달 -->
    <jsp:include page="page_head.jsp">
        <jsp:param name="pageTitle" value="Books in ${category.name}" />
    </jsp:include>
<body>
    <div class="container">
        <!-- 페이지 헤더를 포함 -->
        <jsp:directive.include file="header.jsp" />

        <div class="row">
            <!-- 카테고리명 출력 -->
            <div class="col text-center"><h2>${category.name}</h2></div>
        </div>

        <!-- 줄 바꿈을 위한 여백 -->
        <div>&nbsp;</div>

        <div class="row">
            <!-- 책 목록을 순회하며 각 책을 표시 -->
            <%-- <c:forEach items="${listBooks}" var="book"> --%>
            <c:forEach items="${listBooks}" var="book"> <!-- listBooks에 있는 각 항목(book)에 대해 반복합니다. -->
<%--             이 구문은 JSTL(Core JSP Standard Tag Library)의 <c:forEach> 태그를 사용하여 listBooks라는 객체(혹은 컬렉션)를 반복하여 그 안에 있는 요소들을 하나씩 가져옵니다. var="book"은 현재 반복되는 요소를 book이라는 이름의 변수로 설정하겠다는 것을 의미합니다. 이 구문은 listBooks 안에 있는 각각의 요소를 순회하면서 내부의 코드를 실행합니다. --%>
                <!-- 각 책의 정보를 표시하기 위해 book_group.jsp 포함 -->
                <jsp:directive.include file="book_group.jsp" />	
                <!--  위 book_group.jsp로 리다이렉트(원래 있던 코드 book_group.jsp로 이동) -->	
            </c:forEach>
        </div>

        <!-- 페이지 푸터를 포함 -->
        <jsp:directive.include file="footer.jsp" />
    </div>	
</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="Books in ${category.name}" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	
	<div class="row">
		<div class="col text-center"><h2>${category.name}</h2></div>
	</div>
	
	<div>&nbsp;</div>
	
	<div class="row">
	<c:forEach items="${listBooks}" var="book">
		<jsp:directive.include file="book_group.jsp" />		
	</c:forEach>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</div>	
</body>
</html> --%>