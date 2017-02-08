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

		$("#goMenuOrder").click(function() {
			layer.open({
				area : [ '340px', '160px' ],
				title : '修改菜单排序',
				type : 2,
				content : '${base}goMenuOrder?menuCode=' + "home"
			});
		});
		$("#out_login").click(function() {
			window.location.href = "${base}outLogin";
		});
	});
</script>

</head>
<body id="body">
	<div id="centrer">
		<div class="top">
			<ul id="nav_ul" class="nav nav-tabs">
				<c:forEach items="${menus}" var="t">

					<li <c:if test="${t.menuCode =='home'}"> class="active" </c:if>><a
						href="${base}${t.src }">${t.menuName }</a></li>
				</c:forEach>
			</ul>

			<div id="u">
				<c:if test="${username==null}">
					<button id="w_login" type="button" class="btn btn-link">登录</button>
					<button id="w_register" type="button" class="btn btn-link">注册</button>
				</c:if>
				<c:if test="${username!=null}">
					<span class="label label-danger"> ${username}</span>
					<div class="btn-group">
						<button type="button"
							class="btn btn-success dropdown-toggle btn-xs"
							data-toggle="dropdown">
							菜单设置 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a id="goMenuOrder" href="#">修改菜单排序</a></li>
							<li class="divider"></li>
							<c:forEach items="${ms}" var="m">
								<li><a href="${base}showCustomMenu?menuCode=${m.menuCode}">${m.menuName}</a></li>
							</c:forEach>
						</ul>
					</div>
					<button id="out_login" type="button" class="btn btn-link">退出登录</button>
				</c:if>

			</div>
		</div>
		<div class="left"></div>
		<div class="right">
			<c:if test="${username==null}">
				<h2>&nbsp;&nbsp;想有效的的管理自己吗？想的话就登录本网站吧。</h2>
			</c:if>
			<c:if test="${username!=null}">
				<h2>&nbsp;&nbsp;合理的管理自己，才能使自己更好的成长！</h2>
			</c:if>
			<c:forEach items="${mqs}" var="m">
				<a>&nbsp;&nbsp;&nbsp;&nbsp;${m.id}.
					${m.text}&nbsp;&nbsp;&nbsp;&nbsp;—${m.author}</a>
				<br>
			</c:forEach>
		</div>
		<div class="bottom"></div>
	</div>

</body>
</html>