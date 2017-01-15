$(function() {
	var keyId = $("#id").val();
	var type = $("#uploadType").val();
	
	if(type == null || type == ""){
		layer.alert("uploadType不能为空");
		return;
	}
	
	//已经上传附件数量
	showUploadCount(keyId);

	//添加附件
	//type为前缀，如：cp、sp、op、mail
	//type为detail时，只能查看、下载，不能上传、删除。
	$("#uploadBtn").click(function() {
		top.layer.open({
			type : 2,
			title : "附件",
			content : '/sysFileController/goUploadFiles?keyId=' + keyId + "&type=" + type,
			area : [ '900px', '630px' ],
			cancel : function(index) {
				layer.close(index);
				showUploadCount(keyId);
			}
		});
	})
})

//已经上传附件数量
function showUploadCount(keyId) {
	$.ajax({
		type : "post",
		url : "/sysFileController/getSysFileCount?keyId=" + keyId,
		success : function(rData) {
			$("#uploadCount").html(rData);
		}
	});
}