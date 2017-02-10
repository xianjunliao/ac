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
<style>
#acAmount {
	width: 50px;
	height: 18px;
}
#changeAc a {
	font-size: 14px;
	color: red;
}

#changeAc a:HOVER {
	color: green;
	cursor: pointer;
}

#log {
	float: left;
	/* font-size: 12px; */
}
</style>
<script type="text/javascript">
	$(function() {
		var objJson = "[]";
		var jsonarray = eval('('+objJson+')');
		$(".opt").click(function() {
			
			var acCode=$(this).parents("tr").find("input").eq(0).val();   
			var acName=$(this).parents("tr").find("span").eq(0).text();   
			var acAmount=$(this).parents("tr").find("input").eq(1).val();   
			var j={"acCode": acCode,"acName": acName ,"acAmount":acAmount};
			jsonarray.push(j); 

		});
		$(".btn").click(function() {
			var index = layer.load();
			    console.log(jsonarray);
			    console.log(JSON.stringify(jsonarray));
			$.ajax({
				type : "POST",
				dataType:"json",
				data:{ds:JSON.stringify(jsonarray)},
				url : "${base}addAcLog",
				success : function(data) {
					layer.close(index); 
					if(data==0){
						
						layer.alert("记账成功");
					}else{
						
						layer.alert("记账失败");
					}
					
					
				}
			});
			
		});
	});
</script>

</head>
<body>
	<div id="changeAc">

		<table>
			<c:forEach items="${tempEntities}" var="t">
				<c:if test="${t.txType==0}">
					<tr>
						<td>今日</td>
						<td><input type="hidden" id="acCode" value="${t.txCode}"><span style="color:red" id="acName">${t.txName}</span></td>
						<td>消费了</td>
						<td><input id="acAmount" type="text" value="0.00" /></td>
						<td>元</td>
						<td><a class="opt">&nbsp;&nbsp;&nbsp;&nbsp;记</a></td>
					</tr>
				</c:if>

				<c:if test="${t.txType==1}">
					<tr height="50px">
						<td>今日</td>
						<td><input type="hidden" id="acCode" value="${t.txCode}"><span style="color:green" id="acName">${t.txName}</span></td>
						<td>入账了</td>
						<td><input id="acAmount" type="text" value="0.00" /></td>
						<td>元</td>
						<td><a class="opt">&nbsp;&nbsp;&nbsp;&nbsp;记</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	<div id="myButtons2" class="bs-example">
		<button type="button" class="btn" 
				data-loading-text="正在保存...">保存记账
		</button>
	</div>
</body>
</html>