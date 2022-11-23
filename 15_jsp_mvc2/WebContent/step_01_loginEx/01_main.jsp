<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--

	# MVC2 데이터베이스 연동예시


	1. 해당 프로젝트 > WebContent > WEB-INF > lib폴더에 아래의 jar파일 추가
		
		commons-collections4-4.1.jar
		commons-dbcp2-2.2.0.jar
		commons-pool2-2.5.0.jar
		jstl-1.2.jar
		mysql-connector-java-8.0.15.jar
		
	
	2. 이클립스에서 Servers폴더에 있는 Context.xml파일에 아래의 내용 추가 
	
		<Resource 
			auth="Container" 
			driverClassName="com.mysql.cj.jdbc.Driver"
			loginTimeout="10" 
			maxWait="5000" 
			name="jdbc/pool1" 
			username="root"
			password="1234" 
			type="javax.sql.DataSource"
			url="jdbc:mysql://호스트명:포트번호/데이터베이스명?serverTimezone=UTC"
		/> 
 
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>
	<div align="center"> 
		<c:choose>
			<c:when test="${sessionScope.id eq null }">
				<p><a href="join">회원가입</a></p>
				<p>로그인</p>
			</c:when>
			<c:otherwise>
				<p>입사지원정보수정</p>
				<p>로그아웃</p>
				<p>회원탈퇴</p>
			</c:otherwise>
		</c:choose>
	</div>
	<hr><br><br><br>
	
	<div align="center">
	<!-- WebContent 하위부터 이미지 경로를 작성한다. -->
		<img src="img/applyonline.png" alt="입사지원하기">
	</div>
	
	

</body>
</html>


