$(function(){
	$.fn.fileinput.defaults.allowedPreviewTypes=['image'];
	 var Fileinput = $.fn.fileinput.Constructor;
	 Fileinput.prototype.refreshOption = function () {
         this.otherActionButtons = '';
	 };
	    
    $.extend($.fn.fileinput.defaults.layoutTemplates,{actions:'<div class="file-actions">\n' +
        '    <div class="file-footer-buttons">\n' +
        '       {other} {upload} {delete} {zoom}' +
        '    </div>\n' +
        '    {drag}\n' +
        '    <div class="file-upload-indicator" title="{indicatorTitle}">{indicator}</div>\n' +
        '       <div class="clearfix"></div>\n' +
        '    </div>'});

    var refreshFlag = true;
	$(document).on("click","#uploadFiles",function () {
	    if(refreshFlag){
	    	$("#uploadFiles").fileinput('refreshOption');
	    	refreshFlag = false;    
	    }
	});      
	
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
				var ipc = {type: isPicture(sysFile.fileName), size: sysFile.fileSize, caption: sysFile.fileName, extra: {id: sysFile.id}};
				initialPreviewConfig.push(ipc);
			})
		},
	});
	
	$("#uploadFiles").fileinput({
		previewSettings : {
	        image: {width: "170px", height: "140px"},
	        other: {width: "184px", height: "140px"}
	    },
	    initialPreview: initialPreview,
	    initialPreviewAsData: true,
	    initialPreviewConfig: initialPreviewConfig,
	    otherActionButtons:'<button type="button" style="padding:0;" class="kv-preview-dl btn btn-xs btn-default" title="下载"><a download="{caption}" href="{data}" style="padding:7px 12px;"><i class="glyphicon glyphicon-download"></i></a></button>',
	    overwriteInitial: false,
	    deleteUrl: sysFileUrl + "/doDel",
		uploadUrl: sysFileUrl + '/doUploadFiles?keyId=' + keyId + '&type=' + type,
		maxFileSize: '20480',
		enctype: 'multipart/form-data',
		language: 'zh',
		showCaption: false,
		showClose: false
	});
	
});

function isPicture(fileName){
	var r= fileName.match(/\.(gif|png|jpe?g)$/i);
	if(r==null){
		return "object";
	}
	return "image";
}


