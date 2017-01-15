	$(document).on('click','.closeBtn',function(){
		// console.log(this);
		// var labels = $(this).parent().attr('id');
		var ids = $(this).parent().attr('href').slice(1);	
		removeActives();
		$("#"+ids).remove();
		$(this).parent().parent().remove();
	});
	function removeActives(){
		$("#myTabs").find('li').removeClass('active');
		$("#myTabs").find('li').first().addClass('active');
		$("#myTabContent").find('div').removeClass('active in');
		$("#myTabContent").find('div').first().addClass('active in');
	}
	function removeParentsTab(){
		console.log($(window.frameElement));
		console.log($('#'+ids+'-tab',window.parent.document));
		var ids = $(window.frameElement).data('labelid');
		window.parent.removeActives();
		$('#'+ids+'-tab',window.parent.document).parent().remove();
		$("#"+ids,window.parent.document).remove();
		
		
	}
	$(document).on('click','.addTabBtn',function(){
		//获取本行数据
		var pas,datas,names,exid;
		try{
			pas = $(this).parent().parent().data('index');
			datas = $('#datagrid').bootstrapTable('getData',true);
			names = datas[pas].name;
			exid = datas[pas].id;
		}catch(e){
			names = '业务办理';
		}
		var srcURL = dealWithTask(exid);
		var temURL = 'http://localhost:8080//cpOrderOvertimeController/orderOvertimeList';
		//计算页面大小
		var sWidth=window.screen.width;
		var sHeight=$(window).height();//	 
	    // if(sHeight<400)
	    //	sHeight=400;
//		$("body").width(sWidth);
//		$("body").height(sHeight);
//		$(".icon-button").height(sHeight);
//		$("#content").width($("body").width()-200);	
		
		var height = $('#content', window.parent.document).height() - $("#myTabs").outerHeight();
		console.log($('#content', window.parent.document).height());
		//tab  tempele
		var footTemp = '<div><table><tbody><tr style="height:55px;"><th style="width:80px;"><font color="red">*</font>批注信息：</th><td><textarea class="form-control" style="width:100%;resize:none;" id="comment" name="comment" rows="2" cols="200" data-rule="required;true"></textarea></td></tr></tbody></table><table class="table table-striped table-bordered"><tbody><tr><td style="font-weight:bolder;">时间</td><td style="font-weight:bolder;">审批人</td><td style="font-weight:bolder;">审批信息</td></tr></tbody></table><input type="button" id="passed" class="btn btn-info" style="width: 100px;margin-bottom:15px;margin-left: 500px" value="审核通过"></div>';
		var ids = parseInt((new Date()).getTime());
		// var ids2 = parseInt(ids.toString(2).selice(0,6));
		var tabTemp = '<li role="presentation" class=""><a href="#'+ids+'" role="tab" id="'+ids+'-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">'+names+'<i class="glyphicon glyphicon-remove closeBtn"></i></a></li>';
		var  tabContent = '<div role="tabpanel" class="tab-pane fade" id="'+ids+'" aria-labelledby="'+ids+'-tab"><div  style="height:'+height+'px;margin:0;padding:0;overflow:hidden;"><iframe data-labelid="'+ids+'" id="mainFrame" name="mainFrame" marginheight="0" border="0" frameborder="0" style="border-radius:5px;width: 100%; height: 100%; margin: 0px; padding: 0px;" src="'+srcURL+'"></iframe></div></div>';
		$('#myTabs').append(tabTemp);
		$('#myTabContent').append(tabContent);
		$("#myTabs").find('li').removeClass('active');
		$("#myTabContent").find('div').removeClass('active in');
		$("#"+ids+'-tab').parent().addClass('active');
		$("#"+ids).addClass('active in');
	});