<%@ page  contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="../menu/top.jsp" %>

<!-- <link rel="stylesheet" type="text/css" href="css/shopping.css"> -->
<script type="text/javascript" src="script/board.js" charset="UTF-8"></script>
</head>
<body>
	<div id="wrap" class="container mx-auto" align="center" style="width:700px">
	
		<h1>게시글 등록</h1>
		
		<form name="frm" class="form" method="post" action="BoardServlet">
			
			<input type="hidden" name="command" value="board_write">
			
			<table>
				<tr>
					<th class="col-3">작성자 <span style="color:red">(* 필수)</span></th>
					<td><input type="text" class="form-control my-2" name="name"> </td>
				</tr>
				<tr>
					<th class="col-3">비밀번호 <span style="color:red">(* 필수)</span></th>
					<td><input type="password" class="form-control my-2" name="pass">
					(게시물 수정 삭제시 필요합니다.)</td>
				</tr>
				<tr>
					<th class="col-3">이메일</th>
					<td><input type="text" class="form-control my-2" name="email"></td>
				</tr>
				<tr>
					<th class="col-3">제목<span style="color:red">(* 필수)</span></th>
					<td><input type="text" class="form-control my-2" size="70" name="title"></td>
				</tr>
				<tr>
					<th class="col-3">내용</th>
					<td><textarea class="form-control my-2" style="resize:none" 
						cols="70" rows="8" name="content"></textarea></td>
				</tr>
			</table>
			<br>
			<br> 
				<input type="submit" class="btn btn-primary mr-2" value="등록"
					onclick="return boardCheck()"> 
				<input type="reset" class="btn btn-primary mr-2" value="다시 작성"> 
				<input type="button" value="목록" class="btn btn-primary"
					onclick="location.href='BoardServlet?command=board_list'">
		</form>
	</div>
</body>
</html>