<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
	    <jsp:param name="pageTitle" value="Your Shopping Cart" />
	</jsp:include>
<%-- 	<jsp:include page="page_head.jsp"> <!-- page_head.jsp 파일을 포함시킵니다. -->
	    <jsp:param name="pageTitle" value="Your Shopping Cart" /> <!-- pageTitle이라는 이름의 파라미터를 설정하고 값을 "Your Shopping Cart"로 지정합니다. -->
	</jsp:include> --%>
	
<%-- 	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="Your Shopping Cart" />
	</jsp:include> 이 부분 주석 들어가면 에러남 <!-- ---> 이런식의 주석 들어갈 떄 에러 발생 --%>
<body>

<div class="container">
	<jsp:directive.include file="header.jsp" /> <!-- header.jsp 파일을 포함시킵니다. -->
	
	<div>&nbsp;</div> <!-- 공백을 나타내는 div 요소 -->
	
	<div class="row"> <!-- 클래스가 "row"인 div 요소 -->
	    <div class="col text-center"><h2>Your Cart Details</h2></div> <!-- 클래스가 "col text-center"인 div 요소 내에서 "Your Cart Details" 제목을 가운데 정렬하여 출력 -->
	</div>

	<c:if test="${message != null}"> <!-- message가 null이 아닌 경우에 내용을 표시 -->
	    <div class="row"> <!-- 클래스가 "row"인 div 요소 -->
	        <div class="col text-center"><h4>${message}</h4></div> <!-- 클래스가 "col text-center"인 div 요소 내에서 메시지를 가운데 정렬하여 출력 -->
	    </div>
	</c:if>
	
	<c:set var="cart" value="${sessionScope['cart']}" /> <!-- sessionScope에서 'cart' 값을 가져와 'cart' 변수에 할당 -->
<%-- 	이 코드는 JSP 내에서 변수를 설정하는데 사용됩니다.
	<c:set>: JSTL(Core) 태그 라이브러리 중 하나인 <c:set> 태그는 변수를 설정할 때 사용됩니다.
	var="cart": 설정할 변수의 이름을 'cart'로 지정합니다. 이 변수는 이후에 JSP 페이지 내에서 사용할 수 있습니다.
	value="${sessionScope['cart']}": 'sessionScope'에서 'cart'라는 이름으로 저장된 값을 가져와 'cart' 변수에 할당합니다. 'sessionScope'는 JSP 세션에서 데이터를 가져올 수 있는 영역입니다. 'cart' 변수에는 이전에 세션에 저장된 장바구니 정보가 할당됩니다.
	이렇게 설정된 'cart' 변수는 이후에 JSP 페이지에서 해당 세션의 장바구니 정보를 사용할 수 있게 해줍니다. 이를 통해 장바구니 정보를 표시하거나 수정할 수 있게 됩니다. --%>
	
	
	<c:if test="${cart.totalItems == 0}"> <!-- cart의 총 아이템 수가 0인 경우에 내용을 표시 -->
	    <div class="row"> <!-- 클래스가 "row"인 div 요소 -->
	        <div class="col text-center"><h4>There's no items in your cart</h4></div> <!-- 클래스가 "col text-center"인 div 요소 내에서 장바구니에 아이템이 없다는 메시지를 가운데 정렬하여 출력 -->
	    </div>
	</c:if>

	<c:if test="${cart.totalItems > 0}"> <!-- 장바구니에 아이템이 있는 경우에만 아래의 내용을 표시 -->
	    <form action="update_cart" method="post" style="max-width: 600px; margin: 0 auto"> <!-- 장바구니 아이템 업데이트를 위한 폼 -->
	        <c:forEach items="${cart.items}" var="item" varStatus="status"> <!-- 장바구니 아이템 목록 순회 -->
	            <div class="row border rounded p-1"> <!-- 각 아이템을 보여주는 테두리가 있는 행 -->
	                <div class="col-sm"> <!-- Bootstrap 그리드 시스템을 사용하여 컬럼 생성 -->
	                    <div>${status.index + 1}</div> <!-- 아이템 번호 표시 -->
	                </div>
	                
<%--  <c:if> 태그: 조건을 검사하여 해당 조건이 참인 경우에만 내부의 코드를 실행합니다. ${cart.totalItems > 0}는 장바구니에 아이템이 있는지를 검사하는 조건입니다.
<form> 태그: HTML 폼을 생성하며, 장바구니 아이템을 업데이트하기 위한 정보를 전송하는 역할을 합니다. action 속성은 데이터를 전송할 URL을 지정하고, method="post"는 데이터를 전송하는 HTTP 메서드를 POST로 지정합니다. style 속성은 폼의 스타일을 설정합니다.
<c:forEach> 태그: JSTL의 <c:forEach>를 사용하여 장바구니의 아이템을 반복하며 목록을 표시합니다. items="${cart.items}"는 장바구니 아이템 리스트를 가리킵니다. var="item"은 각 아이템을 참조할 변수명을 설정하고, varStatus="status"는 순회 중인 아이템의 상태를 제어하기 위한 변수명을 설정합니다.
<div> 태그: HTML에서 블록 요소를 나타내며, 각 아이템의 특성을 구분하기 위해 사용됩니다. class="row border rounded p-1"은 Bootstrap 클래스를 활용하여 각 아이템을 보여주는 테두리가 있는 행을 생성합니다.
${status.index + 1}: 순회 중인 아이템의 인덱스 번호를 1부터 표시합니다.
이 코드는 장바구니에 아이템이 있을 때 해당 아이템들을 순회하며 각각의 정보를 표시하고자 하는 목적으로 사용됩니다. --%>

					<div class="col-5"> <!-- Bootstrap 그리드 시스템을 사용하여 컬럼 생성 -->
                    	<img class="img-fluid" src="data:image/jpg;base64,${item.key.base64Image}" /> <!-- 이미지 표시 -->
	                	<!-- books_list_by_category.jsp에서 복붙해옴 -->
	                	
<%-- 	                	<img>: HTML에서 이미지를 나타내는 태그입니다.
class="img-fluid": Bootstrap의 클래스인 "img-fluid"는 이미지를 부모 요소에 맞게 반응형으로 조정해주는 클래스입니다.
src="data:image/jpg;base64,${item.key.base64Image}": 이미지의 소스 경로를 지정합니다. 여기서 "data:image/jpg;base64,"는 Base64로 인코딩된 이미지 데이터를 나타냅니다. ${item.key.base64Image}는 JSP 페이지에서 이미 설정된 'item' 변수의 'key' 속성에 저장된 이미지의 Base64 인코딩된 데이터를 가져옵니다.
이미지가 Base64로 인코딩되어 있기 때문에 일반적인 이미지 파일 경로가 아니라 직접 이미지 데이터를 포함하고 있습니다. 이를 통해 이미지를 URL이 아니라 데이터로 직접 포함시킬 수 있습니다. 이 방식을 사용하면 서버에서 이미지 파일을 로드하는 것이 아니라 HTML 문서 자체에 이미지 데이터를 포함하여 렌더링할 수 있습니다. --%>
	                	
	                </div>
	
	                <div class="col-6"> <!-- Bootstrap 그리드 시스템을 사용하여 컬럼 생성 -->
	                    <div><h5>${item.key.title}</h5></div> <!-- 아이템 제목 표시 -->
	 			<%-- 		<div><span id = "book-title">${item.key.title}</span></div> --%>
	 					<!-- 원래는 위처럼 구성했었음 -->
	                    <div> <!-- 수량 입력란 및 숨은 bookId 입력 -->
	                        <input type="hidden" name="bookId" value="${item.key.bookId}" /> <!-- 아이템의 bookId를 전송하기 위한 hidden input -->
	                        <input type="number" name="quantity${status.index + 1}" value="${item.value}" 
	                            class="form-control" style="max-width: 50px" size="5" required step="1" min="1" /> <!-- 아이템 수량을 변경할 수 있는 입력 필드 -->
	                    </div>

<%-- <div>: HTML에서 블록 레벨 요소를 나타내며, 아이템의 수량을 변경할 수 있는 입력 필드와 bookId를 전송하기 위한 hidden input을 포함합니다.
<input type="hidden" name="bookId" value="${item.key.bookId}" />: 아이템의 bookId를 서버로 전송하기 위한 hidden input 필드입니다. name="bookId"로 정의되어 있으며, value="${item.key.bookId}"에서 ${item.key.bookId}는 각 아이템의 bookId 값을 나타냅니다. 이 값은 숨겨진(hidden) 상태로 전송됩니다.
<input type="number" name="quantity${status.index + 1}" value="${item.value}" class="form-control" style="max-width: 50px" size="5" required step="1" min="1" />: 아이템 수량을 변경할 수 있는 입력 필드입니다. type="number"로 정의되어 있어 숫자를 입력할 수 있습니다. name="quantity${status.index + 1}"에서 ${status.index + 1}는 각 아이템의 수량을 식별하기 위한 이름을 나타냅니다. ${item.value}는 현재 아이템의 수량을 나타내며, 해당 값이 입력 필드의 기본값으로 설정됩니다. class="form-control"는 Bootstrap과 같은 CSS 프레임워크에서 스타일을 적용하기 위한 클래스입니다. style="max-width: 50px"는 입력 필드의 최대 너비를 설정합니다. size="5"는 입력 필드의 길이를 나타냅니다. required는 필수 입력 항목임을 나타내며, step="1"은 입력할 수 있는 값의 단계(증가/감소 값)를 나타냅니다. min="1"은 입력 가능한 최소값을 나타냅니다.
이 코드는 장바구니의 각 아이템에 대해 수량을 변경할 수 있는 입력 필드를 생성하고, 이를 통해 수량 정보를 서버로 업데이트할 수 있는 기능을 제공합니다. --%>	

	
	                    <div> <!-- 각 아이템의 가격을 표시 -->
	                        X <fmt:formatNumber value="${item.key.price}" type="currency" /> <!-- 아이템 단가 표시 -->
	                    </div>
	
	                    <div> <!-- 각 아이템의 총 가격을 표시 -->
	                        = <span class="h5"><fmt:formatNumber value="${item.value * item.key.price}" type="currency" /></span> <!-- 아이템 총 가격 표시 -->
	                    </div>
	
	                    <div> <!-- 아이템 삭제 링크 -->
	                        <a href="remove_from_cart?book_id=${item.key.bookId}">Remove</a> <!-- 아이템을 장바구니에서 삭제하는 링크 -->
	                    </div>
	                </div>
	            </div>
	            <div class="row">&nbsp;</div> <!-- 아이템 간 간격을 위한 공백 -->
	        </c:forEach>

			<div class="row"> <!-- 클래스가 "row"인 div 요소 -->
			    <div class="col-12 text-center"> <!-- 클래스가 "col-12 text-center"인 div 요소 -->
			        <b>${cart.totalQuantity} book(s)</b> <!-- 장바구니에 담긴 전체 책 수량을 강조하여 표시 -->
			        &nbsp;&nbsp; <!-- 공백 -->
			        <span class="h4">Total: <fmt:formatNumber value="${cart.totalAmount}" type="currency" /></span> <!-- 장바구니에 담긴 책들의 총 금액을 화폐 단위로 표시하는 요소 -->
					
<%-- 					<span class="h4">: <span> 태그를 사용하여 텍스트를 감싸고 있습니다. 클래스 이름인 "h4"는 CSS 스타일을 적용하기 위해 사용될 수 있는 클래스명입니다. 이 경우 해당 요소에 'h4' 스타일이 적용될 수 있도록 클래스가 설정되어 있습니다.
Total: : "Total: "이라는 문자열을 화면에 출력합니다.
<fmt:formatNumber>: JSTL의 포맷 라이브러리인 <fmt:formatNumber>를 사용하여 값을 형식화합니다. ${cart.totalAmount}에서 cart는 JSP 페이지에서 이미 설정된 변수이며, totalAmount는 해당 장바구니의 총 금액을 나타냅니다. 이 태그는 숫자 값을 통화 형식으로 형식화하여 출력합니다.
</span>: <span> 태그의 끝을 나타냅니다.
따라서 위 코드는 "Total: " 문자열 다음에 해당 장바구니의 총 금액을 통화 형식으로 출력하는 HTML 요소를 나타냅니다. 해당 요소는 "h4"라는 클래스명을 가진 스타일을 가질 수 있습니다. --%>
					
			    </div>
			</div>
			
			<div class="row">&nbsp;</div> <!-- 공백 -->
			
			<div class="row"> <!-- 클래스가 "row"인 div 요소 -->
			    <div class="col-12 text-center"> <!-- 클래스가 "col-12 text-center"인 div 요소 -->
			        <button type="submit" class="btn btn-primary">Update</button> <!-- 'Update' 버튼을 나타내는 요소 -->
			        <!-- <button type="submit" class="btn btn-primary">Update</button>: 'Update'라는 텍스트를 가진 제출(submit) 버튼을 나타내는 요소입니다. btn 및 btn-primary 클래스는 Bootstrap에서 버튼 스타일을 적용하는 데 사용됩니다. -->
			        
			        &nbsp;&nbsp; <!-- 공백 -->
			        <input type="button" class="btn btn-secondary" id="clearCart" value="Clear Cart"/> <!-- 'Clear Cart' 버튼을 나타내는 입력 요소 -->
			        <!-- <input type="button" class="btn btn-secondary" id="clearCart" value="Clear Cart"/>: 'Clear Cart'라는 텍스트를 가진 버튼을 나타내는 입력 요소입니다. btn 및 btn-secondary 클래스는 Bootstrap에서 버튼 스타일을 적용하는 데 사용됩니다. id="clearCart"는 해당 요소를 식별하기 위한 고유한 ID입니다. -->
			        
<!-- <input type="button" ...>: 입력 타입을 버튼(Button)으로 설정합니다.
class="btn btn-secondary": 버튼에 스타일을 지정하기 위한 Bootstrap의 클래스입니다. 'btn' 클래스는 Bootstrap에서 버튼 스타일을 지정하고, 'btn-secondary'는 부가적인 버튼 스타일을 나타냅니다.
id="clearCart": 버튼의 고유한 식별자(ID)로, JavaScript나 CSS에서 해당 버튼을 식별할 때 사용될 수 있습니다.
value="Clear Cart": 버튼에 표시되는 텍스트로, 'Clear Cart'라는 문구가 버튼에 표시됩니다. -->
			        
			    </div>
			</div>
			
<!-- <div class="row">&nbsp;</div>: 한 줄의 공백을 만들기 위한 <div> 요소입니다. &nbsp;는 HTML에서 공백을 나타내는 특수 문자입니다. 이것은 비어있는 공백을 갖는 빈 <div> 요소를 생성합니다.
<div class="row">: 클래스가 "row"인 div 요소로, Bootstrap의 그리드 시스템을 사용하여 레이아웃을 정의하는데 사용됩니다.
<div class="col-12 text-center">: 클래스가 "col-12 text-center"인 div 요소로, Bootstrap의 그리드 시스템을 사용하여 12개의 컬럼을 차지하며 텍스트를 가운데 정렬하는 역할을 합니다.
<button type="submit" class="btn btn-primary">Update</button>: 'Update'라는 텍스트를 가진 제출(submit) 버튼을 나타내는 요소입니다. btn 및 btn-primary 클래스는 Bootstrap에서 버튼 스타일을 적용하는 데 사용됩니다.
&nbsp;&nbsp;: HTML에서 공백을 나타내는 특수 문자로, 두 칸의 공백을 만듭니다.
<input type="button" class="btn btn-secondary" id="clearCart" value="Clear Cart"/>: 'Clear Cart'라는 텍스트를 가진 버튼을 나타내는 입력 요소입니다. btn 및 btn-secondary 클래스는 Bootstrap에서 버튼 스타일을 적용하는 데 사용됩니다. id="clearCart"는 해당 요소를 식별하기 위한 고유한 ID입니다.
이 코드는 버튼들을 포함한 레이아웃을 만들어 사용자에게 업데이트나 장바구니 비우기 등의 옵션을 제공합니다. -->
			
			<div class="row">&nbsp;</div> <!-- 공백 -->

			
			<div class="row"> <!-- 클래스가 "row"인 div 요소 -->
			    <div class="col-12 text-center"> <!-- 클래스가 "col-12 text-center"인 div 요소 -->
			        <a href="${pageContext.request.contextPath}/">Continue Shopping</a> <!-- 페이지의 루트 경로로 이동하는 링크 -->
			        &nbsp;&nbsp; <!-- 공백 -->
			        <a href="checkout">Checkout</a> <!-- "checkout" 페이지로 이동하는 링크 -->
			    </div>
			</div>

<%-- <div class="row">: 클래스가 "row"인 div 요소로, Bootstrap의 그리드 시스템을 사용하여 레이아웃을 정의하는데 사용됩니다.
<div class="col-12 text-center">: 클래스가 "col-12 text-center"인 div 요소로, Bootstrap의 그리드 시스템을 사용하여 12개의 컬럼을 차지하며 텍스트를 가운데 정렬하는 역할을 합니다.
<a href="${pageContext.request.contextPath}/">Continue Shopping</a>: 페이지의 루트 경로로 이동하는 링크를 나타내는 요소입니다. ${pageContext.request.contextPath}/는 페이지의 루트 경로를 나타냅니다. 'Continue Shopping'이라는 텍스트를 클릭하면 해당 페이지의 루트 경로로 이동합니다.
&nbsp;&nbsp;: HTML에서 공백을 나타내는 특수 문자로, 두 칸의 공백을 만듭니다.
<a href="checkout">Checkout</a>: "checkout" 페이지로 이동하는 링크를 나타내는 요소입니다. 'Checkout'이라는 텍스트를 클릭하면 "checkout" 페이지로 이동합니다.
이 코드는 'Continue Shopping'과 'Checkout'이라는 두 개의 링크를 포함하는 레이아웃을 생성합니다. 'Continue Shopping' 링크는 페이지의 루트 경로로 이동하고, 'Checkout' 링크는 "checkout" 페이지로 이동합니다.				 --%>
		</form>
	</c:if>

	<jsp:directive.include file="footer.jsp" />
</div>

	<script type="text/javascript">
	    $(document).ready(function() { // 문서가 준비되면
	        $("#clearCart").click(function() { // clearCart 요소가 클릭되면
	            window.location = 'clear_cart'; // 'clear_cart'로 페이지를 이동한다
	        });
	    });
	</script>

<!--  원래 아래처럼 script js 되어있었는데 삭제됨(html5 에서 어차피 유효성 검증 하기 때문) -->
<!-- 	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#clearCart").click(function() {
				window.location = 'clear_cart';
			});
			
			$("#cartForm").validate({
				rules : {
					<c:forEach items="${cart.items}" var="item" varStatus="status">
						quantity${status.index + 1}: {
							required: true, number: true, min: 1
						},
					</c:forEach>
				},

				messages : {
					<c:forEach items="${cart.items}" var="item" varStatus="status">
						quantity${status.index + 1}: { 
							required: "Please enter quantity",
							number: "Quantity must be a number",
							min: "Quantity must be greater than 0"
						},
					</c:forEach>					
				}
			});

		});
	</script> -->


</body>
</html>