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

<script type="text/javascript">
	$(function() {
		$("#w_login").click(function() {
			layer.open({
				area : [ '320px', '180px' ],
				title : '登录',
				type : 2,
				content : '${base}doLogin'
			});
		});
		$("#w_register").click(function() {
			layer.open({
				area : [ '320px', '200px' ],
				title : '注册',
				type : 2,
				content : '${base}doRegister'
			});
		});
		$("#out_login").click(function() {
			window.location.href="${base}outLogin";
		});

	});
</script>

</head>
<body>
	<ul id="nav_ul" class="nav nav-tabs">
		<c:forEach items="${menus}" var="t">
        <li <c:if test="${t.menuCode =='home'}"> class="active" </c:if>><a  href="${base}${t.src }">${t.menuName }</a></li>
		</c:forEach>
	</ul>
	<div id="u">
	       <c:if test="${username==null}">
				<button id="w_login" type="button" class="btn btn-link">登录</button>
				<button id="w_register" type="button" class="btn btn-link">注册</button>
			</c:if> <c:if test="${username!=null}">
			<span class="label label-success"> ${username}</span>
			<button id="out_login" type="button" class="btn btn-link">退出登录</button>
			</c:if>
		
	</div>
</body>
</html>