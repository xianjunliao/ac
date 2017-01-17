<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
    String ipAddress = null;   
    ipAddress = request.getHeader("x-forwarded-for");   
    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
     ipAddress = request.getHeader("Proxy-Client-IP");   
    }   
    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
        ipAddress = request.getHeader("WL-Proxy-Client-IP");   
    }   
    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
     ipAddress = request.getRemoteAddr();   
     if(ipAddress.equals("127.0.0.1")){   
      //根据网卡取本机配置的IP   
      InetAddress inet=null;   
   try {   
    inet = InetAddress.getLocalHost();   
   } catch (UnknownHostException e) {   
    e.printStackTrace();   
   }   
   ipAddress= inet.getHostAddress();   
     }   
    }   
    //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
    if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15   
        if(ipAddress.indexOf(",")>0){   
            ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));   
        }   
    }   
	String path = request.getContextPath();
	String CONTEXT_PATH="";
	if("192.168.106.90".equals(ipAddress))
	CONTEXT_PATH = request.getScheme() + "://192.168.106.90:80"+ path + "/";
	else
	CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	request.getSession().setAttribute("base", CONTEXT_PATH);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="${base}css/bootstrap/bootstrap-theme.min.css" />
<link rel="stylesheet" href="${base}css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="${base}css/bootstrapTable/bootstrap-table.min.css" />
<link rel="stylesheet" href="${base}css/bootstrapTable/bootstrap-table-fixed-columns.css" />
<link rel="stylesheet" href="${base}css/bootstrapSelect/bootstrap-select.min.css" />
<link rel="stylesheet" href="${base}css/common/main.css" />
<link rel="stylesheet" href="${base}css/common/common.css" />
<link rel="stylesheet" href="${base}css/common/override.css" />

<script src="${base}js/common/jquery-1.11.2.min.js"></script>
<script src="${base}js/common/JQuery.md5.js"></script>

<script src="${base}js/bootstrap/bootstrap.min.js"></script>
<script src="${base}js/bootstrapTable/bootstrap-table.min.js"></script>
<script src="${base}js/bootstrapTable/bootstrap-table-zh-CN.min.js"></script>
<script src="${base}js/bootstrapTable/bootstrap-table-fixed-columns.js"></script>
<script src="${base}js/bootstrapSelect/bootstrap-select.min.js"></script>
<script src="${base}js/bootstrapSelect/defaults-zh_CN.min.js"></script>
<link rel="stylesheet" href="${base}/js/validator/jquery.validator.css">
<script type="text/javascript" src="${base}/js/validator/src/jquery.validator.js"></script>
<script type="text/javascript" src="${base}/js/validator/local/zh-CN.js"></script>
 
<script type="text/javascript" src="${base}/js/datepicker/WdatePicker.js"></script>

<script type="text/javascript" src="${base}/js/common/common.js"></script>

<script type="text/javascript" src="${base}js/layer/layer.js"></script>

<title>AC：花小钱，记大账 </title>
<link rel="shortcut icon" href="${base}img/ac_tm_32.ico"/>

