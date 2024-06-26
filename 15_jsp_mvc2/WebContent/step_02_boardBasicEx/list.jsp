<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bList</title>
</head>
<body>

	<h1>게시글 보기</h1>
	<table border="1">
		<tr align="center">
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="board" items="${boardList}"> 
			<tr align="center">
				<td>${board.num}</td>
				<td><a href="bInfo?num=${board.num}">${board.subject}</a></td>
				<td>${board.writer}</td>
				<td>${board.regDate}</td>
				<td>${board.readCount}</td>
			</tr>
		</c:forEach>
		<tr align="right">
			<td colspan="5">
				<input type="button" style="float: right" value="글쓰기" onclick="location.href='bWrite'">
			</td>
		</tr>
	</table>
	
</html>