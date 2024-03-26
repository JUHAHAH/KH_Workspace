<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- c == core (if, for문 등) --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- fn == functions (길이, 자르기, 나누기) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
	taglib : 태그 라이브러리를 부르는 말
	prefix : 태그명 앞에 작성되는 접두사 "c" = core / "fn" = function
									<c:remove>, <c:if>, <c:forEach>, <c:set>...
	uri(uniform resource identifier) : 통합 자원 식별자
									-> 자원을 식별하는 고유 문자열
									url(uniform resource Location)와 헷갈리기 쉬움
	
	
--%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL(JSP Standard Tag Library)</title>
</head>
<body>
	<h1>JSTL(JSP 표준 태그 라이브러리)</h1>
	<pre>
	JSP에서 자주 사용되거나 공통적으로 사용되는 Java 코드(if, for, 변수 선언, 형 변환)
	스크립틀릿 쓰면 너우 어렵다!
	대신 html 태그 최적화 가능
	</pre>
	
	<h3>JSTL 사용방법</h3>
	
	<ol>
		<li>/webapp/WEB-INF/lib 폴더에 JSTLT 라이브러리(.jar) 추가</li>
	
		<li>
		JSTL 사용하고자하는 jsp 파일 상단에
		taglib JSP 지시자 태그를 추가
		</li>
	</ol>
	
	<hr>
	
	<h1>1. 변수 선언 (c:set 태그)</h1>
	<pre>
		- 변수 선언을 위한 태그
		- c:set 에 작성 가능한 속성
		1) var : 변수명(속성의 key 역할)
		2) value : 대입할 값(속성의 value)
		3) scope : page, request, session, application (DEFAULT = page)
	</pre>
	
	<% 
		// 스크립틀릿으로 page scope에 속성 세팅하는 법
		pageContext.setAttribute("num1", 10);
	%>
	
	<%-- JSTL로 속성 세팅하는 법 --%>
	<c:set var="num2" value="20" scope="page"/>
	
	num1: ${ num1 }
	
	<br>
	
	num2: ${ num2 }
	
	<h1>2. 변수 제거 (c: remove)</h1>

	<pre>
		- 변수 제거 : 내장 객체에 세팅된 속성을 제거

		(jsp 방법 : removeAttribute("num1"))


		- c: remove 속성

		1) var : 삭제할 변수명

		2) scope : 내장 객체 범위 (기본값 : 모든 scope)

	</pre>

	<%
		pageContext.removeAttribute("num1");
	%>

	num1 제거 확인 : ${num1}
	
	<br>
	<c:remove var ="num2"/>
	
	num2 제거 확인 : ${num2}

	<hr>
	
	<h1>3. 변수 출력(c: out 태그)</h1>
	
	<pre>
		\${ key }와 비슷하다
		
		- 단, escapeXml = "true" (DEFAULT) 설정 시 html 태그가 해석 X
		
		- escapeXml = "false"
	</pre>
	
	<c:set var="temp" value="<h1>배부르네요</h1>"/>
	
	html 태그 해석 X: <c:out value="${ temp }" />
	<br>
	html 태그 해석 O: <c:out value="${ temp }" escapeXml="false" />
	
	<hr><hr><hr>
	
	<!-- 
	상대경로: 현재 위치가 중요함
			현재경로: http://localhost:8080/jstl/main
			이동경로: http://localhost:8080/jstl/condition
	-->	
	<a href="condition">JSTL을 이용한 조건문(if / choose, when, otherwise)</a>
	<!-- /jstl/condition 이라는 요청 주소를 처리할 Controller가 필요 : JSTLConditionController -->
	
	<br>
	
	<!-- 
	상대경로로 작성
	현재 경로: http://localhost:8080/jstl/main
	목표 경로: http://localhost:8080/jstl/loop
	-->
	<a href="loop">JSTL을 이용한 반복문</a>
	<%-- /jstl/loop 요청 처리용 Controller 필요: JSTLLoopController --%>
	
	
	<hr><hr><hr>
	
	<%-- 현재경로: localhost:8080/jstl/main --%>
	<%-- 목표경로: localhost:8080/jstl/student/selectAll --%>
	<a href="student/selectAll">학생전체 조회하기</a>
	<%-- student/selectAll --%>
	
	<!-- -------------------------------------- -->
	
	
	
	<!-- 
	상대경로로 작성
	현재 경로: http://localhost:8080/jstl/main
	목표 경로: http://localhost:8080/jstl/student/selectArch
	-->
	<a href="student/selectArch">workbook에서 건축학과만 조회</a> <!-- 건축학과만 조회 -->
	
	<!-- 
	상대경로로 작성
	현재 경로: http://localhost:8080/jstl/main
	목표 경로: http://localhost:8080/jstl/student/selectOne
	-->
	<form action="/student/selectOne" method="post">
		<input type="text" name="deptName"> <!-- 작성된학과만 조회 -->
		<button>찾기</button>
	</form>
	<%-- SelectOneController --%>
	



	
	

</body>
</html>


























