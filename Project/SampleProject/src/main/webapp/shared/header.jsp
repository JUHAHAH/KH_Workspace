<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/main.css" rel="stylesheet">
</head>
<body>
<header style="position: top; position: fixed; width: 100%;">
	<div style="display: flex; justify-content: space-between;">
		<a href="/" style="display: flex;">
			PJ Shop
		</a>
		<div style="display: flex; padding-right: 20px">
			<c:choose>
				<c:when test="${ empty sessionScope.loginMember}">
					<a href="/login">login</a>
				</c:when>
				<c:otherwise>
					<a href="/profile">${ sessionScope.loginMember.memberName }</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>
<div style="height: 30px; width: 100%"></div>
</body>
</html>