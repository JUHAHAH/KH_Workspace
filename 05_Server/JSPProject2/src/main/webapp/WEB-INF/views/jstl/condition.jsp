<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL 조건문</title>
</head>
<body>
	<h1>4. 조건문 -if (c:if 태그)</h1>
	
	<pre>
		- 단독 if문 (c:else 없음!)
		- 속성은 test에만 존재
		
		** 주의사항 **
		1) test의 속성값은 무조건 EL 구문으로 작성
		2) test의 속성값은 true, false 가 나오는 조건식이어야 함
		3) test의 속성값을 작성하는 "" 내부에는 앞뒤 공백 존재 X
		
	</pre>
	
	<c:if test="${ money == 50000 }"> <!-- if 조건 참일경우 아래의 태그 표시 -->
		<h3 style="color:gold">돈이 50000원 있습니다</h3>
	</c:if>
	
	<h3>EL에서 모든 비교는 '==' 혹은 'eq' / '!=' 혹은 'ne'</h3>
	<h3>EL에서 문자열은 ''</h3>
	
	<c:if test="${ name eq '홍길동' }">
		<h3>이름이 일치합니다</h3>
	</c:if>
	
	<c:if test="${ name ne '홍길동' }">
		<h3>이름이 일치하지 않습니다</h3>
	</c:if>
	
	<hr>
	
	<h1>5. 조건문 - choose, when, otherwise (if ~else, if ~else)</h1>
	<pre>
		choose : when, otherwise 태그를 감싸는 태그
				(if ~else, if ~else 를 쓰겠다 선언)
				
		when : if, else if (역할의 태그)
				속성은 test밖에 없다
				
		otherwise : else 역할, 속성 X
		
		<%-- 
			lt
			
			gt
			
			le : less or equal
			
			ge : greater or equal
		
		 --%>
	</pre>
	
	<c:choose>
	<%-- 
		주석 다는 법 
		반드시 jsp용 주석 사용!
	 --%>
		<c:when test="${param.val gt 100}"> 
		</c:when> 
		
		<c:when test="${ param.val < 100}"> 
			100 미만
		</c:when>
		
		<c:otherwise>
			100과 같다
		</c:otherwise>
	</c:choose>

</body>
</html>
























