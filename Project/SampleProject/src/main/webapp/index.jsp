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
	<jsp:include page="/shared/header.jsp"></jsp:include>
	<main>
		<c:choose>
			<c:when test="${ empty sessionScope.loginMember}">
				먼저 로그인을 해주세요
			</c:when>
			<c:otherwise>
				<div class="game">
					<jsp:include page="/shared/clicker.jsp"></jsp:include>
					<jsp:include page="/WEB-INF/views/items.jsp"></jsp:include>
				</div>
			</c:otherwise>
	</c:choose>
	</main>
	
	<c:if test="${ not empty sessionScope.message }">
		<script type="text/javascript">
			alert('${message}');
		</script>
	</c:if>
	
	<c:remove var="message" scope="session"/>
	
	<jsp:include page="/shared/footer.jsp"></jsp:include>
</body>
</html>