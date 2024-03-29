<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<jsp:include page="/shared/header.jsp"></jsp:include>
	<main>
	
	<div class="profile">
		<p>아이디: ${ sessionScope.loginMember.memberId }</p>
		<p>이름: ${ sessionScope.loginMember.memberName }</p>
		<p>잔액: ${ sessionScope.loginMember.memberAsset }</p>
		<br>
		
		<a href="/updateProfile">나의 정보 수정</a>
		<a href="/delete">회원 탈퇴</a>
		<a href="/logout">logout</a>
	</div>
	
	</main>
	<jsp:include page="/shared/footer.jsp"></jsp:include>
</div>
</body>
</html>