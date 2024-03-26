<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>학생 목록</h1>
	
	<table border="1">
		<c:forEach items="${stdList}" var="std" varStatus="vs">
			<tr>
				<th>${vs.count}</th>
				<th>${std.studentNo}</th>
				<th>${std.studentName}</th>
				<th>${std.departmentName}</th>
				<th>${std.studentAddress}</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>