<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/shared/header.jsp"></jsp:include>
	<main>
	<form action="/updateProfile" method="post">
		아이디: <input name="inputId" value="${ sessionScope.loginMember.memberId }"> <br>
		이름: <input name="inputName" value="${ sessionScope.loginMember.memberName }"> <br>
		
		<button>정보 수정</button>
	</form>
	</main>
	<jsp:include page="/shared/footer.jsp"></jsp:include>
</body>
</html>