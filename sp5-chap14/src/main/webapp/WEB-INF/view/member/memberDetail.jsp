<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 화면</title>
</head>
<body>
	<p>아이디 : ${member.memberId }</p>
	<p>이름 : ${member.memberName }</p>
	<p>이메일 : ${member.memberEmail }</p>
	<p>가입일자 : <tf:formatDateTime value="${member.inputDateTime }" pattern="yyyy-MM-dd HH:mm"/></p>
</body>
</html>