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
<ink rel="stylesheet" href="${base }xiala/reset.css" />
<link rel="stylesheet" href="pullToRefresh.css" />
<script src="${base }xiala/iscroll.js"></script>
<script src="${base }xiala/pullToRefresh.js"></script>
<script src="${base }xiala/colorful.js"></script>
<style type="text/css" media="all">
body,html {
	padding: 0;
	margin: 0;
	height: 100%;
	font-family: Arial, Microsoft YaHei;
	color: #111;
}

li {
	/*border-bottom: 1px #CCC solid;*/
	text-align: center;
	line-height: 80px;
}
</style>

</head>
<body>
	<!--must content ul li,or shoupi-->
	<div id="wrapper">
		<ul>
			<li>row 7</li>
			<li>row 6</li>
			<li>row 5</li>
			<li>row 4</li>
			<li>row 3</li>
			<li>row 2</li>
			<li>row 1</li>
		</ul>
	</div>
	<script>
		for (var i = 0; i < document.querySelectorAll("#wrapper ul li").length; i++) {
			document.querySelectorAll("#wrapper ul li")[i].colorfulBg();
		}
		refresher.init({
			id : "wrapper",//<------------------------------------------------------------------------------------┐
			pullDownAction : Refresh,
			pullUpAction : Load
		});
		var generatedCount = 0;
		function Refresh() {
			setTimeout(
					function() { // <-- Simulate network congestion, remove setTimeout from production!
						var el, li, i;
						el = document.querySelector("#wrapper ul");
						el.innerHTML = '';
						for (i = 0; i < 11; i++) {
							li = document.createElement('li');
							li.appendChild(document.createTextNode('async row '
									+ (++generatedCount)));
							el.insertBefore(li, el.childNodes[0]);
						}
						wrapper.refresh();
						/****remember to refresh after  action completed！ ---yourId.refresh(); ----| ****/
						for (var i = 0; i < document
								.querySelectorAll("#wrapper ul li").length; i++) {
							document.querySelectorAll("#wrapper ul li")[i]
									.colorfulBg();
						}
					}, 1000);

		}

		function Load() {
			setTimeout(
					function() {// <-- Simulate network congestion, remove setTimeout from production!
						var el, li, i;
						el = document.querySelector("#wrapper ul");
						for (i = 0; i < 2; i++) {
							li = document.createElement('li');
							li.appendChild(document.createTextNode('async row '
									+ (++generatedCount)));
							el.appendChild(li, el.childNodes[0]);
						}
						wrapper.refresh();
						/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
						for (var i = 0; i < document
								.querySelectorAll("#wrapper ul li").length; i++) {
							document.querySelectorAll("#wrapper ul li")[i]
									.colorfulBg();
						}
					}, 1000);
		}
	</script>

	<div
		style="text-align: center; margin: 50px 0; font: normal 14px/24px 'MicroSoft YaHei';">
		<p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
	</div>
</body>
</html>