<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String ipAddress = null;
	ipAddress = request.getHeader("x-forwarded-for");
	if (ipAddress == null || ipAddress.length() == 0
			|| "unknown".equalsIgnoreCase(ipAddress)) {
		ipAddress = request.getHeader("Proxy-Client-IP");
	}
	if (ipAddress == null || ipAddress.length() == 0
			|| "unknown".equalsIgnoreCase(ipAddress)) {
		ipAddress = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ipAddress == null || ipAddress.length() == 0
			|| "unknown".equalsIgnoreCase(ipAddress)) {
		ipAddress = request.getRemoteAddr();
		if (ipAddress.equals("127.0.0.1")) {
			//根据网卡取本机配置的IP   
			InetAddress inet = null;
			try {
				inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ipAddress = inet.getHostAddress();
		}
	}
	//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
	if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15   
		if (ipAddress.indexOf(",") > 0) {
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
		}
	}
	String path = request.getContextPath();
	String CONTEXT_PATH = "";
	CONTEXT_PATH = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.getSession().setAttribute("base", CONTEXT_PATH);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${base}css/newPage/sfq.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="model/css/default.css" />
<link rel="stylesheet" type="text/css" href="model/css/component.css" />
<script src="${base}js/common/jquery-1.11.2.min.js"></script>
<script src="model/js/modernizr.custom.js"></script>
<script type="text/javascript">
	$(function() {

		// 		mouseoverAndout("accounting","clock","time");
		// 		mouseoverAndout("major","clock","time");
	});

	function mouseoverAndout(id, showId, hideId) {
		$("#" + id).mouseover(function() {
			$("#" + hideId).css("display", "none");
			$("#" + showId).css("display", "block");
		});
		$("#" + id).mouseout(function() {
			$("#" + hideId).css("display", "block");
			$("#" + showId).css("display", "none");
		});
	}
</script>

</head>
<body>
	<div class="md-modal md-effect-13" id="modal-13">
		<div class="md-content">
			<h3>模态对话框</h3>
			<div>
				<p>这是一个模式窗口。你可以做以下的事:</p>
				<ul>
					<li><strong>读:</strong> 模态窗口可能会告诉你一些重要的事情，所以别忘了读他们说什么</li>
					<li><strong>看:</strong> 模态窗口享受一种关注,看看它,欣赏它的存在。</li>
					<li><strong>关闭:</strong> 点击下面的按钮关闭模态</li>
				</ul>
				<button class="md-close">关 闭</button>
			</div>
		</div>
	</div>
	<div class="md-overlay"></div>
	<div class="flash4" style="margin-top: 50px">
		<ul>
			<li class="first">
				<div class="imgTop">
					<div class="box">
						<canvas id="clock" width="538" height="505"></canvas>
					</div>
				</div>

				<div class="imgCen">一天是永恒的缩影！</div>
				<div class="imgBot ">
					<p class="bt_1">时间</p>
					<p class="bt_2 ">
						<span><a href="#" class="md-trigger md-setperspective"
							data-modal="modal-13" id="accounting">今日日程</a></span><span><a
							href="#" class="md-trigger md-setperspective"
							data-modal="modal-13" id="major">今日历程</a></span>
					</p>
				</div>
			</li>
			<li>
				<div class="imgTop">
					<img src="${base}images/cf.jpg" width="538" height="505" alt="" />
				</div>
				<div class="imgCen">钱不仅仅只是钱。</div>
				<div class="imgBot">
					<p class="bt_1">财产</p>
					<p class="bt_2">
						<span><a class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">支和入</a></span><span><a class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">查看</a></span><span><a
							class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">统计</a></span>
					</p>
				</div>
			</li>
			<li>
				<div class="imgTop">
					<img src="${base}images/yy2.jpg" width="538" height="505" alt="" />
				</div>
				<div class="imgCen">音乐就是记忆。</div>
				<div class="imgBot">
					<p class="bt_1">音乐</p>
					<p class="bt_2">
						<span><a class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">喜欢</a></span><span><a class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">链接</a></span><span><a
							class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">收藏</a></span>
					</p>
				</div>
			</li>
			<li>
				<div class="imgTop">
					<img src="${base}images/dy1.jpg" width="538" height="505" alt="" />
				</div>
				<div class="imgCen">《ar》刘海造型 女孩只需这样即刻焕然一新</div>
				<div class="imgBot">
					<p class="bt_1">电影</p>
					<p class="bt_2">
						<span><a class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">喜欢</a></span><span><a class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">链接</a></span><span><a
							class="md-trigger md-setperspective"
							data-modal="modal-13" href="#">收藏</a></span>
					</p>
				</div>
			</li>
			<li>
				<div class="imgTop">
					<img src="${base}images/ruili_img5.jpg" width="538" height="505"
						alt="" />
				</div>
				<div class="imgCen">电影×大明星 见证传奇从戛纳诞生</div>
				<div class="imgBot">
					<a href=""><p class="bt_1">服饰</p>
						<p class="bt_2">
							<span>封面明星故事</span><span>2015春夏趋势</span><span>我爱海淘</span>
						</p></a>
				</div>
			</li>
			<li>
				<div class="imgTop">
					<img src="${base}images/ruili_img6.jpg" width="538" height="505"
						alt="" />
				</div>
				<div class="imgCen">重返20岁试用周</div>
				<div class="imgBot">
					<a href=""><p class="bt_1">服饰</p>
						<p class="bt_2">
							<span>封面明星故事</span><span>2015春夏趋势</span><span>我爱海淘</span>
						</p></a>
				</div>
			</li>
			<li class="fast">
				<div class="imgTop">
					<img src="${base}images/ruili_img7.jpg" width="538" height="505"
						alt="" />
				</div>
				<div class="imgCen">玩美女孩盖天天 阳光女神进阶攻略</div>
				<div class="imgBot">
					<a href=""><p class="bt_1">服饰</p>
						<p class="bt_2">
							<span>封面明星故事</span><span>2015春夏趋势</span><span>我爱海淘</span>
						</p></a>
				</div>
			</li>
		</ul>
	</div>
	<script src="${base}js/newPage/sfq.js" type="text/javascript"></script>
	<script type="text/javascript"
		src="${base}js/newPage/clock-canvas.min.js"></script>

	<!-- the overlay element -->
	<script src="model/js/classie.js"></script>
	<script src="model/js/modalEffects.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#clock").drawClock(
			//{
			// hCol: 'xxx',// 时针颜色
			// mCol: 'xxx', // 时针颜色
			// sCol: 'xxx', // 时针颜色
			// isNumCol: 'xxx', // 数字所在的点颜色
			// noNumCol: 'xxx', // 非数字所在的点颜色
			// dCol: 'xxx', // 中心圈颜色
			//}
			);

		});
	</script>

</body>
</html>