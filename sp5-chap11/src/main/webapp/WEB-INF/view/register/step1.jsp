<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Step1 - 회원 가입</title>
</head>
<body>
	<h1>Step1 - 회원 가입</h1>
	<p>약관내용</p>
	<form action="step2" method="post">
		<label>
			<input type="checkbox" name="agree" value="true">약관동의
		</label>
		<input type="submit" value="다음 단계"/>
	</form>
</body>
</html>