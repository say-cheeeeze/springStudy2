<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Step2 -회원정보 입력</title>
</head>
<body>
	<h1>Step2 - 회원정보 입력 </h1>
	<%-- <form action="step3" method="post">
		<p>
			<label>이메일 : <input type="text" name="email" id="email" value="${formData.email }"></label>
		</p>
		<p>
			<label>이름 : <input type="text" name="name" id="name" value="${formData.name }"></label>
		</p>
		<p>
			<label>비밀번호 : <input type="password" name="password" id="password"></label>
		</p>
		<p>
			<label>비밀번호 확인 : <input type="password" name="confirmPassword" id="confirmPassword"></label>
		</p>
		<input type="submit" value="가입 완료">
	</form> --%>
	
	<form:form action="step3" modelAttribute="registerRequest">
		<p>
			<label>이메일 : <form:input path="email"/></label>
		</p>
		<p>
			<label>이름 : <form:input path="name"/></label>
		</p>
		<p>
			<label>비밀번호 : <form:password path="password"/></label>
		</p>
		<p>
			<label>비밀번호 확인 : <form:password path="confirmPassword"/></label>
		</p>
		<input type="submit" value="가입완료">
	</form:form>
</body>
</html>