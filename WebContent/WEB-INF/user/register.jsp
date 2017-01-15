<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
	<div id="login">
		<h1>Register</h1>
		<form action="${base}userController/addUser" method="post" >
			<table border="0">
				<tr>
					<td colspan="2"><input type="text"  required="required"
						placeholder="用户名" name="name"></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" required="required"
						placeholder="密码" name="password"></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" required="required"
						placeholder="确认密码" name="confirmPassword"></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" required="required"
						placeholder="邮箱" name="mail"></input></td>
				</tr>
				<tr>
					<td><button class="but" type="submit">注册</button></td>
					<td><a href="${base}">登陆</a></td>
				</tr>
			</table>
		</form>
		<span>${msg}</span>
	</div>
</body>
</html>