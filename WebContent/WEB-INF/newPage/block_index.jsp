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
<script src="${base}js/common/jquery-1.11.2.min.js"></script>
<link href="${base}css/newPage/sfq.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(function() {
		$(".box").mouseover(function() {
			console.log("??");
			$(".tm").css("display", "none");
			$("#clock").show();
		});
		$(".box").mouseout(function() {
			console.log("?????");
			$("#clock").hide();
			$(".tm").css("display", "block");
		});
	});
</script>

</head>
<body>
	<div class="flash4" style="margin-top: 50px">
		<ul>
			<li class="first">
				<div class="imgTop">
					<div class="box">
						<canvas id="clock" width="538" height="505"></canvas>
						<img src="${base}images/timeb.jpg" width="538" height="505" alt=""
							class="tm" />
					</div>
				</div>

				<div class="imgCen">A day is a miniature of eternity</div>
				<div class="imgBot">
					<p class="bt_1">My time</p>
					<p class="bt_2">
						<span><a href="#">Schedule today</a></span><span><a
							href="#">Major event</a></span><span><a href="#">Learn today</a></span>
					</p>
				</div>
			</li>
			<li>
				<div class="imgTop">
					<img src="${base}images/crane1.jpg" width="538" height="505" alt="" />
				</div>
				<div class="imgCen">蒂芙尼为你吟唱一曲自然颂</div>
				<div class="imgBot">
					<p class="bt_1">Crane</p>
					<p class="bt_2">
						<span><a href="#">Schedule today</a></span><span><a
							href="#">Major event</a></span><span><a href="#">Learn today</a></span>
					</p>
				</div>
			</li>
			<li>
				<div class="imgTop">
					<img src="${base}images/cf.jpg" width="538" height="505" alt="" />
				</div>
				<div class="imgCen">Time is money</div>
				<div class="imgBot">
				<p class="bt_1">Property</p>
					<p class="bt_2">
						<span><a href="#">Book today</a></span><span><a href="#">Economic
								situation</a></span><span><a href="#">Statistical summary</a></span>
					</p>
				</div>
			</li>
			<li>
				<div class="imgTop">
					<img src="${base}images/ruili_img4.jpg" width="538" height="505"
						alt="" />
				</div>
				<div class="imgCen">《ar》刘海造型 女孩只需这样即刻焕然一新</div>
				<div class="imgBot">
					<a href=""><p class="bt_1">服饰</p>
						<p class="bt_2">
							<span>封面明星故事</span><span>2015春夏趋势</span><span>我爱海淘</span>
						</p></a>
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
	<script type="text/javascript">
		$(function() {
			$(".tm").hide();
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