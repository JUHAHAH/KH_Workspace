<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>건축학과만 찾기</title>
</head>
<body>
	<h1>건축학과 학생 목록</h1>
		
	<table border="1">
		<c:forEach items="${archList}" var="arch" varStatus="vs">
			<tr>
				<th>${vs.count}</th>
				<th>${arch.studentNo}</th>
				<th>${arch.studentName}</th>
				<th>${arch.departmentName}</th>
				<th>${arch.studentAddress}</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>