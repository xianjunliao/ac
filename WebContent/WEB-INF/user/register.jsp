<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {

		$("#reg").click(function() {
			var name=$("#name").val();
			var password=$("#password").val();
			var confirmPassword=$("#confirmPassword").val();
				   $("#msg").css("color","red"); 
			if(name==""||password==""){
				$("#msg").text("请输入用户名和密码！");	
				return ;
			}if(confirmPassword!=password){
				$("#msg").text("两次输入的密码不一样！");	
				return ;
			}
			submit();
		});
		$("#name").keyup( function() {
			passwordValidate();
			isExist();
		
		});
		$("#confirmPassword").keyup( function() {
			passwordValidate();
		});
		$("#password").keyup( function() {
			passwordValidate();
		});
		
	});
	
	function passwordValidate(){
		var name=$("#name").val();
		var password=$("#password").val();
		var confirmPassword=$("#confirmPassword").val();
        $("#msg").css("color","red");
        if((confirmPassword!=null&&password!=null)||(confirmPassword!=""&&password!="")){
           if(password!=confirmPassword){
			$("#msg").text("两次输入的密码不一样！");	
		
		}
           else if(password==confirmPassword&&(name==null||name=="")){
        	   
        	   $("#msg").text("请输入用户名！");	
           }
           else {
         	   $("#msg").text("");	
            }
        } 
	}
	function isExist(){
		var name=$("#name").val();
		if(name==null||name==""){
			 $("#msg").text("请输入用户名！");	
			 return ;
		}
		$.ajax({
			type : "POST",
			url : "${base}user/isExist?name="+name,
			async : false,
			success : function(data) {
				
				if(data==1){
					  $("#msg").css("color","red"); 
					$("#msg").text("该用户名已经存在，请重新输入！");
				
				}else{
					   $("#msg").css("color","green"); 
					$("#msg").text("该用户名可以用！");
				}
			}
		});
		
	}
	function submit() {
		var name=$("#name").val();
		$.ajax({
			type : "POST",
			url : "${base}user/addUser",
			data : $('#regForm').serialize(),// 你的formid
			async : false,
			success : function(data) {
				
				if(data==0){
					layer.confirm('注册成功！马上去登陆？', {icon: 3, title:'提示',btn: ['是的', '不']}, function(index){
						window.location.href="${base}?name="+name;
						});
				}else if(data==1){
					$("#msg").text("改用户已经存在！");
				}else{
					$("#msg").text("未知错误！");
				}
			}
		});

	}
</script>
</head>
<body>
	<div id="centrer">
		<h1>Register</h1>
		<form  method="post" id="regForm">
			<table border="0">
				<tr>
					<td colspan="2"><input type="text"  required="required"
						placeholder="用户名" id="name" name="name" data-rule="required;true"></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" required="required"
						placeholder="密码" id="password" name="password" data-rule="required;true"></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" required="required"
						placeholder="确认密码" id="confirmPassword" name="confirmPassword" data-rule="required;true"></input></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td colspan="2"><input type="password" required="required" -->
<!-- 						placeholder="邮箱" name="mail"></input></td> -->
<!-- 				</tr> -->
				<tr>
					<td><button id="reg" class="but" type="button">注册</button></td>
					<td><a href="${base}">登陆</a></td>
				</tr>
			</table>
		</form>
		<span > <font id="msg" color="red">${msg}</font>
		</span>
	</div>
</body>
</html>