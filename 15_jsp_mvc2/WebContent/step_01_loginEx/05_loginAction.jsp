<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05_loginAction</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.id eq null }">
			<script>
				alert("아이디와 비밀번호를 확인하세요.");
				history.go(-1);
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("로그인되었습니다.");
				location.href = "main";
			</script>
		</c:otherwise>
	</c:choose>

</body>
</html>