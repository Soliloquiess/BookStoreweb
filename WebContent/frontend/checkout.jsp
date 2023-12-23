<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="Checkout" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	
	<div class="row">&nbsp;</div>
	
	<c:if test="${message != null}"> <!-- 메시지가 null이 아닌 경우 -->
	    <div class="row">
	        <div class="col text-center"><h4>${message}</h4></div> <!-- 메시지 출력 -->
	    </div>		
	</c:if>
	
	<c:set var="cart" value="${sessionScope['cart']}" /> <!-- 세션에서 'cart' 변수 설정 -->
	
	<c:if test="${cart.totalItems == 0}"> <!-- 카트에 담긴 총 아이템 수가 0인 경우 -->
	    <div class="row">
	        <div class="col text-center"><h4>장바구니에 아이템이 없습니다.</h4></div> <!-- 장바구니에 아이템이 없다는 메시지 출력 -->
	    </div>		
	</c:if>

	<c:if test="${cart.totalItems > 0}">			
		<div class="row">
			<div class="col-sm-2"></div>
			
			<div class="col-sm-8">
				<div class="card">
					<div class="card-header">
						<div class="text-center"><h3>Review Your Order Details <a href="view_cart">Edit</a></h3></div>		
					</div>
					<div class="card-body">
						<div>
							<c:forEach items="${cart.items}" var="item" varStatus="status">
								<div class="row">
									<div class="col-sm">${status.index + 1}</div>
									<div class="col-5"><img width="128" height="164" src="data:image/jpg;base64,${item.key.base64Image}" /></div>
									<div class="col-6">
										<div><h6>${item.key.title}</h6></div>
										<div>by <i>${item.key.author}</i></div>
										<div><fmt:formatNumber value="${item.key.price}" type="currency" /></div>								
										<div>									
											X <input type="text" name="quantity${status.index + 1}" value="${item.value}" size="5" readonly="readonly" />
										</div>
										<div>= <b><fmt:formatNumber	value="${item.value * item.key.price}" type="currency" /></b></div>									
									</div>								
								</div>
								<div class="row">&nbsp;</div>
							</c:forEach>
			
							<div class="row">							
								<div class="col text-center">
									<p>Number of copies: ${cart.totalQuantity}</p>
									<p>Subtotal: <fmt:formatNumber value="${cart.totalAmount}" type="currency" /></p>
									<p>Tax: <fmt:formatNumber value="${tax}" type="currency" /></p>
									<p>Shipping Fee: <fmt:formatNumber value="${shippingFee}" type="currency" /></p>
									<p>TOTAL: <fmt:formatNumber value="${total}" type="currency" /></p>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">&nbsp;</div>
				
				<div class="card">
					<div class="card-header">
						<div class="text-center"><h3>Recipient Information</h3></div>		
					</div>
				</div>
				<div class="card-body">
					<form action="place_order" method="post">
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">First Name:</label>
							<div class="col-sm-8"><input type="text" name="firstname" value="${loggedCustomer.firstname}" required minlength="3" maxlength="30" class="form-control" /></div>
						</div>
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Last Name:</label>
							<div class="col-sm-8"><input type="text" name="lastname" value="${loggedCustomer.lastname}" required minlength="3" maxlength="30" class="form-control"/></div>
						</div>						
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Phone:</label>
							<div class="col-sm-8"><input type="text" name="phone" value="${loggedCustomer.phone}" required minlength="9" maxlength="15" class="form-control"/></div>
						</div>
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Address Line 1:</label>
							<div class="col-sm-8"><input type="text" name="address1" value="${loggedCustomer.addressLine1}" required minlength="10" maxlength="256" class="form-control" /></div>
						</div>
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Address Line 2:</label>
							<div class="col-sm-8"><input type="text" name="address2" value="${loggedCustomer.addressLine2}" required minlength="10" maxlength="256" class="form-control"/></div>
						</div>						
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">City:</label>
							<div class="col-sm-8"><input type="text" name="city" value="${loggedCustomer.city}" required minlength="3" maxlength="32" class="form-control"/></div>
						</div>
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">State:</label>
							<div class="col-sm-8"><input type="text" name="state" value="${loggedCustomer.state}" required minlength="3" maxlength="45" class="form-control" /></div>
						</div>												
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Zip Code:</label>
							<div class="col-sm-8"><input type="text" name="zipcode" value="${loggedCustomer.zipcode}" required minlength="3" maxlength="24" class="form-control"/></div>
						</div>						
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Country:</label>
							<div class="col-sm-8">
								<select name="country" required class="form-control">
								<c:forEach items="${mapCountries}" var="country">
									<option value="${country.value}" <c:if test='${loggedCustomer.country eq country.value}'>selected='selected'</c:if> >${country.key}</option>
								</c:forEach>
								</select>							
							</div>
						</div>																								
					
						<div class="row">
							<div class="col text-center"><h2>Payment:</h2></div>
						</div>					
							
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Choose your payment method:</label>
							<div class="col-sm-8">
								<select name="paymentMethod" required class="form-control">
									<option value="Cash On Delivery">Cash On Delivery</option>
									<option value="paypal">PayPal or Credit card</option>
								</select>
							</div>
						</div>
						<div class="row">
				    <div class="col text-center">
				        <!-- 주문하기 버튼 -->
				        <button type="submit" class="btn btn-primary">주문하기</button> <!-- 사용자가 주문을 완료하도록 하는 버튼 -->
				
				        &nbsp;&nbsp; <!-- 공백 추가 -->
				
				        <!-- 쇼핑 계속하기 링크 -->
				        <a href="${pageContext.request.contextPath}/">쇼핑 계속하기</a> <!-- 사용자가 쇼핑을 계속할 수 있는 링크 -->
				  
<%-- 				  ${pageContext.request.contextPath}는 JSP 페이지에서 사용되는 내장 객체인 pageContext를 통해 현재 웹 애플리케이션의 루트 경로를 나타냅니다.
/는 루트 경로에 대한 절대 경로를 나타냅니다.
즉, 이 링크는 사용자가 클릭하면 현재 웹 애플리케이션의 루트 경로로 이동하여 쇼핑을 계속할 수 있는 기능을 제공합니다. 보통 이러한 링크는 웹 애플리케이션에서 메인 페이지나 쇼핑 홈페이지로 연결되는 역할을 합니다. 사용자가 이 링크를 클릭하면 홈페이지나 쇼핑 페이지 등으로 이동할 수 있습니다. --%>
				    </div>
				</div>
							
					</form>						
				</div>
				
	
			</div>
			
			<div class="col-sm-2"></div>
			
		</div>			
	</c:if>

	<jsp:directive.include file="footer.jsp" />
</div>
</body>
</html>