    	$(function(){
    		$('#feedBackDg').datagrid({
    	        width: 'auto', 
    	        height: 'auto', 
    	        nowrap: false, 
    	        striped: true, 
    	        border: true, 
    	        fitColumns:true,
    	        collapsible:false,//是否可折叠的 
    	        fit: true,//自动大小 
    	        url:'getFeedBckByJson', 
    	        remoteSort:false,  
    	        idField:'fldId', 
    	        singleSelect:true,//是否单选 
    	        pagination:true,//分页控件 
    	        rownumbers:true,//行号 
    	        toolbar: '#tb',
    	        columns:[[
    	      			{field:'feedbackId',title:'反馈标识号',width:100,align:'center'},
    	      			{field:'feedbackContent',title:'反馈内容',width:600,align:'left'},
    	      			{field:'questionContent',title:'知识标题',width:400,align:'left'},
    	      			{field:'questionId',title:'知识id',hidden:true,width:400,align:'center'},
    	      	    ]]
    	    }); 
    	    //设置分页控件 
    	    var p = $('#feedBackDg').datagrid('getPager'); 
    	    $(p).pagination({ 
    	        pageSize: 15,//每页显示的记录条数，默认为10 
    	        pageList: [15,20],//可以设置每页记录条数的列表 
    	        beforePageText: '第',//页数文本框前显示的汉字 
    	        afterPageText: '页    共 {pages} 页', 
    	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
    	    });  
    		
      	 $("#edit").click(function(){
      		var row = $('#feedBackDg').datagrid('getSelected');
      		if (row){
      			var url = 'getQuestionFeedBack';
      			var kbsQuestionId = row.questionId;
      			var feedbackId = row.feedbackId;
      			location.href="getQuestionFeedBack?kbsQuestionId="+kbsQuestionId+"&feedbackId="+feedbackId;
      		}
      		else{
      			alert("您没有选中任何对象！");
      		}
      	 });
    });  
