<div class="col-sm-3 text-center"> <!-- 1. Bootstrap 그리드 시스템을 사용하여 컬럼을 설정하고, 텍스트를 중앙 정렬합니다. -->
	<div> <!-- 2. 이미지와 제목 등을 포함하는 블록을 설정합니다. -->
		<a href="view_book?id=${book.bookId}"> <!-- 3. 책의 세부 정보가 표시되는 페이지로 이동하는 링크를 설정합니다. bookId를 동적으로 가져옵니다. -->
			<img style="width: 128px; height: 164px;" 
				src="data:image/jpg;base64,${book.base64Image}" /> <!-- 4. 책의 이미지를 Base64로 인코딩하여 표시합니다. -->
		</a>
	</div>
	<div> <!-- 5. 책 제목을 포함하는 블록을 설정합니다. -->
		<a href="view_book?id=${book.bookId}"> <!-- 6. 책의 세부 정보가 표시되는 페이지로 이동하는 링크를 설정합니다. bookId를 동적으로 가져옵니다. -->
			<b>${book.title}</b> <!-- 7. 책의 제목을 굵게 표시합니다. -->
		</a>
	</div>
	<div> <!-- 8. 책 평점을 포함하는 블록을 설정합니다. -->
		<jsp:directive.include file="book_rating.jsp" /> <!-- 9. 다른 JSP 파일을 포함하여 책의 평점을 표시합니다. -->
	</div>
	<div> <!-- 10. 책의 저자를 표시하는 블록을 설정합니다. -->
		<i>by ${book.author}</i> <!-- 11. 책의 저자를 이탤릭체로 표시합니다. -->
	</div>
	<div> <!-- 12. 책의 가격을 표시하는 블록을 설정합니다. -->
		<b>$${book.price}</b> <!-- 13. 책의 가격을 굵게 표시하며, $ 기호를 붙여 표시합니다. -->
	</div>
</div>
