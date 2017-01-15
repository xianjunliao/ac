$(function(){
	var keyId = $("#keyId").val();
	var type = $("#type").val();

	var initialPreview = new Array();
	var initialPreviewConfig = new Array();
	$.ajax({  
		type: "get",
		url: sysFileUrl + "/getSysFileList?keyId="+keyId,
		async: false,
		success: function(sysFileList){
			$.each(sysFileList, function(index, sysFile){
				initialPreview.push(sysFileUrl+'/doDownloadFiles?id='+sysFile.id);
				var ipc = {type: isPicture(sysFile.fileName), size: sysFile.fileSize, caption: sysFile.fileName, extra: {id: sysFile.id}, showDelete: false};
				initialPreviewConfig.push(ipc);
			})
		},
	});
	
	$.fn.fileinput.defaults.allowedPreviewTypes=['image'];
	$("#uploadFiles").fileinput({
		previewSettings : {
	        image: {width: "170px", height: "140px"},
	        object: {width: "180px", height: "246px"}
	    },
	    initialPreview: initialPreview,
	    initialPreviewAsData: true,
	    initialPreviewConfig: initialPreviewConfig,
	    otherActionButtons:'<button type="button" style="padding:0;" class="kv-preview-dl btn btn-xs btn-default" title="下载"><a download="{caption}" href="{data}" style="padding:7px 12px;"><i class="glyphicon glyphicon-download"></i></a></button>',
		enctype: 'multipart/form-data',
		language: 'zh',
		showCaption: false,
		showRemove: false,
		showUpload: false,
		showClose: false,
		showBrowse: false
	});
	
});

function isPicture(fileName){
	var r= fileName.match(/\.(gif|png|jpe?g)$/i);
	if(r==null){
		return "object";
	}
	return "image";
}



