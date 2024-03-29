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
	<main>
	<form class="login" action="/login" method="post">
		<div>
			<p>아이디</p>
			<input type="text" name="inputId">
		</div>
		<div>
			<p>패스워드</p>
			<input type="password" name="inputPw">
		</div>
		<button>로그인</button>
	</form>
	<a href="/signup">회원가입</a>
	</main>
	<jsp:include page="/shared/footer.jsp"></jsp:include>
</body>
</html>