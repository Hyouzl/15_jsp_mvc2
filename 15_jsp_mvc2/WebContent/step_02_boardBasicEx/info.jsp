<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bInfo</title>
</head>
<body>

	<h1>게시글 보기</h1>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>${boardDto.num}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${boardDto.readCount}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${boardDto.writer}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${boardDto.regDate}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${boardDto.email}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${boardDto.subject}</td>
		</tr>
		<tr>
			<td>글 내용</td>
			<td>${boardDto.content}</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정하기" onclick="location.href='bUpdate?num=${boardDto.num}'">
				<input type="button" value="삭제하기" onclick="location.href='bDelete?num=${boardDto.num}'">
				<input type="button" value="목록보기" onclick="location.href='bList'">
			</td>
		</tr>
	</table>
	
</body>
</html>