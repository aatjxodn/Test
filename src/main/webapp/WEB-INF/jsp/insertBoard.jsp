<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>

<style>
	body {
	background-color: #f8f8f8;
}
</style>
<body>
	<form id="form_test" action="insertBoard.do" method="post" encType="multipart/form-data">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th>제목</th>
					<td><input type="text" placeholder="제목을 입력하세요." name="testTitle" class="form-control" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea placeholder="내용을 입력하세요 ." name="testContent" class="form-control" style="height: 200px;"></textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="uploadFile"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="등록">
						<input type="button" value="이전" onclick="javascript:history.back()">
				</tr>

			</tbody>
		</table>
		<input type="hidden" value="${user.id }" name="id">
	</form>

</body>
</html>