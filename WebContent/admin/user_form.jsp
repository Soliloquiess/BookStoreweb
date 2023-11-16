<%@ page language="java" contentType="text/html; charset=UTF-8" %> <!-- JSP 페이지 언어 및 콘텐츠 유형을 설정하고 UTF-8 문자 인코딩 지정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL Core 라이브러리를 사용하기 위한 선언 -->

<!DOCTYPE html> <!-- HTML 문서 유형을 선언합니다. -->
<html>
<head>
    <meta charset="UTF-8"> <!-- 콘텐츠 문자 집합을 UTF-8로 설정합니다. -->
    <title>Create New User</title> <!-- 페이지 제목을 설정합니다. -->
    <link rel="stylesheet" href="../css/style.css"> <!-- 외부 CSS 파일을 연결합니다. -->
</head>
<body>
    <%-- Include the header --%>
    <jsp:directive.include file="header.jsp" /> <!-- header.jsp 파일을 include하여 삽입합니다. -->

    <div align="center">
        <h2 class="pageheading">
            <c:if test="${user != null}"> <!-- 'user'가 null이 아니면 조건을 실행합니다. -->
                Edit User <!-- 'Edit User'를 표시합니다. -->
            </c:if>
            <c:if test="${user == null}"> <!-- 'user'가 null이면 조건을 실행합니다. -->
                Create New User <!-- 'Create New User'를 표시합니다. -->
            </c:if>
        </h2>
    </div>

    <div align="center">
        <c:if test="${user != null}"> <!-- 'user'가 null이 아니면 조건을 실행합니다. -->
            <form action="update_user" method="post" style="max-width: 400px; margin: 0 auto;"> <!-- 'update_user'로 양식을 제출하는 form을 생성합니다. -->
            <input type="hidden" name="userId" value="${user.userId}"> <!-- 'userId' 숨은 입력 필드를 생성하고 값으로 '${user.userId}'를 사용합니다. -->
        </c:if>
        <c:if test="${user == null}"> <!-- 'user'가 null이면 조건을 실행합니다. -->
            <form action="create_user" method="post" style="max-width: 400px; margin: 0 auto;"> <!-- 'create_user'로 양식을 제출하는 form을 생성합니다. -->
        </c:if>

        <table class="form"> <!-- 'form' 클래스를 가진 테이블을 생성합니다. -->
            <tr>
                <td align="right">Email:</td> <!-- 이메일을 나타냅니다. -->
                <td align="left">
                    <input type="text" name="email" size="20" value="${user.email}" required minlength="5" maxlength="30" /> <!-- 사용자의 이메일을 입력할 수 있는 입력 필드를 생성합니다. -->
                </td>
            </tr>
            <tr>
                <td align="right">Full name:</td> <!-- 전체 이름을 나타냅니다. -->
                <td align="left">
                    <input type="text" name="fullname" size="20" value="${user.fullName}" required minlength="3" maxlength="30" /> <!-- 사용자의 전체 이름을 입력할 수 있는 입력 필드를 생성합니다. -->
                </td>
            </tr>
            <tr>
                <td align="right">Password:</td> <!-- 비밀번호를 나타냅니다. -->
                <td align="left">
                    <input type="password" name="password" size="20" value="${user.password}" required minlength="4" maxlength="32" /> <!-- 사용자의 비밀번호를 입력할 수 있는 입력 필드를 생성합니다. -->
                </td>
            </tr>
            <tr><td>&nbsp;</td></tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Save</button>&nbsp;&nbsp;&nbsp; <!-- 저장을 나타내는 버튼을 생성합니다. -->
                    <button type="button" onclick="history.go(-1);">Cancel</button> <!-- 취소를 나타내는 버튼을 생성하고 이전 페이지로 돌아가는 JavaScript를 연결합니다. -->
                </td>
            </tr>				
        </table>
        </form>
    </div>

    <%-- Include the footer --%>
    <jsp:directive.include file="footer.jsp" /> <!-- footer.jsp 파일을 include하여 삽입합니다. -->
</body>
</html>
