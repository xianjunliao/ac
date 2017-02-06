<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript"> 

</script>
<head>

</head>
<body>
<ul id="nav_ul" class="nav nav-tabs">
		<li ><a href="${base}">首页</a></li>
		<li class="active"><a href="${base}accounting">记账</a></li>
  <li><a href="#">音乐</a></li>
  <li><a href="#">电影</a></li>
  <li><a href="#">运动</a></li>
  <li><a href="#">设置</a></li>
</ul>

<div id="u">
	       <c:if test="${username==null}">
				<button id="w_login" type="button" class="btn btn-link">登录</button>
				<button type="button" class="btn btn-link">注册</button>
			</c:if> <c:if test="${username!=null}">
			<span class="label label-success"> ${username}</span>
			</c:if>
		<button id="out_login" type="button" class="btn btn-link">退出登录</button>
	</div>
</body>
</html>