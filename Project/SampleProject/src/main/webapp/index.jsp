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
			
		<jsp:include page="/WEB-INF/views/items.jsp"></jsp:include>
<script type="module" src="https://unpkg.com/@splinetool/viewer@1.0.86/build/spline-viewer.js"></script>
<spline-viewer url="https://prod.spline.design/ni0gfvSDdrB8oVus/scene.splinecode"></spline-viewer>
	</main>
	<jsp:include page="/shared/footer.jsp"></jsp:include>
</body>
</html>