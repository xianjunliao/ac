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
<script src="${base}js/newPage/page.js"></script>
<link rel="stylesheet" type="text/css"
	href="${base }css/newPage/page.css" />
<script type="text/javascript">
	
</script>

</head>
<body>
	<div class="page">

		<div class="ac_menus">
			<div class="menu">1</div>
			<div class="menu img left two"></div>
			<div class="menu hide">3</div>
			<div class="menu hide">4</div>
			<div class="menu img right">5</div>
			<div class="menu ">6</div>
			<div class="menu ">7</div>
			<div class="menu img left eight"></div>
			<div class="menu plan">9-10</div>
	
			<div class="menu  img right">11</div>
			<div class="menu ">12</div>
			<div class="menu ">13</div>
			<div class="menu img left fourteen"></div>
			<div class="menu hide">15</div>
			<div class="menu hide">16</div>
			<div class="menu img right">17</div>
			<div class="menu">18</div>

		</div>
	</div>
</body>
</html>