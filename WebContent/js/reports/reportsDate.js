$(function() {
	var now = new Date($("#currDate").val());
	$(document).on('click','.quiteTime>a',function(){
	    $('.quiteTime>a').removeClass('active');
	    $(this).addClass('active');
	    if($(this)[0].id=='thisWeek') {
	    	$("#startDate").val(getWeekStartDate);
			$("#endDate").val(getCurrentDate);
	    }
	    if($(this)[0].id=='lastWeek'){
	    	$("#startDate").val(getUpWeekStartDate);
			$("#endDate").val(getUpWeekEndDate);
	    }
	    if($(this)[0].id=='thisMonth'){
	    	$("#startDate").val(getMonthStartDate);
			$("#endDate").val(getCurrentDate);
	    }
	    if($(this)[0].id=='lastMonth'){
	    	$("#startDate").val(getLastMonthStartDate);
			$("#endDate").val(getLastMonthEndDate);
	    }
	    if($(this)[0].id=='twelveWeek'){
	    	$('#weekRadio').click();
	    	$("#startDate").val(getSeviceDate.twelveWeek());
			$("#endDate").val(getCurrentDate);
			
	    }
	    if($(this)[0].id=='twelveDay'){
	    	$('#dayRadio').click();
	    	$("#startDate").val(getSeviceDate.twelveDay());
			$("#endDate").val(getCurrentDate);
			
	    }
	    if($(this)[0].id=='twelveMonth'){
	    	$('#monthRadio').click();
	    	$("#startDate").val(getSeviceDate.twelveMonth);
			$("#endDate").val(getCurrentMonthDate);
			
	    }
	    $("#refreshTableBtn").click();
	    $("#refreshEchartsBtn").click();
	});
	$('#dayRadio').change(function(){
	     $("#startDate").off('focus').val('');
	      $('#endDate').off('focus').val('');
	     $("#startDate").on("focus",function(){
	    	 WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}',isShowClear:true,readOnly:true});
		 });
		  $('#endDate').on("focus",function(){
			  WdatePicker({maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true,readOnly:true}) ;
		 });
	});
	$('#weekRadio').change(function(){
	     $("#startDate").off('focus').val('');
	      $('#endDate').off('focus').val('');
	      $("#startDate").on("focus",function(){
		    	 WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}',isShowClear:true,readOnly:true});
			 });
			  $('#endDate').on("focus",function(){
				  WdatePicker({maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true,readOnly:true}) ;
			 });
	});
	$('#monthRadio').change(function(){
		$("#startDate").off('focus').val('');
	      $('#endDate').off('focus').val('');
	     $("#startDate").on("focus",function(){
		    	  WdatePicker({dateFmt:'yyyy-M',maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}',isShowClear:true,readOnly:true});
		 });
		  $('#endDate').on("focus",function(){
		    	   WdatePicker({dateFmt:'yyyy-M',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true,readOnly:true});
		 });
	});
})