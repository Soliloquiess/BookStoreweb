<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
</head>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="Order Details" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	
	<div class="row">&nbsp;</div>
	
	<div class="row">
		<div class="col text-center">
			<h3>Details of Order ID: ${order.orderId}</h3>
		</div>
	</div>
	
	<div class="row">&nbsp;</div>
	
	<c:if test="${message != null}">
		<div class="row">
			<div class="col text-center text-success">	
				<h4>${message}</h4>
			</div>
		</div>
	</c:if>

	<jsp:directive.include file="../common/common_order_detail.jsp" />	

	<div class="row m-4">
		<div class="col text-center">
			<a href="edit_order?id=${order.orderId}" class="btn btn-primary mr-2">Edit this Order</a>
			<a href="javascript:void(0);" class="deleteLink btn btn-secondary ml-2" id="${order.orderId}">Delete this Order</a>
		</div>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</div>	
	<script>
		$(document).ready(function() {
			$(".deleteLink").each(function() {
				$(this).on("click", function() {
					orderId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the order with ID ' +  orderId + '?')) {
						window.location = 'delete_order?id=' + orderId;
					}					
				});
			});
		});	
	</script>
</body>
</html>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Order Details - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Details of Order ID: ${order.orderId}</h2>		
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>

	<jsp:directive.include file="../common/common_order_detail.jsp" />	
	<div align="center">
		<h2>Order Overview:</h2>	
		<table>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Book Copies: </b></td>
				<td>${order.bookCopies}</td>
			</tr>

			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>												
		</table>
	</div>
<div class="row">
	<div class="col-sm-2"></div>
	
	<div class="col-sm-8">
		<div class="card">
			<div class="card-header">
				<div class="text-center"><h3>Order Overview:</h3></div>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col"><b>Ordered By: </b></div>
					<div class="col">${order.customer.fullname}</div>
				</div>
				<div class="row">
					<div class="col"><b>Order Status: </b></div>
					<div class="col">${order.status}</div>
				</div>
				<div class="row">
					<div class="col"><b>Order Date: </b></div>
					<div class="col">${order.orderDate}</div>
				</div>
				<div class="row">
					<div class="col"><b>Payment Method: </b></div>
					<div class="col">${order.paymentMethod}</div>
				</div>						
				<div class="row">
					<div class="col"><b>Book Copies: </b></div>
					<div class="col">${order.bookCopies}</div>
				</div>
				<div class="row">
					<div class="col"><b>Total Amount: </b></div>
					<div class="col"><fmt:formatNumber value="${order.total}" type="currency" /></div>
	I'm using this tag:
<fmt:formatNumber value="${order.total}" type="currency" />
The currency symbol depends on the locale setting (language) of your browser.
Try to use a fixed symbol like this:

<fmt:formatNumber type="currency" currencySymbol="$" value="${order.total}" />
		
				</div>			
			</div>
		</div>
		
		<div class="row">&nbsp;</div>
		
		<div class="card">
			<div class="card-header">
				<div class="text-center"><h3>Recipient Information:</h3></div>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col"><b>First Name: </b></div>
					<div class="col">${order.firstname}</div>
				</div>
				<div class="row">
					<div class="col"><b>Last Name: </b></div>
					<div class="col">${order.firstname}</div>
				</div>			
				<div class="row">
					<div class="col"><b>Phone: </b></div>
					<div class="col">${order.phone}</div>
				</div>
				<div class="row">
					<div class="col"><b>Address Line 1: </b></div>
					<div class="col">${order.addressLine1}</div>
				</div>
				<div class="row">
					<div class="col"><b>Address Line 2: </b></div>
					<div class="col">${order.addressLine1}</div>
				</div>					
				<div class="row">
					<div class="col"><b>City: </b></div>
					<div class="col">${order.city}</div>
				</div>
				<div class="row">
					<div class="col"><b>State: </b></div>
					<div class="col">${order.state}</div>
				</div>
				<div class="row">
					<div class="col"><b>Country: </b></div>
					<div class="col">${order.countryName}</div>
				</div>
				<div class="row">
					<div class="col"><b>Zipcode: </b></div>
					<div class="col">${order.zipcode}</div>
				</div>				
			</div>
		</div>
		
		<div class="row">&nbsp;</div>
		
		<div class="card">
			<div class="card-header">
				<div class="text-center"><h3>Ordered Books:</h3></div>
			</div>
			<div class="card-body">
				<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
					<div class="row">
						<div class="col-sm">${status.index + 1}</div>
						<div class="col-5"><img width="128" height="164" src="data:image/jpg;base64,${orderDetail.book.base64Image}" /></div>
						<div class="col-6">
							<div><h6>${orderDetail.book.title}</h6></div>
							<div>by <i>${orderDetail.book.author}</i></div>
							<div><fmt:formatNumber value="${orderDetail.book.price}" type="currency" /></div>								
							<div>									
								X ${orderDetail.quantity}
							</div>
							<div>= <b><fmt:formatNumber	value="${orderDetail.subtotal}" type="currency" /></b></div>									
						</div>								
					</div>
					<div class="row">&nbsp;</div>				
				</c:forEach>
			</div>
		</div>
		
		<div class="row">&nbsp;</div>
		
		<div class="card">
			<div class="card-header">
				<div class="text-center"><h3>Summary:</h3></div>
			</div>
			<div class="card-body">
				<div class="col text-center">
					<p>Subtotal: <fmt:formatNumber value="${order.subtotal}" type="currency" /></p>
					<p>Tax: <fmt:formatNumber value="${order.tax}" type="currency" /></p>
					<p>Shipping Fee: <fmt:formatNumber value="${order.shippingFee}" type="currency" /></p>
					<p>TOTAL: <fmt:formatNumber value="${order.total}" type="currency" /></p>				
				</div>
			</div>
		</div>						
	</div>
	
	<div class="col-sm-2"></div>
</div>
	
	<div align="center">
		<h2>Order Overview:</h2>	
		<table>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Book Copies: </b></td>
				<td>${order.bookCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount: </b></td>
				<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
			</tr>			
			<tr>
				<td><b>Recipient Name: </b></td>
				<td>${order.recipientName}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td>${order.recipientPhone}</td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td><b>Shipping Address: </b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Order Status: </b></td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
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
				<th>Subtotal</thd>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>
					<img style="vertical-align: middle;" src="data:image/jpg;base64,${orderDetail.book.base64Image}" width="48" height="64" />
					${orderDetail.book.title}
				</td>
				<td>${orderDetail.book.author}</td>
				<td><fmt:formatNumber value="${orderDetail.book.price}" type="currency" /></td>
				<td>${orderDetail.quantity}</td>
				<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL:</i></b>
				</td>
				<td>
					<b>${order.bookCopies}</b>
				</td>
				<td>
					<b><fmt:formatNumber value="${order.total}" type="currency" /></b>
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<br/>
		<a href="edit_order?id=${order.orderId}">Edit this Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">Delete this Order</a>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
	
	<script>
		$(document).ready(function() {
			$(".deleteLink").each(function() {
				$(this).on("click", function() {
					reviewId = $(this).attr("id");
					if (confirm('Are you sure you want to delete the review with ID ' +  reviewId + '?')) {
						window.location = 'delete_review?id=' + reviewId;
					}					
				});
			});
		});	
	</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Order Details - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Details of Order ID: ${order.orderId}</h2>		
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>

	<jsp:directive.include file="../common/common_order_detail.jsp" />	
<!-- 	원래 여기 구현한거 ../common/common_order_detail.jsp 여기로 다 이동시킴 -->

	<div align="center"> <!-- 가운데 정렬을 위한 div 요소 시작 -->
	    <br/> <!-- 줄 바꿈 -->
	    <a href="edit_order?id=${order.orderId}">Edit this Order</a> <!-- 주문을 수정하는 링크 -->
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <!-- 공백 -->
	    <a href="">Delete this Order</a> <!-- 주문을 삭제하는 링크 -->
	</div> <!-- 가운데 정렬을 위한 div 요소 종료 -->
	
	<jsp:directive.include file="footer.jsp" /> <!-- footer.jsp 파일을 포함하는 JSP 지시문 -->
	
	<script>
	    $(document).ready(function() { // 문서가 준비되면 실행되는 JavaScript 함수
	        $(".deleteLink").each(function() { // deleteLink 클래스를 갖는 요소마다 반복문 실행
	            $(this).on("click", function() { // 해당 요소를 클릭했을 때 실행되는 함수
	                reviewId = $(this).attr("id"); // 클릭한 요소의 id 속성 값을 가져와 reviewId 변수에 저장
	                if (confirm('Are you sure you want to delete the review with ID ' +  reviewId + '?')) { // 삭제 여부를 묻는 확인 대화상자 표시
	                    window.location = 'delete_review?id=' + reviewId; // 확인을 선택하면 해당 리뷰를 삭제하는 페이지로 이동
	                }                   
	            });
	        });
	    }); 
	</script>

</body>
</html> --%>