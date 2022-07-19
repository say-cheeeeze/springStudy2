<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Step3 - 회원가입 성공</title>
</head>
<body>
	<h1>Step3 - 회원가입 성공</h1>
	<p>${ formData.name }님</p> 회원가입을 축하합니다.
	<a href="<c:url value='/main/'/>">첫 화면으로 이동</a>
</body>
</html>