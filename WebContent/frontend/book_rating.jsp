<c:forTokens items="${book.ratingStars}" delims="," var="star">
    <!-- book.ratingStars를 쉼표(,)를 구분자로 사용하여 토큰으로 분리 -->
    <c:if test="${star eq 'on'}">
        <!-- 'on'인 경우 해당 이미지를 생성 -->
        <img src="images/rating_on.png" />
    </c:if>
    <c:if test="${star eq 'off'}">
        <!-- 'off'인 경우 해당 이미지를 생성 -->
        <img src="images/rating_off.png" />
    </c:if>
    <c:if test="${star eq 'half'}">
        <!-- 'half'인 경우 해당 이미지를 생성 -->
        <img src="images/rating_half.png" />
    </c:if>
</c:forTokens>


<%-- 위의 코드는 JSTL(Core Tag Library)의 <c:forTokens>를 사용하여 book.ratingStars 문자열을 구분자(delimiter)인 쉼표(,)를 기준으로 토큰으로 분리합니다. 분리된 각 토큰(star)은 on, off, half 중 하나일 것으로 예상됩니다.

<c:if> 태그는 각 토큰(star)의 값에 따라 다른 이미지를 출력하는 조건문을 작성합니다. 각각의 조건에 맞는 경우에만 해당 이미지를 생성하여 출력합니다.
${star eq 'on'}: star가 'on'인 경우, images/rating_on.png 이미지를 생성합니다.
${star eq 'off'}: star가 'off'인 경우, images/rating_off.png 이미지를 생성합니다.
${star eq 'half'}: star가 'half'인 경우, images/rating_half.png 이미지를 생성합니다.
이러한 코드 구성은 book.ratingStars에 저장된 각 등급에 따라 적절한 이미지를 표시하려는 것으로 보여집니다. 예를 들어, book.ratingStars가 'on,on,off,half,off'와 같은 문자열이라면, 다섯 개의 이미지가 생성되고 각각의 등급에 해당하는 이미지가 화면에 출력될 것입니다.
 --%>
 
 
 
<%-- <c:forTokens items="${book.ratingStars}" delims="," var="star">
	<c:if test="${star eq 'on'}">
		<img src="images/rating_on.png" />
	</c:if>
	<c:if test="${star eq 'off'}">
		<img src="images/rating_off.png" />
	</c:if>
	<c:if test="${star eq 'half'}">
		<img src="images/rating_half.png" />
	</c:if>
</c:forTokens>


 --%>