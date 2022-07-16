<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.WebStudy.web.entity.Notice" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 공부</title>
</head>
<body>
	<style>
	.notice{
		border-collapse: collapse;
		border: 2px solid black;
		background: pink;
	}
	.notice > thead > tr> th{
		border: 2px solid gray;
	}
	.notice > tbody > tr > td{
		border: 2px solid gray;
	}
	</style>
	<div>
	<h2>"게시판"</h2>
		<table class="notice">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="n" items="${list }">
				<tr>
					<td>${n.id }</td>
					<td class="title indent text-align-left"><a href="detail?id=${n.id }">${n.title }</a></td>
					<td>${n.writerId }</td>
					<td>${n.regdate }</td>
					<td>${n.hit }</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
</body>
</html>