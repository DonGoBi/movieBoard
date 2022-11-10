<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영화삭제</title>
	<link rel="stylesheet" type="text/css" href="css/movie.css">

</head>
<body>
	<div id="wrap">
		<h2 style="color:green">영화 삭제 </h2>	
		
		<form method="post" enctype="multipart/form-data" name="frm">
		
			<input type="hidden" name="code" value="${mVo.code}">
			<table>
				<tr>
					<td style="width:20%;">
						<c:choose>
							<c:when test="${empty mVo.poster }">
								<img src="images/noimage.gif" style="width:200px;">
							</c:when>
							
							<c:otherwise>
								<img src="images/${mVo.poster}" style="width:200px;">
							</c:otherwise>
						</c:choose>
						
					</td>
					
					<td>	
						<table>
							<tr>
								<th style="width:100px;">영화제목</th>
								<td>${mVo.title}</td>
							</tr>
							
							<tr>
								<th>가 격</th>
								<td>${mVo.price}원</td>
							</tr>
							
							<tr>
								<th>감 독</th>
								<td>${mVo.director}</td>
							</tr>
							
							<tr>
								<th>배 우</th>
								<td>${mVo.actor}</td>
							</tr>
							
							<tr>
								<th>설명</th>
								<td><div style="height:200px; width:100%">${mVo.synopsis}</div></td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
			

			<div style="text-align:center">
				<input type="submit" value="삭제" onclick="return movieCheck()">
				<input type="button" value="목록" onclick="location.href='movieList.do'">
			</div>
		</form>
	</div>	
</body>
</html>