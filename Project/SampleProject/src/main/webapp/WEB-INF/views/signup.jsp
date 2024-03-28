<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/main.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/shared/header.jsp"></jsp:include>
	
	<form action="/signup" method="post">
		<div>
			<p>아이디</p>
			<input type="text" name="inputId" required>
		</div>
		<div>
			<p>이름</p>
			<input type="text" name="inputName" required>
		</div>
		<div>
			<p>패스워드</p>
			<input type="password" name="inputPw" required>
		</div>
		<button>회원가입</button>
	</form>
	
	<jsp:include page="/shared/footer.jsp"></jsp:include>
</body>
</html>