<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="${book.title}" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" /> <!-- 1. 'header.jsp' 파일을 현재 JSP 페이지에 포함합니다. -->
	
	<div class="row">
	    <div class="col-12">
	        <h2>${book.title}</h2> by <span id="author">${book.author}</span> <!-- 2. 책의 제목과 저자를 표시합니다. -->
	    </div>
	</div>
	
	<div class="row">
	    <div class="col-sm-2">
	        <img class="img-fluid" src="data:image/jpg;base64,${book.base64Image}" /> <!-- 3. 책의 이미지를 Base64로 인코딩하여 표시합니다. -->
	    </div>
	    <div class="col-sm-8">
	        <div>
	            <jsp:directive.include file="book_rating.jsp" />&nbsp;&nbsp; <!-- 4. 'book_rating.jsp'를 포함하여 책의 평점을 표시합니다. -->
	            <!-- &nbsp;&nbsp; 로 공백 추가 -->
	            <a href="#reviews">${fn:length(book.reviews)} Reviews</a> <!-- 5. 리뷰 링크를 통해 책의 리뷰 수를 표시합니다. -->
	            <!-- fn:length는 일반적으로 프로그래밍이나 쿼리 언어에서 사용되는 함수 중 하나입니다. 이 함수는 컬렉션(배열, 리스트, 문자열 등)의 요소 수 또는 길이를 반환합니다.

예를 들어, 프로그래밍에서 배열의 요소 수를 계산하거나, 문자열의 길이를 파악하는 데 사용됩니다. 이 함수를 사용하면 컬렉션의 크기를 쉽게 알 수 있어서 데이터 처리나 제어 구조에서 유용하게 활용됩니다. -->
	        </div>
	        <div>${book.description}</div> <!-- 6. 책의 설명을 표시합니다. -->
	    </div>
	    <div class="col-sm-2 text-center">
	        <div><h2>$${book.price}</h2></div> <!-- 7. 책의 가격을 표시합니다. -->
	        <div><button id="buttonAddToCart" class="btn btn-primary">Add to Cart</button></div> <!-- 8. 장바구니에 추가하는 버튼을 표시합니다. -->		
	    </div>				
	</div>
	
	<div class="row">&nbsp;</div> <!-- 9. 줄바꿈을 위한 빈 줄을 추가합니다. -->
	
<div class="row">&nbsp;</div> <!-- HTML에서 빈 줄(공백)을 만들기 위한 방법 중 하나입니다. 웹 페이지 디자인을 위해 여백을 추가하거나 요소들 사이의 간격을 조절할 때 사용될 수 있습니다. -->

<!-- 일반적으로 HTML에서 공백은 무시되기 때문에, 공백을 만들기 위해 명시적으로 <div>나 다른 HTML 요소에 공백 문자 &nbsp;를 포함하는 방식을 사용합니다.
이러한 방법은 페이지 레이아웃을 조정하거나 시각적인 디자인 요소를 추가하는 데 유용할 수 있습니다. 페이지의 구조를 보다 명확하게 표현하거나 요소들 사이의 간격을 조절하기 위해 사용될
기술적으로, 이 방식은 권장되는 방법은 아니지만, 특정 레이아웃이나 디자인 요소에 필요한 경우에는 사용될 수 있습니다. 보다 효율적인 디자인과 레이아웃을 위해서는 CSS를 이용하여 마진(margin)이나 패딩(padding)을 조정하는 것이 일반적으로 권장되는 방법입니다 -->

	<div class="row">
	    <div class="col-12 text-center">
	        <h3><a id="reviews">Customer Reviews</a></h3> <!-- 10. 고객 리뷰 섹션을 표시합니다. -->
	        &nbsp;
	        <button id="buttonWriteReview" class="btn btn-secondary">Write a Customer Review</button> <!-- 11. 고객 리뷰 작성 버튼을 표시합니다. -->
	    </div>	
	</div>

	
	<div class="row">&nbsp;</div> <!-- 1. 빈 줄을 생성하여 섹션 사이에 여백을 만듭니다. -->

	<c:forEach items="${book.reviews}" var="review"> <!-- 2. 'book.reviews'에 있는 각 리뷰에 대해 반복합니다. -->
	    <div class="row">
	        <div class="col-sm-1"></div> <!-- 3. Bootstrap 그리드 시스템을 이용하여 여백을 만듭니다. -->
	        <div class="col-sm-8">
	            <!-- 4. 각 리뷰에 대한 별점을 이미지로 표시합니다. -->
	            <c:forTokens items="${review.stars}" delims="," var="star">
	                <c:if test="${star eq 'on'}">
	                    <img src="images/rating_on.png" /> <!-- 별점이 'on'일 때 꽉 찬 별 이미지를 표시합니다. -->
	                </c:if>
	                <c:if test="${star eq 'off'}">
	                    <img src="images/rating_off.png" /> <!-- 별점이 'off'일 때 빈 별 이미지를 표시합니다. -->
	                </c:if>
	            </c:forTokens>
	            - <b>${review.headline}</b> <!-- 5. 리뷰의 제목을 굵게 표시합니다. -->
	        </div>
	        <div class="col-sm-1"></div> <!-- 6. 여백을 만듭니다. -->
	    </div>
	    <div class="row">
	        <div class="col-sm-1"></div> <!-- 7. 여백을 만듭니다. -->
	        <div class="col-sm-8">
	            by ${review.customer.fullname} on ${review.reviewTime} <!-- 8. 리뷰를 작성한 고객과 작성 시간을 표시합니다. -->
	        </div>
	        <div class="col-sm-1"></div> <!-- 9. 여백을 만듭니다. -->
	    </div>
	    <div class="row">
	        <div class="col-sm-1"></div> <!-- 10. 여백을 만듭니다. -->
	        <div class="col-sm-8">
	            <i>${review.comment}</i> <!-- 11. 리뷰의 코멘트를 이탤릭체로 표시합니다. -->
	        </div>
	        <div class="col-sm-1"></div> <!-- 12. 여백을 만듭니다. -->
	    </div>
	    <div class="row">&nbsp;</div> <!-- 13. 빈 줄을 추가하여 리뷰 사이의 간격을 만듭니다. -->
	</c:forEach>


	<jsp:directive.include file="footer.jsp" />
</div>
	<script type="text/javascript">
	    $(document).ready(function() {
	        // 1. 'Write a Customer Review' 버튼을 클릭했을 때의 이벤트 핸들러 설정
	        $("#buttonWriteReview").click(function() {
	            // 2. 해당 도서의 리뷰를 작성하는 페이지로 이동합니다. book_id를 전달합니다.
	            window.location = 'write_review?book_id=' + ${book.bookId};
	            //매개변수 이름은 book_id
	        });
	        
	        // 3. 'Add to Cart' 버튼을 클릭했을 때의 이벤트 핸들러 설정
	        $("#buttonAddToCart").click(function() {
	            // 4. 해당 도서를 장바구니에 추가하는 기능을 수행합니다. book_id를 전달합니다.
	            window.location = 'add_to_cart?book_id=' + ${book.bookId};
	            //매개변수 이름은 book_id
	        });                
	    });
	</script>
</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="${book.title}" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />

	<div class="row">
		<div class="col-12">
			<h2>${book.title}</h2> by <span id="author">${book.author}</span>			
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-2">
			<img class="img-fluid" src="data:image/jpg;base64,${book.base64Image}" />
		</div>
		<div class="col-sm-8">
			<div>
				<jsp:directive.include file="book_rating.jsp" />&nbsp;&nbsp;
				<a href="#reviews">${fn:length(book.reviews)} Reviews</a>			
			</div>
			<div>${book.description}</div>
		</div>
		<div class="col-sm-2 text-center">
			<div><h2>$${book.price}</h2></div>
			<div><button id="buttonAddToCart" class="btn btn-primary">Add to Cart</button></div>		
		</div>				
	</div>
	
	<div class="row">&nbsp;</div>
	
	<div class="row">
		<div class="col-12 text-center">
			<h3><a id="reviews">Customer Reviews</a></h3>
			&nbsp;
			<button id="buttonWriteReview" class="btn btn-secondary">Write a Customer Review</button>
		</div>	
	</div>
	
	<div class="row">&nbsp;</div>

	<c:forEach items="${book.reviews}" var="review">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-8">
				<c:forTokens items="${review.stars}" delims="," var="star">
					<c:if test="${star eq 'on'}">
						<img src="images/rating_on.png" />
					</c:if>
					<c:if test="${star eq 'off'}">
						<img src="images/rating_off.png" />
					</c:if>
				</c:forTokens>
				- <b>${review.headline}</b>
			</div>
			<div class="col-sm-1"></div>
		</div>
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-8">
				by ${review.customer.fullname} on ${review.reviewTime}
			</div>
			<div class="col-sm-1"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-8">
				<i>${review.comment}</i>
			</div>
			<div class="col-sm-1"></div>
		</div>
		
		<div class="row">&nbsp;</div>
	</c:forEach>

	<jsp:directive.include file="footer.jsp" />
</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#buttonWriteReview").click(function() {
				window.location = 'write_review?book_id=' + ${book.bookId};
			});
			
			$("#buttonAddToCart").click(function() {
				window.location = 'add_to_cart?book_id=' + ${book.bookId};
			});				
		});
	</script>
</body>
</html> --%>
