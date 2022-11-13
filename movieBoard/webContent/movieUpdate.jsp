<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영화 수정</title>
	<link rel="stylesheet" type="text/css" href="css/movie.css">
	<script src="script/movie.js"></script>
</head>
<body>
	<div id="wrap">
		<h2 style="color:green">영화 수정</h2>	
		
		<form method="post" enctype="multipart/form-data" name="frm">
		
			<input type="hidden" name="code" value="${movie.code}">
			<input type="hidden" name="nomakeImg" value="${movie.poster}">
		
			<table>
				<tr>
					<td>
						<c:choose>
							<c:when test="${empty movie.poster }">
								<img src="images/noimage.gif" style="width:200px;">
							</c:when>
							
							<c:otherwise>
								<img src="images/${movie.poster}" style="width:200px;">
							</c:otherwise>
						</c:choose>
						
					</td>
					
					<td>	
						<table>
							<tr>
								<th>제목</th>
								<td><input type="text" name="title" size="80" value="${movie.title}"></td>
							</tr>
							
							<tr>
								<th>가 격</th>
								<td><input type="text" name="price" size="10" value="${movie.price}"> 원</td>
							</tr>
							
							<tr>
								<th>감 독</th>
								<td><input type="text" name="director" size="10" value="${movie.director}"></td>
							</tr>
							
							<tr>
								<th>배 우</th>
								<td><input type="text" name="actor" size="30" value="${movie.actor}"></td>
							</tr>
							
							<tr>
								<th>시놉시스</th>
								<td ><textarea cols="80" rows="10" name="synopsis">${movie.synopsis}</textarea></td>
							</tr>
							
							<tr>
								<th>사 진</th>
								<td><input type="file" name="poster"></td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
			
			
			<div style="text-align:center">
				<input type="submit" value="등록" onclick="return movieCheck()">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" onclick="location.href='movieList.do'">
			</div>
		</form>
	</div>	
</body>
</html>