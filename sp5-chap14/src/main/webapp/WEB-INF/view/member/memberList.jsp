<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회</title>
</head>
<body>
	<form:form modelAttribute="cmd">
		<p>
			<label>from : <form:input path="from"/></label>
			<form:errors path="from"/>
			~
			<label>to : <form:input path="to"/></label>
			<form:errors path="to"/>
			<input type="submit" value="조회">
		</p>
	</form:form>
	
	<c:if test="${ !empty memberList }">
		<table>
			<tr>
				<th>아이디</th>
				<th>이메일</th>
				<th>이름</th>
				<th>가입일</th>
			</tr>
			<c:forEach var="member" items="${memberList }">
				<tr>
					<td>${member.memberId }</td>
					<td><a href="<c:url value='/members/${member.memberId }'/>">${member.memberEmail }</a></td>
					<td>${member.memberName }</td>
					<td><tf:formatDateTime value="${member.inputDateTime }" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>