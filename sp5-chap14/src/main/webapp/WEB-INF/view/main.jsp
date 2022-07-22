<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>
	<c:if test="${empty authInfo }">
		<p>환영합니다.</p>
		<a href="<c:url value='/register/step1'/>">회원가입하기</a>
		<a href="<c:url value='/login'/>">로그인화면으로</a>
	</c:if>
	<c:if test="${!empty authInfo }">
		<h1>${authInfo.name }님, 환영합니다! </h1>
		<a href="<c:url value='/edit/changePassword'/>">비밀번호 변경</a>
		<a href="<c:url value='/logout'/>">로그아웃하기</a>
	</c:if>
	
</body>
</html>