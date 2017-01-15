<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>登陆</title>
<script type="text/javascript">
	$(function() {

		$("#DL").click(function() {
			var name=$("#name").val();
			var password=$("#password").val();
			if(name==""||password==""){
				$("#msg").text("请输入用户名和密码！");	
				return ;
			}
			submit();
		});

	});
	function submit() {

		$.ajax({
			cache : true,
			type : "POST",
			url : "${base}goLogin",
			data : $('#loginForm').serialize(),// 你的formid
			async : false,
			success : function(data) {
				if(data==0){
					$("#msg").text("该用户不存在！");
				}else if(data==1){
					window.location.href="${base}bookController/books";
				}else if(data==2){
					$("#msg").text("账户或密码错误！");
				}else{
					$("#msg").text("未知错误！");
				}
			}
		});

	}
</script>

</head>
<body>
	<div id="login">
		<h1>Login</h1>
		<form method="post" id="loginForm">
			<table border="0">
				<tr>
					<td colspan="2"><input type="text" required="required"
						placeholder="用户名" id="name" name="name" data-rule="required;true"></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" required="required"
						placeholder="密码" id="password" name="password" data-rule="required;true"></input>
					</td>
				</tr>
				<tr>
					<td><button class="but" id="DL" type="button">登录</button></td>
					<td><a href="${base}userController/register">注册</a></td>
				</tr>
			</table>
		</form>
		<span > <font id="msg" color="red">${msg}</font>
		</span>
	</div>
</body>
</html>