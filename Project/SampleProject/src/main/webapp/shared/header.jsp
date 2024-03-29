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
<header>
	<div >
		<a href="/">
			PJ Shop
		</a>
		<div>
			<c:choose>
				<c:when test="${ empty sessionScope.loginMember}">
					<a href="/login">login</a>
				</c:when>
				<c:otherwise>
					<div>
                    	잔액:
               		</div> 
               		<div>${ sessionScope.loginMember.memberAsset }원</div>
					<a href="/profile">${ sessionScope.loginMember.memberName }</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>
<div style="height: 100px; width: 100%"></div>
</body>
</html>