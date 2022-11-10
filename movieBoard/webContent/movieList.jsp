<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영화 리스트</title>
	<link rel="stylesheet" type="text/css" href="css/movie.css">
</head>
<body>
	<div id="wrap" class="listWrap">
		<h2>영화 리스트</h2>
		<table>
			<tr>
				<td colspan="6" style="text-align:right">
					<a href="movieWrite.do">영화 등록</a>
				</td>
				
			</tr>
			
			<tr>
				<th>영화 제목</th>
				<th>감독</th>
				<th>출연배우</th>
				<th>가격</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			
			<c:forEach var="movie" items="${movieList}">
				<tr>
					<td>${movie.title}</td>
					<td>${movie.director}</td>
					<td>${movie.actor}</td>
					<td>${movie.price}</td>
					<td><a href="movieUpdate.do?code=${movie.code}"> 수정</a></td>
					<td><a href="movieDelete.do?code=${movie.code}"> 삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>