//locastorage兼容性封装
	        /**
	         * @parame  {st0:'',st1:'',st2:''}
	         * 	st0:顶层菜单
	         *  st1:左侧菜单第一级
	         *  st2:左侧菜单第二级
	         * **/
	        var save = function (parame){
	        	// parame = string;
	        	var _parame = parame;
	        	parame = JSON.stringify(parame);
	        	supports_storage?localStorage.setItem('menu',parame):document.cookie = 'menu='+ parame;	
	        	console.log(parame);
	        	window.parent.f_click(_parame.st0);
	        };
	        function f_click(menuName){
	        	$('#header-nav>li>a').each(function(){
	        		if(menuName == $(this).text().trim()){
	        			location.href = $(this)[0].href;
	        		}
	        	});
	        }
	        function supports_storage() { 
	        	try { 
	        	    return 'localStorage' in window && window['localStorage'] !== null; 
	        	} catch (e) { 
	        	    return false; 
	        	} 
	        }
//跨页面传参
	      	var keyDate = function (key,value) {
	      		var _parame = value;
	    			parame = JSON.stringify(value);
	    			supports_storage?localStorage.setItem(key,parame):document.cookie = key+'='+ parame;
	      	};
	      	var getKeyDate = function (key) {
	      		var loadDate;
	    			if(supports_storage){
	    				loadDate = localStorage.getItem(key);
	    			}else{
	    				loadDate = document.cookie;
	    				if(!loadDate) return;
	    				var sts = loadDate.split(';');var a =0;
	    				for(var i in sts){ 
	    					if(sts[i].indexOf(key) != -1){
	    						a = i;
	    					}
	    				}
	    				loadDate = sts[a].split('=')[1];
	    			}
	    			if(!loadDate) return;
	    			//清楚数据
	    			if(supports_storage){
	    				localStorage.removeItem(key);
	    			}else{
	    			  	var exp = new Date();
	    			  	exp.setTime(exp.getTime() + (-1 * 24 * 60 * 60 * 1000));
	    			  	document.cookie = key+"=" + loadDate + "; expires=" + exp.toGMTString();				
	    			}
	    			return JSON.parse(loadDate);
	      	};
var pageData={};
//扩展Date的format方法   
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1,  
        "d+": this.getDate(),  
        "h+": this.getHours(),  
        "m+": this.getMinutes(),  
        "s+": this.getSeconds(),  
        "q+": Math.floor((this.getMonth() + 3) / 3),  
        "S": this.getMilliseconds()  
    }
        if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    }  
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}  

/**   
 *转换long值为日期字符串   
 * @param l long值   
 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
 * @return 符合要求的日期字符串   
 */    

 function getFormatDateByLong(l, pattern) {
     if(pattern==null||pattern==undefined)
         pattern='yyyy-MM-dd';
     if(l==undefined||l=='')
         return '-';
     return getFormatDate(new Date(l), pattern);  
 } 
 
 /**   
  *转换日期对象为日期字符串   
  * @param l long值   
  * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
  * @return 符合要求的日期字符串   
  */    
 
 function timeFormatter(value, row, index) {	 
        var day = new Date(value.time);
        var CurrentDate = ""; 
        //var CurrentDate = day.toLocaleTimeString(); 
        if(day!=null){
        var Year = 0; 
        var Month = 0; 
        var Day = 0; 
        var Hour = 0;
        var Minute = 0;
        var Second = 0;
        
        //初始化时间 
        //Year= day.getYear();//有火狐下2008年显示108的bug 
        Year= day.getFullYear();//ie火狐下都可以 
        Month= day.getMonth()+1; 
        Day = day.getDate(); 
        Hour = day.getHours(); 
         Minute = day.getMinutes(); 
         Second = day.getSeconds(); 
        CurrentDate += Year + "-"; 
        if (Month >= 10 ) 
        { 
        CurrentDate += Month + "-"; 
        } 
        else 
        { 
        CurrentDate += "0" + Month + "-"; 
        } 
        if (Day >= 10 ) 
        { 
        CurrentDate += Day + " " ; 
        } 
        else 
        { 
        CurrentDate += "0" + Day + " "; 
        } 
        if (Hour >= 10 ) 
        { 
        CurrentDate += Hour + ":"; 
        } 
        else 
        { 
        CurrentDate += "0" + Hour + ":"; 
        } 
        if (Minute >= 10 ) 
        { 
        CurrentDate += Minute + ":" ; 
        } 
        else 
        { 
        CurrentDate += "0" + Minute + ":" ; 
        } 
        if (Second >= 10 ) 
        { 
        CurrentDate += Second ; 
        } 
        else 
        { 
        CurrentDate += "0" + Second  ; 
        } 
        }
        return CurrentDate; 
    } 
 
  function getFormatDate(date, pattern) {  
      if (date == undefined) {  
          date = new Date();  
      } 
      if (pattern == undefined) {  
          pattern = "yyyy-MM-dd hh:mm:ss";  
      }  
      return date.format(pattern);  
  }
  
  function formatDate(v,r,i){
      return getFormatDateByLong(v);
  }
  
  function formatDate2(v,r,i){
      return getFormatDateByLong(v, "yyyy/MM/dd");
  }
  
  function formatDate3(v,r,i){
      return getFormatDateByLong(v, "MM/dd");
  }
 
  function formatDateTime(v,r,i){
	  if(v == null){
		  return v;
	  }else{
		  return getFormatDateByLong(v,"yyyy-MM-dd hh:mm:ss");
	  }
  }
  function formatDateTime2(v,r,i){
      return getFormatDateByLong(v,"yyyy-MM-dd hh:mm");
  }
  function formatTime(v,r,i){
      return getFormatDateByLong(v,"hh:mm:ss");
  }
  
  var tipClose;
  //表格字段提示框
  function infoTip(v,aId){
         tipClose = layer.tips(v+"<div style='text-align:center;width:100%;color:white;'><a href='javascript:closeTip()' style='color:white;'>关闭</a></div>","#"+aId,{
            tips: [1, '#3595CC'],
            time:5000000000
        });
  }
  
  function closeTip(){
      layer.close(tipClose);
  }
  
  function checkData(v) {
      var  entry = { "'": "’", '"': '“', '<': '＜', '>': '＞','&lt;':'＜','&lt;':'＞' };
       v = v.replace(/(['")-><&\\\/\.])/g, function ($0) { return entry[$0] || $0; });
      return v;
  }
  
  var tipIndex=0;
  function contentTip(v,r,i){
    var aId="tip_"+i+"_"+(tipIndex++);
    var str;
    var lenStr='';
    if(v!=null && v!=''&&v!='undefined'){
        var reg=/&#[0-9]{5};/gm;
        lenStr=v.replace(reg,'AA');
        v= checkData(v);
        if(lenStr.length>11){
            str=v;
             //str=v.substr(0,64)+"...";
             return '<a id="'+aId+'" href="javascript:infoTip(\''+v+'\',\''+aId+'\');">'+str+'</a>';
        }else
            str=v;
         return  v;
    }else{
     return  v;
    }
  }
  
  var afterClose; 
 //页面弹出层固定在 祖先页面
  function openWindow(type,title,width,height,content,operation,callBack){
      var topWin= (function (p,c){
            while(p!=c){
                c = p;       
                p = p.parent;
            }
            return c;
        })(window.parent,window);
     return topWin.topWindow(type,title,width,height,content,operation,callBack);
  }
 
  function getTopwindow(){
      return (function (p,c){
            while(p!=c){
                c = p;       
                p = p.parent;
            }
            return c;
        })(window.parent,window);
  }
  
  function myAlert(content,opt){
     layer.alert(content,opt);
  }
  
  function myMsg(content){
      layer.msg(content);
  }
  
  function myConfirm(content,btns,success,cancel){
      var topWin= (function (p,c){
            while(p!=c){
                c = p;       
                p = p.parent;
            }
            return c;
        })(window.parent,window);
     return topWin.confirm2(content,btns,success,cancel);
  }  
  
  function confirm2(content,btns,success,cancel){
      var index;
      var result=true;
      index=layer.confirm(content,
                btns?btns:{
            btn : [ '确认','取消' ]
        //按钮
        },
        function() {
            success();
            layer.close(index);
        },
        function() {
            if(cancel)
            cancel();
            layer.close(index);
        });
  }
  /*
    zIndex: layer.zIndex, //重点1
            success: function(layero){
                layer.setTop(layero); //重点2
            }
   * */
  function topWindow(type,title,width,height,content,operation,callBack){
      var index = layer.open({
            type:type,
            title:title,
            shadeClose:false,
            shade:[0.3, '#000'],
            content:content,
            area:[width,height]
        });
      afterClose=operation;
      return index;
  }
  function closeDialogAll(type){
      if(!!type){
    	  console.log(type);
    	  layer.closeAll(type);
      }else{
    	  layer.closeAll();
      }
     
  }
  function closeDialog(index){
      if(index!=undefined)
      layer.close(index);
      if(afterClose)
      afterClose();
  }
 
  function fclosed(index,type){
        parent.layer.close(index);
        window.frames["mainFrame"].window.closed(type);
    }
  function fclosed1(index,type){
        parent.layer.close(index);
        window.frames["mainFrame"].window.closedd(type);
    }
  function fclosed2(index,type){
    
        parent.layer.close(index);
        window.frames["mainFrame"].window.closed(type);
    }
  function fclosedinfo(){
        window.frames["mainFrame"].window.refreshtable();
        window.frames["mainFrame"].window.timeoc();
    }
  function chrildRefresh(){
        //alert("complete");
        $('#datagrid').bootstrapTable("refresh");
    }
//禁用带.boxdisabled checkbox
var disableCheck=(function(){
  var disabled = function(){
      var tr = $("span.boxDisable").parent().parent();
      tr.find('[name="btSelectItem"]').parent().text("-");
  };
  return {disabled:disabled}
})()

function getFormatter(id){
	var obj = {};
	$("#"+id).find("option").each(function(){
		obj[$(this).val()] = $(this).text();
	});
	return obj;
}

//下拉插件验证提示位置修复
function fixPosition(id) {
    var width = $("[data-id="+id+"]").outerWidth();
    $('<style>#'+id+'+span>span.msg-wrap{left:'+width+'px;}</style>').appendTo($('head'));
}

//fomater  yyyy-mm-dd
function yyyymmdd(times){
	var dates = new Date(times),
	 	year = dates.getFullYear(),
	 	mon =dates.getMonth()+1,
	 	day = dates.getDate(); 
	return year+"-"+mon+"-"+day;
}

//ajax方法扩展
;(function($){
    //备份jquery的ajax方法
    var _ajax=$.ajax;
    
    //重写jquery的ajax方法
    $.ajax=function(opt){
        //备份opt中error和success方法
        var fn = {
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            success:function(data, textStatus){}
        }
        if(opt.error){
            fn.error=opt.error;
        }
        if(opt.success){
            fn.success=opt.success;
        }
        
        //扩展增强处理
        var _opt = $.extend(opt,{
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //错误方法增强处理
                 fn.error(XMLHttpRequest, textStatus, errorThrown);
                var status = XMLHttpRequest.status;
                console.log(status);
                switch(status){
                case 401:
                	location.href = "/errors/error401";
                	break;
                default:
                	location.href = "/errors/error500";
                };
            },
            success:function(data, textStatus){
                //成功回调方法增强处理
                fn.success(data, textStatus);
            }
        });
        _ajax(_opt);
    };
})(jQuery);

//文件导入
function initImportFile(ctrlName) {    
    ctrlName.fileinput({
        language: 'zh', //设置语言
        allowedFileExtensions : ['xls','xlsx'],//接收的文件后缀
        //showUpload: false, //是否显示上传按钮
        //showCaption: false,//是否显示标题
        browseClass: "btn btn-primary",//按钮样式
        showPreview : false,
        showRemove :false,
        showUpload : false
    });
}
//毫秒转换  hh:mm:ss
function formateHMS(ms){
    var h = 0,m = 0,s = 0;
    ms = Math.floor(ms/1000);
    s = parseInt((parseFloat((parseFloat(ms / 3600.0) - parseInt(ms / 3600.0)) * 60) - parseInt((parseFloat(ms / 3600.0) - parseInt(ms / 3600.0)) * 60)) * 60);
    s = s<10?"0"+s:s;
    m = parseInt((parseFloat(ms / 3600.0) - parseInt(ms / 3600.0)) * 60);
    m = m<10?"0"+m:m;
    h = parseInt(ms / 3600.0);
    h = h<10?"0"+h:h;
    return h+":"+m+":"+s;
}

function refreshTable(id){
	$('#'+id).bootstrapTable('refresh',{query: {offset: 0}});
}

//默认不用传参，要限制mytable的范围需加上父标签id
function resetParams(id){
	$('#' + id + ' .mytable [id]').val("");
	$('.selectpicker').selectpicker('deselectAll');
}


//金额不小于0规则
$(function(){

	
	
});

function addTitle(v){
	return "<span title='" + v + "'>" + v + "</span>";
}



$(document).on('click','#moreBtn',function(){
	if($(this).hasClass('slideDown')){
		$(this).toggleClass('slideDown slideUp');
		$('.mytable tr').eq(1).slideDown(function(){
			$('#datagrid').bootstrapTable('resetView',{height : $(window).height()-$("#formTool").height()-10});
		});
	}else{
		$(this).toggleClass('slideDown slideUp');
		$('.mytable tr').eq(1).slideUp(function(){
			$('#datagrid').bootstrapTable('resetView',{height : $(window).height()-$("#formTool").height()-10});
		});
	}	
});


/***************************/
