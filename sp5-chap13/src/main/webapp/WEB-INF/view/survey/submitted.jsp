<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 응답 제출</title>
</head>
<body>
	<h1>응답 결과</h1>
	<ul>
		<c:forEach var="response" items="${ answerInfo.answerList }" varStatus="status">
			<li>${status.index+1 }번 문항 : ${response }</li>
		</c:forEach>
	</ul>
	<p>응답자 나이 : ${ answerInfo.surveyUserInfo.age }</p>
	<p>응답자 위치 : ${ answerInfo.surveyUserInfo.location }</p>
</body>
</html>