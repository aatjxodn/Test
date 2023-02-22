<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<style>
* {
	margin: 0px;
	padding: 0px;
	list-style: none;
	text-decoration: none;
	color: black;
	border-collapse: collapse;
}

#layout1 {
	width: 100%;
}

#container1 {
	width: 350px;
	margin: auto;
}

#container1 table {
	margin: auto;
	margin-top: 300px;
}
</style>
<script>
	function GosignUp(a) {
		var reservationIdx = a;

		var result = confirm('회원가입 하시겠습니까?');
		if (result == true) {
			alert('회원가입 화면으로 이동합니다.');

			location.href = 'goSignUp.do';
		}
	}
</script>
</head>
<body>
	<div id="layout1">
		<div id="container1">
			<form name="loginForm" action="login.do" method="post">
				<table style="display: block; padding: 30px; border: 1px solid black; border-radius: 10px;">
					<tr>
						<th>ID</th>
						<th><input type="text" name="id" placeholder="아이디"></th>
					</tr>
					<tr>
						<th>Password</th>
						<th><input type="password" value="비밀번호" name="password"
							id="password"></th>
					</tr>
					<tr>
						<th style="padding: 10px;"><input type="button" value="회원가입"
							onclick="GosignUp(this.form)" id="signUp"
							style="padding: 5px 10px;"></th>
						<th style="padding: 10px;"><input type="submit" value="로그인"
							style="padding: 5px 40px;"></th>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>