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
		
		$("#submit").click(function() {
        var id=$("#id").val();
        var order =$("#orderNo").val();

			$.ajax({
				cache : true,
				type : "POST",
				url : "${base}doMenuOrderUp?id="+id+"&order="+order,
				async : false,
				success : function(data) {
					if(data==0){
					parent.location.reload();
					}
				}
			});
		});
	});
</script>

</head>
<body>
	<div id="menuOrder_centrer">
		<input type="hidden" class="form-control" id="id" name="id" value="${id}"/>
			<table class="form_table" style="margin-top: 16px;">
				<tbody>
					<tr>
						<th style="width: 0px;"><font color="red"></font>更改${order.menuName }菜单序列:</th>
						<td><input type="text" class="form-control" id="orderNo" name="orderNo" value="${order.menuOrder}"/></td>
				</tr>
				<tr>
						<td colspan="2" class="from_table_submit" style="text-align: center;"><input type="button" id="submit" class="btn btn-info" style="width: 100px; margin-top: 10px;" value="确定" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>