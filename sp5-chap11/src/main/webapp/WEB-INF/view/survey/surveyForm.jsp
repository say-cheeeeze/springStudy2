<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 Form</title>
</head>
<body>
	<h1>설문 Form</h1>
	<!-- <form method="post">
	1.담당 직무
	<label>
		<input type="radio" name="answerList[0]" value="서버">서버개발자
	</label>
	<label>
		<input type="radio" name="answerList[0]" value="프론트">프론트개발자
	</label>
	<label>
		<input type="radio" name="answerList[0]" value="풀스택">풀스택 개발자
	</label>
	<br/>
	2. 가장 많이 사용하는 개발도구 <br/>
	<label>
		<input type="radio" name="answerList[1]" value="Eclipse">Eclipse
	</label>
	<label>
		<input type="radio" name="answerList[1]" value="IntelliJ">IntelliJ
	</label>
		<label>
		<input type="radio" name="answerList[1]" value="Sublime">Sublime
	</label>
	<br/>
	3. 하고 싶은 말<br/>
	<input type="text" name="answerList[2]">
	<br/>
	<label>
	응답자 위치 : <input type="text" name="surveyUserInfo.location">
	</label>
	<br/>
	<label>
	응답자 나이 : <input type="text" name="surveyUserInfo.age">
	</label>
	<input type="submit" value="전송">
	</form> -->
	

	<!-- form 태그를 기존 하드코딩의 방식에서 컨트롤러로부터 모델을 받는 구조로 변경 -->
	
	<form method="post">
		<c:forEach var="question" items="${ questionList }" varStatus="status">
			<p>
				${ status.index + 1 }. ${question.questionTitle }
				<c:if test="${ question.choice }">
					<c:forEach var="options" items="${question.questionOptions }">
						<label>
							<input type="radio" name="answerList[${status.index }]" value="${options }">
							${ options }
						</label>
					</c:forEach>
				</c:if>
				<c:if test="${ !question.choice }">
					<input type="text" name="answerList[${status.index }]">
				</c:if>
			</p>
		</c:forEach>
		<p>
			<label>응답자 위치 : <br/>
				<input type="text" name="surveyUserInfo.location">
			</label>
		</p>
		<p>
			<label>응답자 나이 : <br/>
				<input type="text" name="surveyUserInfo.age">
			</label>
		</p>
		<input type="submit" value="전송">
	</form>
</body>
</html>