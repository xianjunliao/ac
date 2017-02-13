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

		$("#saveac").click(function() {
			var index = layer.load();
			
			$("#gridAc").find("tr").each(function(){
				var acCode=$(this).find("input").eq(0).val();   
				var acType=$(this).find("input").eq(1).val();   
				var tempId=$(this).find("input").eq(2).val();  
				var acName=$(this).find("span").eq(0).text();   
				var acAmount=$(this).find("input").eq(3).val(); 
				if(acAmount>0){
				var j={"acCode": acCode,"acName": acName ,"tempId":tempId,"acAmount":acAmount,"acType":acType};
				jsonarray.push(j); 
				}
			  });
			    console.log(jsonarray);
			$.ajax({
				type : "POST",
				dataType:"json",
				data:{ds:JSON.stringify(jsonarray)},
				url : "${base}addAcLog",
				success : function(data) {
					if(data==0){
						
						layer.msg("记账成功");
						parent.location.reload();
					}else{
						
						layer.msg("记账失败");
					}
					layer.close(index); 
					
					
				}
			});
			
		});
		
		$("#afreshAc").click(function() {
			var index = layer.load();
			$.ajax({
				type : "POST",
				dataType:"json",
				data:{ds:JSON.stringify(jsonarray)},
				url : "${base}afreshAcLog",
				success : function(data) {
					if(data==0){
						
						layer.msg("重置成功");
						parent.location.reload();
					}else{
						
					    layer.msg("重置失败");
					}
					layer.close(index); 
					
					
				}
			});
			
		});
	});
</script>

</head>
<body>
    <div>今日共计消费了${out}元，收入了${in}元</div>
    <br>
	<div id="changeAc">
		<table id="gridAc">
			<c:forEach items="${tempEntities}" var="t">
				<c:if test="${t.txType==0}">
					<tr>
						<td>今日</td>
						<td><input type="hidden" id="acCode" value="${t.txCode}"><input type="hidden" id="acType" value="${t.txType}"><input type="hidden" id="acType" value="${t.id}"><span style="color:red" id="acName">${t.txName}</span></td>
						<td>消费了</td>
						<td><input id="acAmount" type="text" value="0.00" /></td>
						<td>元</td>
					</tr>
				</c:if>

				<c:if test="${t.txType==1}">
					<tr height="50px">
						<td>今日</td>
						<td><input type="hidden" id="acCode" value="${t.txCode}"><span style="color:green" id="acName">${t.txName}</span></td>
						<td>入账了</td>
						<td><input id="acAmount" type="text" value="0.00" /></td>
						<td>元</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	<div id="myButtons2" class="bs-example">
		<button type="button" id="saveac" class="btn" 
				data-loading-text="正在保存...">保存记账
		</button>
		<button type="button" id="afreshAc" class="btn" title="重置今天所有已计入到数据库中的记账明细">重新记账
		</button>
	</div>
	<p></p>
</body>
</html>