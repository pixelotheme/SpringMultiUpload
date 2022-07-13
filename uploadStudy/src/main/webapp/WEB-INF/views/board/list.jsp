<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}

</style>

<script src="https://code.jquery.com/jquery-3.5.0.js"></script>

<script type="text/javascript">

	$(function(){

		$(".dataRow").click(function(){
			location="view.do?no=10";

			});
		});
</script>

<title>일반게시판 > 리스트</title>
</head>
<body>
<h2>일반게시판 > 리스트</h2>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
<!-- 	<tr class="dataRow" onclick="location='view.do?no=10'"> -->
	<c:forEach items="${list }" var="vo" >
	
	<tr class="dataRow">
		<td>${vo.no }</td>
		<td>${vo.title }</td>
		<td>${vo.writer }</td>
		<td>${vo.writeDate }</td>
		<td>${vo.hit }</td>
	</tr>
	</c:forEach>
</table>
<br>
<a href="write.do">글쓰기</a>
</body>
</html>