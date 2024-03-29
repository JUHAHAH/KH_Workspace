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
<div class="items">
	<c:forEach var="item" items="${ itemList }">
		<form class="itemFrame" action="/purchase" method="post">
			<img class="itemImage" alt="${ item.itemName }" src="${ item.itemImage }">
			<div style="font-weight: 700;">${ item.itemName }</div>
			<div>${ item.itemPrice }</div>
			<input type="hidden" name="itemNo" value="${ item.itemNo }">
			<input type="hidden" name="itemPrice" value="${ item.itemPrice }">
			<button>구매</button>
		</form>
	</c:forEach>
</div>
</body>
</html>