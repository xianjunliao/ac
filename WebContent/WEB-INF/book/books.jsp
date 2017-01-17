<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>

	<div id="login">
		<h1>欢迎用户[<a href="#">${username}</a>]来到记账薄</h1>
		<c:if test="${ empty list }"><a >新增</a></c:if>
		<c:if test="${not empty list }">
		<a >新增</a>
		<table border="1" width="300xp">
		<thead>
		 <tr >
		    <th>
		      记账编号
		  </th>
		   <th>
		      记账名称
		  </th>
		     <th>
		 记账类型
		  </th>
		       <th>
		 记账金额
		  </th>
		 </tr>
		</thead>
		<c:forEach items="${list }" var="bk">
		<tr>
		<td>${bk.id}</td>
		<td>${bk.acName}</td>
		<td><c:if test="${bk.acType==0}">消费</c:if><c:if test="${bk.acType==1}">收入</c:if>
		</td>
		<td>${bk.acAmount}</td>
		</tr>
		</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>