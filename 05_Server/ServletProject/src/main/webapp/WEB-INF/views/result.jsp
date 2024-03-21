<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
	JSP용 주석 
		<%@ %> : 지시자 태그 (JSP 페이지의 전반적 속성을 설정하는데 사용하는 태그)
		
		JSP (Java Server PAge) : Java 코드가 들어있는 HTML 코드 (자바 코드 사용 가능)
		<% %> : 스크립틀릿 -> 자바코드 작성 가능한 태그
		<%= %> : 표현식 -> 자바, 서버에서 받아온 값을 표현/출력 시 사용하는 태그
		
				
	--%>
	
	<h3 style="color: hotpink"><%= request.getParameter("inputName") %>님의 가입을 환영합니다</h3>

    <ul>
      <li>id: <%= request.getParameter("inputId") %></li>
      <li>pw: <%= request.getParameter("inputPw") %></li>
    </ul>

	<%if(!request.getParameter("intro").equals("")) { %>
    	<h4>자기소개</h4>
    	<p><%= request.getParame  ter("intro") %></p>
    <% } %>
    
</body>
</html>