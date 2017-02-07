<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="${base }css/rigth/default.css" />
<link rel="stylesheet" type="text/css" href="${base }css/rigth/component.css" />
<script src="${base }js/rigth/modernizr.custom.js"></script>
<script type="text/javascript">
	$(function() {
	

		$('[data-toggle="offcanvas"]').click(function() {
			$('#wrapper').toggleClass('toggled');
		});

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
				content : '${base}goMenuOrder?menuCode=' + "ac"
			});
		});
		$("#out_login").click(function() {
			window.location.href = "${base}outLogin";
		});
	});
</script>
<head>

</head>
<body id="body">

	<div class="container">
<!-- 		<header class="clearfix"> -->
		<ul id="nav_ul" class="nav nav-tabs">
			<c:forEach items="${menus}" var="t">

				<li <c:if test="${t.menuCode =='ac'}"> class="active" </c:if>><a
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
<!-- 		</header> -->
		<div class="main">

			<div class="side">
				<nav class="dr-menu">
				<div class="dr-trigger">
					<span class="dr-icon dr-icon-menu"></span><a class="dr-label">选项</a>
				</div>
				<ul>
					<li><a class="dr-icon" href="#">查看消费记录</a></li>
					<li><a class="dr-icon" href="#">查看收入记录</a></li>
					<li><a class="dr-icon" href="#">今日记账</a></li>
					<li><a class="dr-icon" href="#">周消费</a></li>
					<li><a class="dr-icon" href="#">月消费</a></li>
					<li><a class="dr-icon" href="#">季消费</a></li>
				</ul>
				</nav>
			</div>
			<div style="clear: both">
			</div>
			<div id="centrer"><h1>静下心来吧！</h1></div> 
		</div>
	</div>
	<!-- /container -->
	<script src="${base }js/rigth/ytmenu.js"></script>
</body>
</html>