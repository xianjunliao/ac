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
				
					layer.confirm('注册成功！是否立即登陆？', {icon: 3, title:'提示',btn: ['是', '等会再说']} , function(index){
						
						$.ajax({
							cache : true,
							type : "POST",
							url : "${base}goLogin",
							data : $('#regForm').serialize(),// 你的formid
							async : false,
							success : function(data) {
								if(data==0){
									$("#msg").text("该用户不存在！");
								}else if(data==1){
									parent.location.reload();
								}else if(data==2){
									$("#msg").text("账户或密码错误！");
								}else{
									$("#msg").text("未知错误！");
								}
							}
						});
						
						    });
						
				}else if(data==1){
					$("#msg").text("该用户已经存在！");
				}else{
					$("#msg").text("未知错误！");
				}
			}
		});

	}
</script>
</head>
<body>

	<div id="register_centrer">
		<form  method="post" id="regForm">
			<table border="0">
				<tr>
					<td colspan="2"><input type="text"  required="required"
						placeholder="用户名" id="name" name="name" ></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" required="required"
						placeholder="密码" id="password" name="password" ></input></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" required="required"
						placeholder="确认密码" id="confirmPassword" name="confirmPassword" ></input></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td colspan="2"><input type="password" required="required" -->
<!-- 						placeholder="邮箱" name="mail"></input></td> -->
<!-- 				</tr> -->
				<tr>
					<td><button id="reg" class="but" type="button">注册</button></td>
					<td><button class="btn btn-default" type="reset">重置</button></td>
				</tr>
			</table>
		</form>
		<span > <font id="msg" color="red">${msg}</font>
		</span>
	</div>
</body>
</html>