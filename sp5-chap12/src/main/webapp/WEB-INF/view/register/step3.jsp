<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
	<h1><spring:message code="member.register.done"/></h1>
	<p><spring:message code="register.done" arguments="${formData.name }"/></p>
	<a href="<c:url value='/main/'/>"><spring:message code="go.main"/></a>
</body>
</html>