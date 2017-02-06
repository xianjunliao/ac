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
	$(function() {
		$('#datagrid').bootstrapTable(
				{
					url : "${base}datagrid", // 接口 URL 地址
					pagination : true, // 开启分页功能
					sidePagination : 'server',
					toolbar : '#process-custom-toolbar',
					showColumns : false,
					escape : true,
// 					fixedColumns : isFixed,
					idField : "id",
					columns : [ { align : 'center', valign : 'middle', checkbox : true }, 
					            { field : 'id', visible : false, switchable : false },
							{ field : 'menuCode', title : '菜单编码', width : '130'}, 
							{ field : 'menuName', title : '菜单名称', width : '230'},
							{ field : 'createTime', title : '创建时间  ', width : '160', sortable : true, formatter : formatDateTime }] });
		
		
		
		
		
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
			window.location.href="${base}outLogin";
		});
		
		$("#show").click(function() {
			
			window.location.href="${base}showMenu?ids="+getSelectDataID();
		});
		
	});
	
	function getSelectDataID() {
		var ids = new Array();
		$($('#datagrid').bootstrapTable('getSelections')).each(function(index, element) {
		ids.push(element.id);
		});
		return selectedIds;

	}
</script>

</head>
<body>
	<ul id="nav_ul" class="nav nav-tabs">
		<c:forEach items="${menus}" var="t">
			
        <li <c:if test="${t.menuCode =='custom'}"> class="active" </c:if>><a  href="${base}${t.src }">${t.menuName }</a></li>
		</c:forEach>
	</ul>
	<div id="u">
	       <c:if test="${username==null}">
				<button id="w_login" type="button" class="btn btn-link">登录</button>
				<button id="w_register" type="button" class="btn btn-link">注册</button>
			</c:if> <c:if test="${username!=null}">
			<span class="label label-success"> ${username}</span>
			<button id="out_login" type="button" class="btn btn-link">退出登录</button>
			</c:if>
		
	</div>
	<div id="centrer">
	
			<div id="process-custom-toolbar" >
			<div class="form-inline">
			<button type="button" id="show" class="btn btn-link">显示</button>
			<button type="button" id="hidden" class="btn btn-link">隐藏</button>
		</div>
	</div>
			<table id="datagrid"></table>
	</div>
</body>
</html>