<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02_join</title>
</head>
<body>

	<div align="center">
		<h1>회원 가입</h1>
		<hr>
		<form action="join" method="post" >
			<h4>로그인 정보</h4>
			<label for="id">아이디 : </label><input type="text" id="id" name="id" autofocus>
			<br><br>
			<label for="pw">패스워드 : </label><input type="password" id="pw" name="pw">
			<br>
			<h4>개인 정보</h4>
			<label for="name">이름 : </label><input type="text" id="name" name="name" placeholder="공백없이 입력하세요">
			<br><br>
			<label for="contact">연락처 : </label><input type="text" id="tel" name="tel" size="20" placeholder="000-0000-0000"> 
			<br><br>
			<label for="email">이메일 : </label><input type="email" id="email" name="email">
			<br><br><br>
			<div>
				<input type="submit" value="회원가입">
				<input type="reset" value="다시 쓰기">
			</div>
		</form>		
	</div>
	
</body>
</html>