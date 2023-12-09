<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <%@ taglib %> 지시문은 JSP(JavaServer Pages) 파일에서 JSTL(JavaServer Pages Standard Tag Library) 태그를 사용하기 위해 필요한 태그 라이브러리를 선언하는 데 사용됩니다.
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>은 JSTL의 Core 태그 라이브러리를 선언하는 것입니다. 이 라이브러리는 JSTL의 기본적인 기능과 태그들을 포함하고 있습니다. 예를 들어, <c:forEach>, <c:if>, <c:choose> 등의 제어 흐름 태그나 EL(Expression Language)을 사용하기 위해 필요한 태그들이 여기에 속합니다.
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>는 JSTL의 Formatting 태그 라이브러리를 선언하는 것입니다. 이 라이브러리에는 날짜 및 시간 형식 지정, 숫자 및 통화 포맷팅 등과 관련된 태그들이 포함되어 있습니다. <fmt:formatDate>, <fmt:parseNumber> 등이 이에 해당합니다. --%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Create New Book</title>
    
    <!-- CSS 파일 및 외부 라이브러리 스타일 시트 연결 -->
    <link rel="stylesheet" href="../css/style.css" >
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="..//css/richtext.min.css">    
    
    <!-- jQuery 및 기타 자바스크립트 파일 연결 -->
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
    <!-- 텍스트 편집기 불러옴 -->
    
<script>
    // 입력 필드에서 마지막 공백을 제거하는 함수
    function removeTrailingSpace() {
        var titleField = document.getElementById("titleInput");
        titleField.value = titleField.value.trim(); // 입력 필드의 값에서 마지막 공백 제거
    }
</script>

</head>
<body>
    <!-- header.jsp 파일 include -->
    <jsp:directive.include file="header.jsp" />
    
    <div align="center">
        <!-- Book이 존재하면 Edit Book, 아니면 Create New Book 출력 -->
        <h2 class="pageheading">
            <c:if test="${book != null}">
                Edit Book
            </c:if>
            <c:if test="${book == null}">
                Create New Book
            </c:if>
        </h2>
    </div>
    
    <div align="center">
        <!-- Book이 존재하면 update_book으로, 없으면 create_book으로 데이터 전송하는 form 생성 -->
        <c:if test="${book != null}">
            <form action="update_book" method="post" enctype="multipart/form-data"> <!--  enctype="multipart/form-data" 꼭 넣어줘야 파일 들어감. -->
                <input type="hidden" name="bookId" value="${book.bookId}">
        </c:if>
        <c:if test="${book == null}">
            <form action="create_book" method="post" enctype="multipart/form-data">
        </c:if>
        
        <!-- 책 정보 입력을 위한 테이블 -->
        <table class="form">
            <!-- 카테고리 선택 -->
            <tr>
                <td align="right">Category:</td>
                <td>
                    <select name="category" required>
                        <!-- 카테고리 목록을 순회하며 선택된 카테고리 표시 -->
						<c:forEach items="${listCategory}" var="category">
						    <!-- 리스트에서 반복하며 각 카테고리 항목을 가져옵니다 -->
						    <c:if test="${category.categoryId eq book.category.categoryId}">
						        <!-- 만약 현재 카테고리의 categoryId가 현재 책의 categoryId와 일치하면 -->
						        <option value="${category.categoryId}" selected>
						            <!-- 선택된 옵션을 생성하고 값을 categoryId로 설정합니다 -->
						    </c:if>
						    <c:if test="${category.categoryId ne book.category.categoryId}">
						        <!-- 만약 현재 카테고리의 categoryId가 현재 책의 categoryId와 일치하지 않으면 -->
						        <option value="${category.categoryId}">
						            <!-- 일반 옵션을 생성하고 값을 categoryId로 설정합니다 -->
						    </c:if>                            
						    ${category.name}
						    <!-- 옵션의 텍스트로 현재 카테고리의 이름을 사용합니다 -->
						</option>
						<!-- 각 카테고리에 대한 옵션을 완료합니다 -->
						</c:forEach>
                    </select>
                </td>
            </tr>
            <!-- 책 제목 입력 -->
            <tr>
                <td align="right">Title:</td>
    			<td align="left"><input type="text" name="title" id="titleInput" size="20" value="${book.title}" required onblur="removeTrailingSpace()" /></td>
            </tr>
            <!-- 저자 입력 -->
            <tr>
                <td align="right">Author:</td>
                <td align="left"><input type="text" name="author" size="20" value="${book.author}" required /></td>
            </tr>
            <!-- ISBN 입력 -->
            <tr>
                <td align="right">ISBN:</td>
                <td align="left"><input type="text" name="isbn" size="20" value="${book.isbn}" required /></td>
            </tr>
            <!-- 출판일 입력 -->
            <tr>
                <td align="right">Publish Date:</td>
                <td align="left">
                    <input type="date" name="publishDate" size="20" required
                        value="<fmt:formatDate pattern='yyyy-MM-dd' value='${book.publishDate}' />" />
                    </td>
            </tr>            
            <!-- 책 이미지 및 미리보기 -->
            <tr>
                <td align="right">Book Image:</td>
                <td align="left">
                    <!-- Book이 없을 경우 파일 업로드 필드 표시 -->
                    <c:if test="${book == null}">
                    <input type="file" id="bookImage" name="bookImage" size="20" required /><br/>
                    </c:if>
                    
                    <!-- Book이 있을 경우 파일 업로드 필드 표시하지 않음 -->
                    <c:if test="${book != null}">
                    <input type="file" id="bookImage" name="bookImage" size="20" /><br/>
                    </c:if>                    
                    
                    <!-- Book의 base64 인코딩된 이미지를 미리보기로 출력 -->
                    <img id="thumbnail" alt="Image Preview" style="width:20%; margin-top: 10px"
                        src="data:image/jpg;base64,${book.base64Image}"
                     />
                </td>
            </tr>
            <!-- 책 가격 입력 -->
            <tr>
                <td align="right">Price:</td>
                <td align="left"><input type="text" name="price" size="20" value="${book.price}" required /></td>
            </tr>
            <!-- 책 설명 입력 -->
            <tr>
                <td align="right">Description:</td>
                <td align="left">
                    <textarea rows="5" cols="50" name="description" id="description" required>${book.description}</textarea>
                </td>
            </tr>                                        
            <tr><td>&nbsp;</td></tr>
            <!-- 저장 및 취소 버튼 -->
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
                    <button id="buttonCancel">Cancel</button>
                </td>
            </tr>                
        </table>
        </form>
    </div>

    <!-- footer.jsp 파일 include -->
    <jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">

    // 페이지 로딩 후 실행되는 스크립트
    $(document).ready(function() {
        // 텍스트 에디터 초기화
        $('#description').richText(); // ID가 'description'인 요소에 리치 텍스트 편집기를 초기화합니다.  
        //richText로 텍스트 편집기 불러옴 description는 위 html의 id 맞춰줌( line 132)
        /* 
        richText()는 HTML 텍스트 편집기나 에디터를 활성화하고, 사용자가 리치 텍스트(Rich Text) 형식으로 텍스트를 편집하고 서식을 적용할 수 있도록 도와주는 JavaScript 라이브러리나 플러그인 중 하나입니다.
        일반적으로 richText() 함수는 WYSIWYG(What You See Is What You Get) 에디터를 구현하는 데 사용됩니다. 이를 사용하면 사용자가 텍스트를 입력하고 서식을 지정할 수 있는 기능을 가진 편집기를 웹 페이지에 쉽게 통합할 수 있습니다.
        richText() 함수를 사용하면 사용자가 일반적인 텍스트 입력이 아닌 서식 있는 텍스트(글꼴, 색상, 글자 크기 등)를 입력할 수 있습니다. 사용자가 텍스트를 입력하면서 볼드체, 이탤릭체, 목록, 링크 등을 쉽게 적용할 수 있도록 도와줍니다.
        일반적으로 이 함수는 다른 JavaScript 라이브러리나 플러그인으로 제공되며, 해당 라이브러리의 기능에 따라 다양한 편집 옵션과 스타일을 제공할 수 있습니다. 위의 코드에서 $('#description').richText(); 라인은 description이라는 ID를 가진 요소에 대해 richText() 함수를 호출하여 해당 요소를 리치 텍스트 편집기로 활성화하고 있습니다. 사용자는 이를 통해 텍스트를 리치 텍스트 형식으로 편집할 수 있게 됩니다. */
        
        // 이미지 업로드 시 미리보기 표시
        $('#bookImage').change(function() {
            showImageThumbnail(this); // 'bookImage' input 요소에서 변화가 감지되면 미리보기 함수를 호출합니다.
        });
        
        // 취소 버튼 클릭 시 이전 페이지로 이동
        $("#buttonCancel").click(function() {
            history.go(-1); // 'buttonCancel'이 클릭되면 브라우저의 이전 페이지로 이동합니다.
        });
    });
    
    // 이미지 업로드 시 미리보기 함수
    function showImageThumbnail(fileInput) {
        var file = fileInput.files[0]; // 선택된 이미지 파일을 가져옵니다.
        
        var reader = new FileReader(); // FileReader 객체를 생성합니다.
        
        reader.onload = function(e) {
            $('#thumbnail').attr('src', e.target.result); // 선택된 이미지 파일의 미리보기를 '#thumbnail' 요소에 표시합니다.
        };
        
        reader.readAsDataURL(file); // 이미지 파일을 읽어 base64 형태로 변환합니다.
    }
</script>



<!-- 이전 제이쿼리 코드 -->
<!-- <script type="text/javascript">

	$(document).ready(function() {
		$('#publishDate').datepicker();
		$('#description').richText();
		
		$('#bookImage').change(function() {
			showImageThumbnail(this);
		});
		
		
		$("#bookForm").validate({
			rules: {
				category: "required",
				title: "required",
				author: "required",
				isbn: "required",
				publishDate: "required",
				
				<c:if test="${book == null}">
				bookImage: "required",
				</c:if>
				
				price: "required",
				description: "required",
			},
			
			messages: {
				category: "Please select a category for the book",
				title: "Please enter title of the book",
				author: "Please enter author of the book",
				isbn: "Please enter ISBN of the book",
				publishDate: "Please enter publish date of the book",
				bookImage: "Please choose an image of the book",
				price: "Please enter price of the book",
				description: "Please enter description of the book"
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});
	
	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$('#thumbnail').attr('src', e.target.result);
		};
		
		reader.readAsDataURL(file);
	}
</script>

 -->
</html>


<%-- 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create New Book</title>
	
	<link rel="stylesheet" href="../css/style.css" >
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="..//css/richtext.min.css">	
	
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	
	<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${book != null}">
				Edit Book
			</c:if>
			<c:if test="${book == null}">
				Create New Book
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${book != null}">
			<form action="update_book" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bookId" value="${book.bookId}">
		</c:if>
		<c:if test="${book == null}">
			<form action="create_book" method="post" enctype="multipart/form-data">
		</c:if>
		
		<table class="form">
			<tr>
				<td align="right">Category:</td>
				<td>
					<select name="category" required>
						<c:forEach items="${listCategory}" var="category">
							<c:if test="${category.categoryId eq book.category.categoryId}">
								<option value="${category.categoryId}" selected>
							</c:if>
							<c:if test="${category.categoryId ne book.category.categoryId}">
								<option value="${category.categoryId}">
							</c:if>							
								${category.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" name="title" size="20" value="${book.title}" required /></td>
			</tr>
			<tr>
				<td align="right">Author:</td>
				<td align="left"><input type="text" name="author" size="20" value="${book.author}" required /></td>
			</tr>
			<tr>
				<td align="right">ISBN:</td>
				<td align="left"><input type="text" name="isbn" size="20" value="${book.isbn}" required /></td>
			</tr>
			<tr>
				<td align="right">Publish Date:</td>
				<td align="left">
					<input type="date" name="publishDate" size="20" required
						value="<fmt:formatDate pattern='yyyy-MM-dd' value='${book.publishDate}' />" />
					</td>
			</tr>			
			<tr>
				<td align="right">Book Image:</td>
				<td align="left">
					<c:if test="${book == null}">
					<input type="file" id="bookImage" name="bookImage" size="20" required /><br/>
					</c:if>
					
					<c:if test="${book != null}">
					<input type="file" id="bookImage" name="bookImage" size="20" /><br/>
					</c:if>					
					
					<img id="thumbnail" alt="Image Preview" style="width:20%; margin-top: 10px"
						src="data:image/jpg;base64,${book.base64Image}"
					 />
				</td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" name="price" size="20" value="${book.price}" required /></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left">
					<textarea rows="5" cols="50" name="description" id="description" required>${book.description}</textarea>
				</td>
			</tr>										
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>				
		</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">

	$(document).ready(function() {
		$('#description').richText();
		
		$('#bookImage').change(function() {
			showImageThumbnail(this);
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});
	
	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$('#thumbnail').attr('src', e.target.result);
		};
		
		reader.readAsDataURL(file);
	}
</script>


<%-- <script type="text/javascript">

    // 페이지 로딩 후 실행되는 스크립트
    $(document).ready(function() {
        // 텍스트 에디터 초기화
        $('#description').richText();
        
/* 
        richText()는 HTML 텍스트 편집기나 에디터를 활성화하고, 사용자가 리치 텍스트(Rich Text) 형식으로 텍스트를 편집하고 서식을 적용할 수 있도록 도와주는 JavaScript 라이브러리나 플러그인 중 하나입니다.
        일반적으로 richText() 함수는 WYSIWYG(What You See Is What You Get) 에디터를 구현하는 데 사용됩니다. 이를 사용하면 사용자가 텍스트를 입력하고 서식을 지정할 수 있는 기능을 가진 편집기를 웹 페이지에 쉽게 통합할 수 있습니다.
        richText() 함수를 사용하면 사용자가 일반적인 텍스트 입력이 아닌 서식 있는 텍스트(글꼴, 색상, 글자 크기 등)를 입력할 수 있습니다. 사용자가 텍스트를 입력하면서 볼드체, 이탤릭체, 목록, 링크 등을 쉽게 적용할 수 있도록 도와줍니다.
        일반적으로 이 함수는 다른 JavaScript 라이브러리나 플러그인으로 제공되며, 해당 라이브러리의 기능에 따라 다양한 편집 옵션과 스타일을 제공할 수 있습니다. 위의 코드에서 $('#description').richText(); 라인은 description이라는 ID를 가진 요소에 대해 richText() 함수를 호출하여 해당 요소를 리치 텍스트 편집기로 활성화하고 있습니다. 사용자는 이를 통해 텍스트를 리치 텍스트 형식으로 편집할 수 있게 됩니다. */
        
        
        // 이미지 업로드 시 미리보기 표시
        $('#bookImage').change(function() {
            showImageThumbnail(this);
        });
        
        // 취소 버튼 클릭 시 이전 페이지로 이동
        $("#buttonCancel").click(function() {
            history.go(-1);
        });
    });
    
    // 이미지 업로드 시 미리보기 함수
    function showImageThumbnail(fileInput) {
        var file = fileInput.files[0];
        
        var reader = new FileReader();
        
        reader.onload = function(e) {
            $('#thumbnail').attr('src', e.target.result);
        };
        
        reader.readAsDataURL(file);
    }
</script> --%>
</html> --%>