<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 > 글수정</title>
</head>
<body>
<h2>일반게시판 > 글수정</h2>
<form action="update.do" method="post">
<table>
<!-- 	<tr> -->
<!-- 		<th>번호</th> -->
<!-- 		<td><input name="no" readonly="readonly"/></td> -->
<!-- 	</tr> -->
	<tr>
		<th>제목</th>
		<td><input name="title" /></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="5" style="width:600px;" name="content"></textarea></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><input name="writer" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<th>작성일</th> -->
<!-- 		<td><input name="writeDate" readonly="readonly" /></td> -->
<!-- 	</tr> -->
	<tr>
		<td colspan="2"><button>수정</button> </td>
	</tr>
</table>
</form>
</body>
</html>