<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
<h1>El(Expression Language)</h1>
<pre>
	JSP 의표현식을 조금 더 간단하게 작성할 수 있도록 고안된 언어
	(JSP에 내장)
	
	화면에 출력하고자 하는 자바 코드르르 \${ key } 형식으로 작성하면 해당 key에 알맞은 value가 표현됨
</pre>
<h3>EL의 특징 1번: get이라는 단어를 사용하지 않음</h3>
<pre>
EL은 자바코드를 얻어와서 출력하는 전용 언어
-> 출력 전용 언어
-> get 밖에 남지 않으니 생략
</pre>

테스트1 (JSP 표현식) 	: <%= request.getParameter("test") %>
<br>
테스트2 (EL) 			: ${ param.test }
<hr>
<h3>EL의 특징 2번: null, NullPointerException을 빈칸 처리</h3>
테스트1 (JSP 표현식) 	: <%= request.getParameter("num") %>
<br>
테스트2 (EL) 			: ${ param.num }
<hr>
<form action="/elTest" method="post">
이름: <input name="inputName">
<br>
나이: <input name="inputAge">
a <input type="checkbox" name="opt" value="a">
b <input type="checkbox" name="opt" value="b">
c <input type="checkbox" name="opt" value="c">

<button>제출</button>

</form>



</body>
</html>




















