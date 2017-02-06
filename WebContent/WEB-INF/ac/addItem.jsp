<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	var index = $('#tx').length;
	$(function() {

		var tables = $('#tx');

		$("#addRow")
				.click(
						function() {
							index++;
							var txName = "<input name='txName' type='text' width='140px' />";
							var txType = '<select width="28px" name="select" name="txType" id="txType" class="xla_k"> <option value="0" selected="selected">消费</option> <option value="1">收入</option></select>';
							var trtd = $('<tr id='+index+'><td>' + txName
									+ '</td><td>' + txType + '</td></tr>');
							trtd.appendTo(tables);
						});

	});
	//删除行  
	function delRow(rowIndex) {
		$("#" + rowIndex).remove();
		index--;
	}

	//删除行数据
	function delRowData(id) {
		$.ajax({
			cache : true,
			type : "POST",
			url : "${base}book/deleteTx?id=" + id,
			async : false,
			success : function(data) {
				window.location.reload();//刷新当前页面.
			}
		});
	}

	//保存行
	function saveRow(obj) {
		var $td = $(obj).parents('tr').children('td');
		var txType = $td.find('option:selected').val();
		var txName = $td.find('input').val();

		$.ajax({
			cache : true,
			type : "POST",
			url : "${base}book/addTemp?txName=" + txName + "&txType=" + txType,
			async : false,
			success : function(data) {
				window.location.reload();//刷新当前页面.
			}
		});
	}

	function saveRowBook(obj) {
		var $td = $(obj).parents('tr').children('td');
		var map = new Map();
		var arr = new Array();
		$td.find('input').each(function() {
			var id = $(this).attr('id');
			var val = $(this).val();
			map.put(id, val);
			arr.push(id + ":" + val);
		});

		$.ajax({
			cache : true,
			type : "POST",
			url : "${base}book/addBook?array=" + arr,
			async : false,
			success : function(data) {
				window.location.reload();//刷新当前页面.
			}
		});
	}
</script>
<head>

</head>
<body>
<ul id="nav_ul" class="nav nav-tabs">
  <li class="active"><a href="${base}">首页</a></li>
  <li ><a href="${base}book/accounting">记账</a></li>
  <li><a href="#">音乐</a></li>
  <li><a href="#">电影</a></li>
  <li><a href="#">运动</a></li>
  <li><a href="#">设置</a></li>
</ul>

	<div id="u">
		<b>用户[${username}]</b>
	</div>
	<div id="books">


		<div id="temp">
			<div class="btn-group" data-toggle="buttons">
				<label class="btn btn-primary"> <input type="radio"
					name="options" id="option1"> 新增
				</label> <label class="btn btn-primary"> <input type="radio"
					name="options" id="option2"> 修改
				</label> <label class="btn btn-primary"> <input type="radio"
					name="options" id="option3"> 删除
				</label>
			</div>
			<table id="tx" width="500px" class="table">
				<tr>
					<td ><b style="color:red">名 称</b></td>
					<td ><b>类 型</b></td>
				</tr>
				<c:forEach items="${temps }" var="t">
					<tr>
						<td>${t.txName}</td>
						<td><c:if test="${t.txType==0 }">消费</c:if>
							<c:if test="${t.txType==1 }">收入</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>