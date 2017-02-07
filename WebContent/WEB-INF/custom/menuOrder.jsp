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
		$('#datagrid').bootstrapTable({
			url : "${base}datagrid", // 接口 URL 地址
			pagination : false, // 开启分页功能
			sidePagination : 'server',
			toolbar : '#process-custom-toolbar',
			showColumns : false,
			escape : true,
			idField : "id",
			columns : [ {
				align : 'center',
				valign : 'middle',
				checkbox : true
			}, {
				field : 'id',
				visible : false,
				switchable : false
			}, {
				field : 'menuCode',
				title : '菜单编码',
				width : '130'
			}, {
				field : 'menuName',
				title : '菜单名称',
				width : '230'
			},{
				field : 'menuOrder',
				title : '排序',
				width : '130'
			}]
		});
		
		$("#up").click(function() {
			var ids = getSelectDataID();
			if (ids.length == 0) {

				layer.msg("请选中需要显示的菜单！");
				return;
			}
			if (ids.length > 1) {
				layer.msg("只能选中一行！");
				return;
			}
			
			$.ajax({
				cache : true,
				type : "POST",
				url : "${base}goMenuOrderUp?id="+ids[0],
				async : false,
				success : function(data) {
					$('#datagrid').bootstrapTable('refresh');
				}
			});
		});
	});
	function getSelectDataID() {
		var ids = new Array();
		$($('#datagrid').bootstrapTable('getSelections')).each(
				function(index, element) {
					ids.push(element.id);
				});
		return selectedIds;

	}
</script>

</head>
<body>
	<div id="menuOrder_centrer">
		<div id="process-custom-toolbar">
			<button id="up" type="button" class="btn btn-link">上移</button>
			<button id="down" type="button" class="btn btn-link">下移</button>
		</div>
		<table id="datagrid"></table>
	</div>
</body>
</html>