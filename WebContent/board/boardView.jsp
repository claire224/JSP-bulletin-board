<%@ page  contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="../menu/top.jsp" %>

<script type="text/javascript" src="script/board.js" charset="UTF-8"></script>

</head>
<body>
	<div id="wrap" class="container mx-auto mt-5" style="width:700px" align="center">
		<h1>게시글 상세보기</h1>
		<table class="table mt-5">
			<tr>
				<th>작성자</th>
				<td>${board.name}</td>
				<th>이메일</th>
				<td>${board.email}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.writedate}" /></td>
				<th>조회수</th>
				<td>${board.readcount }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${board.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><pre>${board.content}</pre></td>
			</tr>
		</table>
		<br> <br> 
		
		<!-- 이전 페이지 이동 : 추가 -->
		<input type="button" class="btn btn-primary mr-3" value="이전 페이지"
			onclick="history.go(-1)">
		<!-- onclick="history.back()"> -->
		<input type="button" class="btn btn-primary mr-3" value="게시글 수정"
			onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'update')">
		<input type="button" class="btn btn-primary mr-3" value="게시글 삭제"
			onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'delete')">
		<input type="button" class="btn btn-primary mr-3" value="게시글 리스트"
			onclick="location.href='BoardServlet?command=board_list'"> 
		<input type="button" class="btn btn-primary mr-3" value="게시글 등록"
			onclick="location.href='BoardServlet?command=board_write_form'">
	</div>
</body>
</html>