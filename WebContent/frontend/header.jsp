<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<a href="${pageContext.request.contextPath}/">
		<img src="images/BookstoreLogo.png" class="img-fluid" />
	</a>
</div>

<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#topNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<div class="collapse navbar-collapse" id="topNavbar">
		<form action="search" method="get" class="form-inline">
			<input type="search" name="keyword" class="form-control mr-sm-2 mt-1" placeholder="keyword"/>
			<input type="submit" value="Search" class="btn btn-outline-success mt-1" />	
		</form>
		<ul class="navbar-nav">
			<c:if test="${loggedCustomer == null}">
				<li class="nav-item">
					<a href="login" class="nav-link">Sign In</a>
				</li>
				<li class="nav-item">
					<a href="register" class="nav-link">Register</a>
				</li>			
			</c:if>
			
			<c:if test="${loggedCustomer != null}">
				<li class="nav-item">
					<a href="view_profile" class="nav-link">Welcome, ${loggedCustomer.fullname}</a>
				</li>
				
				<li class="nav-item">
					<a href="view_orders" class="nav-link">My Orders</a>
				</li>
				
				<li class="nav-item">
					<a href="logout" class="nav-link">Logout</a>
				</li>
			</c:if>
			
			<li class="nav-item">
				<a href="view_cart" class="nav-link">Cart</a>
			</li>			
		</ul>
	</div>
</nav>

<!-- 비어 있는 한 줄을 만들기 위한 div 요소 -->
<div>&nbsp;</div>

<!-- 요소를 가운데 정렬하기 위한 Bootstrap 그리드 시스템 div -->
<div class="row justify-content-center">

    <!-- 카테고리 목록 반복 -->
    <c:forEach var="category" items="${listCategory}" varStatus="status">
        
        <!-- 각 카테고리에 대한 링크 -->
        <a href="view_category?id=${category.categoryId}">
            
            <!-- 카테고리 이름을 크고 굵게 표시 -->
            <font size="+1"><b><c:out value="${category.name}" /></b></font>
        </a>
        
        <!-- 마지막 카테고리가 아닌 경우 구분자 출력 -->
        <c:if test="${not status.last}">
            &nbsp; | &nbsp;
        </c:if>
    </c:forEach>
</div>

