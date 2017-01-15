<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<script type="text/javascript">

</script>


</head>
<body>
<div id="login">  
        <h1>Login</h1>  
        <form action="${base}goLogin" method="post" >  
          <table border="0">
             <tr><td colspan="2"><input type="text" required="required" placeholder="用户名" name="name"  data-rule="required;true"></input>  </td></tr>
             <tr><td colspan="2"><input type="password" required="required" placeholder="密码" name="password"  data-rule="required;true"></input> </td></tr>
             <tr><td><button class="but" type="submit">登录</button> </td><td><a href="${base}userController/register">注册</a></td></tr>
          </table>
        </form>  
        <span > <font color="red">${msg}</font> </span>
    </div>  
</body>
</html>