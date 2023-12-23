<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Order - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId}</h2>		
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	<form action="update_order" method="post">
	<div align="center">
	
		<table>
			<h2>Order Overview:</h2>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>
					<select name="paymentMethod">
						<option value="Cash On Delivery" <c:if test="${order.paymentMethod eq 'Cash On Delivery'}">selected='selected'</c:if>>Cash On Delivery</option>
						<option value="paypal" <c:if test="${order.paymentMethod eq 'paypal'}">selected='selected'</c:if>>PayPal or Credit card</option>
					</select>
				</td>
			</tr>

			<tr>
				<td><b>Order Status: </b></td>
				<td>
					<select name="orderStatus">
						<option value="Processing" <c:if test="${order.status eq 'Processing' }">selected='selected'</c:if> >Processing</option>
						<option value="Shipping" <c:if test="${order.status eq 'Shipping' }">selected='selected'</c:if>>Shipping</option>
						<option value="Delivered" <c:if test="${order.status eq 'Delivered' }">selected='selected'</c:if>>Delivered</option>
						<option value="Completed" <c:if test="${order.status eq 'Completed' }">selected='selected'</c:if>>Completed</option>
						<option value="Cancelled" <c:if test="${order.status eq 'Cancelled' }">selected='selected'</c:if>>Cancelled</option>
					</select>
				</td>
			</tr>
		</table>
		<h2>Recipient Information</h2>
		<table>	
			<tr>
				<td><b>First Name: </b></td>
				<td><input type="text" name="firstname" value="${order.firstname}" size="45" required minlength="3" maxlength="30" /></td>
			</tr>
			<tr>
				<td><b>Last Name: </b></td>
				<td><input type="text" name="lastname" value="${order.lastname}" size="45" required minlength="3" maxlength="30" /></td>
			</tr>			
			<tr>
				<td><b>Phone: </b></td>
				<td><input type="text" name="phone" value="${order.phone}" size="45" required minlength="9" maxlength="15" /></td>
			</tr>		
			<tr>
				<td><b>Address Line 1: </b></td>
				<td><input type="text" name="address1" value="${order.addressLine1}" size="45" required minlength="10" maxlength="256" /></td>
			</tr>
			<tr>
				<td><b>Address Line 2: </b></td>
				<td><input type="text" name="address2" value="${order.addressLine2}" size="45" required minlength="10" maxlength="256" /></td>
			</tr>
			<tr>
				<td><b>City: </b></td>
				<td><input type="text" name="city" value="${order.city}" size="45" required minlength="3" maxlength="32" /></td>
			</tr>			
			<tr>
				<td><b>State: </b></td>
				<td><input type="text" name="state" value="${order.state}" size="45" required minlength="3" maxlength="45" /></td>
			</tr>
			<tr>
				<td><b>Zipcode: </b></td>
				<td><input type="text" name="zipcode" value="${order.zipcode}" size="45" required minlength="3" maxlength="24" /></td>
			</tr>
			<tr>
				<td><b>Country: </b></td>
				<td>
					<select name="country" required>
					  <!-- 'country'라는 이름의 셀렉트 박스(드롭다운 목록)를 생성합니다. -->
            <!-- 'required' 속성으로 필수 선택 필드로 설정합니다. -->
					<c:forEach items="${mapCountries}" var="country">
		                <!-- 'mapCountries'에 대한 반복문을 시작합니다. -->
		                <!-- 각 'country'를 순회하며 아래의 옵션을 생성합니다. -->
		                
		                <option value="${country.value}" 
		                        <c:if test='${order.country eq country.value}'>selected='selected'</c:if>>
		                    <!-- 옵션을 생성하고, 'value' 속성은 실제 값으로, 'country.value' 값을 사용합니다. -->
		                    <!-- 주문의 국가가 해당 옵션의 국가와 동일한 경우, 해당 옵션을 선택 상태로 표시합니다. -->
		                    
		                    ${country.key}
		                    <!-- 옵션 텍스트로 국가 이름을 표시합니다. -->
		                </option>
		            </c:forEach>
					</select>				
				</td>
			</tr>					
		</table>
	</div>
	<div align="center">
		<h2>Ordered Books</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
				<th></th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${orderDetail.book.title}</td>
				<td>${orderDetail.book.author}</td>
				<td>
					<input type="hidden" name="price" value="${orderDetail.book.price}" />
					<fmt:formatNumber value="${orderDetail.book.price}" type="currency" />
				</td>
				<td>
					<input type="hidden" name="bookId" value="${orderDetail.book.bookId}" />
					<input type="number" name="quantity${status.index + 1}" value="${orderDetail.quantity}" size="5" step="1" min="1" required />
				</td>
				
				<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
				<td><a href="remove_book_from_order?id=${orderDetail.book.bookId}">Remove</a></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="7" align="right">
					<p>Subtotal: <fmt:formatNumber value="${order.subtotal}" type="currency" /></p>
					<p>Tax: <input type="number" size="5" name="tax" value="${order.tax}" required step="0.1" min="0.0" /></p>
					<p>Shipping Fee: <input type="number" size="5" name="shippingFee" value="${order.shippingFee}" required  step="0.1" min="0.0"/></p>
					<p>TOTAL: <fmt:formatNumber value="${order.total}" type="currency" /></p>
				</td>				
			</tr>
		</table>
	</div>
	<div align="center">
		<br/>		
		<a href="javascript:showAddBookPopup()"><b>Add Books</b></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="Save" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Cancel" onclick="javascript:window.location.href='list_order';" /> 		
	</div>
	</form>
	
	<jsp:directive.include file="footer.jsp" />
	
	<script>
	    // 새 책을 추가하는 팝업을 표시하는 함수 정의
	    function showAddBookPopup() {
	        // 팝업 창의 너비와 높이 설정
	        var width = 600;
	        var height = 250;
	        
	        // 팝업 창이 화면 가운데에 위치하도록 left와 top 위치 설정
	        var left = (screen.width - width) / 2;
	        var top = (screen.height - height) / 2;
	        
	        // window.open 메서드를 사용하여 새 창 열기
	        window.open('add_book_form', '_blank', 
	                    'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
	        // window.open()으로 새 창을 열고, 'add_book_form'을 로드하며, 새 창의 크기 및 위치를 설정합니다.
	        // '_blank'는 새 창이 열리는 방식을 나타냅니다.
	        // 위에서 설정한 너비, 높이, 위치 정보를 문자열로 조합하여 새 창의 속성으로 전달합니다.
	    }
	</script>
</body>
</html>