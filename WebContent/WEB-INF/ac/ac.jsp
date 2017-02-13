<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">

<script type="text/javascript">
var id="changeac";
	$(function() {
		init();
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
			$.ajax({
				type : "POST",
				url : "${base}outLogin",
				success : function(data) {
			    location.href = "${base}";
				}
			});
		});
	
		$(".side li").each(function() {
			$(this).click(function() {
				var index = layer.load();
				id= $(this).attr("id");
				// 				window.location.href = "${base}" + src;
	     	 $(".side li").css("background-color", "#AFEEEE");
				$.ajax({
					type : "POST",
					url : "${base}" + id,
					success : function(data) {
						$("#"+id).css("background-color", "#FFE4C4");
						$(".right").html(data);
						layer.close(index); 
					}
				});
			});

			$(this).hover(function() {
				$(this).css("cursor", "pointer");
				$(this).css("background-color", "#FFE4C4");
			}, function() {
				 $(".side li").css("background-color", "#AFEEEE");
				$("#"+id).css("background-color", "#FFE4C4");
			});
		});
	});

	function init() {
		var index = layer.load();
		$("#changeac").css("background-color", "#FFE4C4");
		$.ajax({
			type : "POST",
			url : "${base}changeac",
			success : function(data) {
				$(".right").html(data);
				layer.close(index); 
			}
		});
	}
</script>

</head>
<body>
	<div id="centrer">
		<div class="top">
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
					<button id="out_login" type="button" class="btn btn-link">退出登录</button>
				</c:if>

			</div>
		</div>
		<div class="left">
			<c:if test="${username!=null}">
				<div class="side">
					<nav class="dr-menu dr-menu-open">
					<ul>
						<c:forEach items="${submenus }" var="s">
							<li id="${s.src }">${s.menuName }</li>
						</c:forEach>
					</ul>
					</nav>
				</div>
				<div style="clear: both"></div>
			</c:if>
		</div>
		<div class="right"></div>
		<div class="bottom">
			<div class="icp">
				<a target="_blank" href="http://www.miitbeian.gov.cn">粤ICP备16059245号</a>
			</div>
		</div>
	</div>
</body>
</html>