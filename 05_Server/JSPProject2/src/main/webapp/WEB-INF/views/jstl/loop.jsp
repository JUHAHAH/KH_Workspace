<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL 반복문</title>
</head>
<body>
	<h1>6. 반복문 - for (c:forEach)</h1>
	
	<pre>
		- 일반 for + 추가 기능
		
		- 속성
		1) var : 현재 반복횟수에 해당하는 변수 (int i)
		2) begin : 반복시 var의 시작 값
		3) end : 반복이 종료된 var 값
		4) step : 반복시마다 var의 증가 값
		5) items : 반복 접근할 객체
		6) varStatus : 현재 반복상태
		
		관련된 정보를 제공하는 변수 선언
			varStatus="변수명"
			-current : 현재 반복 횟수, 혹은 반복 접근중인 객체
			-index : 현재 인덱스 값(0부터 시작)
			-count : 현재 반복횟수
			-first : 첫번째 반복인가? t/f
			-last : 마지막 반복인가? t/f
	</pre>
	
	<hr>
	
	<h3>일반 for문 형식으로 사용하기</h3>
	
	<c:forEach var="i" begin="1" end="6" step="1"> <%-- for(i=1; i<=6; i++) --%>
		<h${i}>현재 i 값: ${i}</h${i}>
	</c:forEach>
	
	<hr>
	
	<h3>향상된 for문 사용해보기</h3>
	
	<c:forEach items="${bookList}" varStatus="vs">
		<p>
			index: ${vs.index} <br>
			count: ${vs.count} <br>
			current: ${vs.current} <br>
		</p>
		<hr>
	
	</c:forEach>
	
	<hr>
	
	
	
</body>
</html>































