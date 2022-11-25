<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>

	<%--
	
		이클립스에서 Servers폴더에 있는 Context.xml파일에 아래의 설정 추가 
	
		<Resource 
			auth="Container" 
			driverClassName="com.mysql.cj.jdbc.Driver"
			loginTimeout="10" 
			maxWait="5000" 
			name="jdbc/pool2" 
			username="root"
			password="1234" 
			type="javax.sql.DataSource"
			url="jdbc:mysql://localhost:3306/MVC2_BOARD_BASIC_EX?serverTimezone=UTC"
		/> 
	
	 --%>

	<img src="img/jsp.PNG" alt="jsp심볼" width="800" height="500"><br><br><br><br>
	<input type="button" value="게시판 보기" onclick="location.href='bList'">
	
</body>
</html>