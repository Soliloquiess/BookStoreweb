<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
	<title>Write a Review - Online Book Store</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	
	<form action="submit_review" method="post" style="max-width: 800px; margin: 0 auto">
			<div class="row">
			    <!-- 새로운 행(row)을 생성하는 div 요소 시작 -->
			    <div class="col"><h2>Your Reviews</h2></div>
			    <!-- 첫 번째 열(col)에 'Your Reviews'라는 제목을 포함한 h2 요소를 생성하는 div 요소 -->
			    <div class="col">&nbsp;</div>
			    <!-- 두 번째 열(col)에 공백을 표시하는 div 요소 -->
			    <div class="col"><h4>${loggedCustomer.fullname}</h4></div>
			    <!-- 세 번째 열(col)에 loggedCustomer의 fullname을 h4 요소로 출력하는 div 요소 -->
			</div>
			<!-- 행(row)을 닫는 div 요소 -->

			<div class="row">
				<div class="col"><hr/></div>
			</div>
			<div class="row">
				<div class="col-sm">
					<h4>${book.title}</h4>
					<img class="img-fluid" src="data:image/jpg;base64,${book.base64Image}" />
				</div>
				<div class="col-sm">
					<div id="rateYo"></div>
					<input type="hidden" id="rating" name="rating" />
					<input type="hidden" name="bookId" value="${book.bookId}" />
					<div>&nbsp;</div>
					<div>
						<input type="text" name="headline" class="form-control" 
							placeholder="Headline or summary for your review (required)" required minlength="10" maxlength="128" />
					</div>
					<div>&nbsp;</div>
					<div>
						<textarea name="comment" cols="70" rows="10" class="form-control" 
							placeholder="Write your review details..." required minlength="10" maxlength="500" ></textarea>
					</div>
				</div>
			</div>
			<div>&nbsp;</div>
			<div class="row">
				<div class="col text-center">
					<button type="submit" class="btn btn-primary">Submit</button>
					&nbsp;&nbsp;
					<button type="button" onclick="history.go(-1)" class="btn btn-secondary">Cancel</button>
				</div>
			</div>
	</form>
	
	<jsp:directive.include file="footer.jsp" />
</div>
<script type="text/javascript">
    // JavaScript 코드 시작

    $(document).ready(function() {
        // 문서가 준비되면 실행될 함수 시작

        $("#rateYo").rateYo({
            // rateYo 플러그인을 #rateYo 요소에 적용하는 함수 호출
            starWidth: "40px", // 별의 너비를 40px로 설정
            fullStar: true, // 별이 완전한 상태(full)로 표시되도록 설정
            onSet: function (rating, rateYoInstance) {
                // 사용자가 별을 설정할 때 실행될 콜백 함수 시작
                $("#rating").val(rating); // rating 값을 #rating 요소의 값으로 설정
            }
            // 사용자가 별을 설정할 때 실행될 콜백 함수 끝
        });
        // rateYo 플러그인 적용 함수 끝
    });
    // 문서가 준비되면 실행될 함수 끝
</script>

</body>
</html>