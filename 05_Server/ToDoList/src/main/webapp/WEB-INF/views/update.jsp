<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>To do 수정하기</title>	
</head>
<body>
	<main>
		<h1>To do 수정하기</h1>
		
		<form action="/update" method="post">
			<p>제목</p>
			<input type="text" name="title" value="${ todo.todoTitle }" required>
			
			<p>메모</p>
			<textarea name="memo" style="resize:none; font-size:18px" rows="5" cols="20">${ todo.todoMemo }</textarea>
			
			<br>
			<button class="insert-btn">수정하기</button>
			
			<input type="hidden" name="todoNo" value="${ todo.todoNo }">
			
		</form>
		
		
	</main>
</body>
</html>