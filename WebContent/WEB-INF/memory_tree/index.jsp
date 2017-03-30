<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="/header.jsp"%> --%>
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
	if ("192.168.106.90".equals(ipAddress))
		CONTEXT_PATH = request.getScheme() + "://192.168.106.90:80"
				+ path + "/";
	else
		CONTEXT_PATH = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	request.getSession().setAttribute("base", CONTEXT_PATH);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">

<style type="text/css">
/* common styling */
.menu {
	font-family: arial, sans-serif;
	/* 	width: 749px; */
	height: 30px;
	position: relative;
	margin: 0;
	font-size: 11px;
	margin: 50px auto;
	background: #fff;
	position: relative;
}

.menu ul {
	padding: 0;
	margin: 0;
	list-style-type: none;
}

.menu ul li {
	float: left;
	border-left: 1px solid #eee;
	width: 106px;
}

.menu ul li a,.menu ul li a:visited {
	display: block;
	float: left;
	width: 101px;
	text-decoration: none;
	padding: 0 0 0 5px;
	height: 30px;
	line-height: 30px;
	color: #000;
	background: #c9c9a7 bottom right no-repeat;
}

.menu ul li ul {
	display: none;
}
/* specific to non IE browsers */
.menu ul li:hover a {
	color: #fff;
	background: #b3ab79;
}

.menu ul li:hover ul {
	display: block;
	position: absolute;
	/* 	width: 749px; */
	top: 30px;
	left: 0;
	background: #b3ab79;
	color: #fff;
}

.menu ul li:hover ul.right_side li {
	float: right;
	border: 0;
	border-left: 1px solid #eee;
}

.menu ul li:hover ul.left_side li {
	float: left;
	border: 0;
	border-left: 1px solid #eee;
}

.menu ul li:hover ul li a.hide {
	background: #bd8d5e bottom right no-repeat;
	color: #fff;
}

.menu ul li:hover ul li:hover a.hide {
	background: #b3ab79;
	color: #fff;
}

.menu ul li:hover ul li ul {
	display: none;
}

.menu ul li:hover ul li a {
	display: block;
	background: ##b3ab79;
	color: #fff;
}

.menu ul li:hover ul li a:hover {
	background: #dfc184;
	color: #000;
}

.menu ul li:hover ul li:hover ul {
	display: block;
	position: absolute;
	left: 0;
	top: 30px;
	color: #000;
	background: #dfc184;
}

.menu ul li:hover ul.right li {
	float: right;
}

.menu ul li:hover ul li:hover a.hide {
	background: #dfc184;
	color: #000;
}

.menu ul li:hover ul li:hover ul li a {
	background: #dfc184;
	color: #000;
}

.menu ul li:hover ul li:hover ul li a:hover {
	background: #bd8d5e;
	color: #fff;
}
</style>
</head>
<body>
	<div class="menu">
		<ul>
			<c:forEach items="${menu }" var="m">
				<li><a class="hide" href="#">${m.name}</a>
					<ul class="left_side">
						<c:forEach items="${m.menu1lists}" var="m1">
							<li><a href="#" title="">${m1.name }</a>
								<ul class="right_side">
									<li><a href="#" title="">一</a></li>
									<li><a href="#" title="">二</a></li>
									<li><a href="#" title="">三</a></li>
								</ul></li>
						</c:forEach>
					</ul></li>
			</c:forEach>
		</ul>
	</div>

	<div
		style="text-align: center; margin: 50px 0; font: normal 14px/24px 'MicroSoft YaHei';">
	</div>
</body>
</html>