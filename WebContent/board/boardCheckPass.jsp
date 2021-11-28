<%@ page  contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="../menu/top.jsp" %>

<link rel="stylesheet" href="css/shopping.css">
<script type="text/javascript" src="script/board.js" charset="UTF-8"></script>
</head>
<body>
	<div align="center">
		<h3>비밀번호 확인</h3>
		<form action="BoardServlet" class="form" name="frm" method="get">
			<input type="hidden" name="command" value="board_check_pass">
			<input type="hidden" name="num" value="${param.num}">
			
			<table class="table table-borderless mt-3">
				<tr>
					<th class="col-3 pt-3 pl-5 pr-0">비밀번호</th>
					<td class="col-6"><input type="password" class="form-control" name="pass" size="20"></td>
					<td class="col-3">
						<input type="submit" class="btn btn-primary" value="확 인 "
							onclick="return passCheck()">
					</td>
				</tr>
			</table>
			<br>${message}
		</form>
	</div>
</body>
</html>