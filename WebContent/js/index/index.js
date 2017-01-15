$(function(){
	var axHeight=0;
	 if (!!window.ActiveXObject || "ActiveXObject" in window)
		 axHeight=68;
	//左侧收起标示
	var lefthide = false;
	//控制页面布局
	var sWidth=window.screen.width;
	var sHeight=$(window).height();//	 
	if(sWidth<1366)
		sWidth=1366;
    // if(sHeight<400)
    //	sHeight=400;
	$("body").width(sWidth);
	$("body").height(sHeight);
	$(".icon-button").height(sHeight);
	$("#content").width($("body").width()-200);
	
	$("#content").height($("body").outerHeight() - $("#base").outerHeight()-axHeight);
    $(window).resize(function(){
    	 sWidth=window.screen.width;
    	 sHeight=$(window).height();
    	 if(sWidth<1366)
    			sWidth=1366;
    	 $("body").width(sWidth);
    		$("body").height(sHeight);
    		if(lefthide){
    			$("#content").width($("body").width()-10);
    		}else{
    			$("#content").width($("body").width()-200);
    		}
    		
    		$("#content").height($("body").outerHeight() - $("#base").outerHeight()-axHeight);
      });
    
    
    
    
    $('.dropdown-toggle').dropdown(); 
	  //侧边栏展开收起
		$("#sidebar .icon-button i").click(function(){

			if($("#sidebar").hasClass("showed")){
				lefthide = true;
				$("#sidebar").removeClass("showed");
				$("#sidebar").addClass("hideed");
				// $("#sidebar").fadeOut();
				$("#sidebar").animate({left:'-180px'},"1s");
				$(this).toggleClass("  glyphicon-menu-right");
				// $("#content").width();
				$("#content").animate({width:$("body").width()-20,marginLeft:20},"1s",function(){
					try{
						mainFrame.window.chrildRefresh();
					}catch(e){
						
					}
				});
			}else{
				lefthide = false;
				$("#sidebar").removeClass("hideed");
				$("#sidebar").addClass("showed");
				// $("#sidebar").fadeIn();
				$("#sidebar").animate({left:'0px'},"1s");
				$("#content").animate({width:$("body").width()-200,marginLeft:"200px"},"1s",function(){
					try{
						mainFrame.window.chrildRefresh();
					}catch(e){
						
					}
					
				});
				// $(document.body).animate({opacity:'1'},"1s");
				$(this).toggleClass("  glyphicon-menu-right");
			}
		});
		$("#sidebar ul li a").click(function() {
			$("#leftMenu a").removeClass("topLevel");
			$(this).addClass("topLevel");
			$("#sidebar ul li").removeClass("active");
			$(this).parent().addClass("active");
			 
			$(this).children().first().toggleClass(" glyphicon-menu-down");
			$(this).parent().siblings().children("a").children("span").removeClass(" glyphicon glyphicon-menu-down");
			$(this).parent().siblings().children("a").children("span").addClass(" glyphicon glyphicon-menu-right");
		});
		$("#sidebar ul li ul li a").click(function() {
			$("#leftMenu a").removeClass("topLevel");
			$(this).addClass("topLevel");
			$(this).parent().parent().parent().addClass(" open active ");
			$(this).addClass("active");
		});
		
	        	//记住一级菜单状态
//	        	$("[name='${menu}']").css("background-color","#00A3D6");
//				$("[name='${menu}']").parent().siblings().children().css("background-color","#01BCEF");
              
				$(function(){
				//设置加载页面
				$("#mainFrame").attr("src",$("a[target='mainFrame']:first").attr("href"));
				//改变选中菜单样式
				var topLevel = $("#leftMenu").first('li').find('ul');
				if(!!topLevel.length){
					$("a[target='mainFrame']:first").parent().parent().parent().children().children().first().addClass("glyphicon glyphicon-menu-down");
				}else{
					$("a[target='mainFrame']:first").addClass('topLevel');
				}	
				$("a[target='mainFrame']:first").addClass("active");
				$("a[target='mainFrame']:first").parents("li").attr("class","submenu open active");
				
				
				
				 var reload = function () {
	        			var loadDate;
	        			if(supports_storage){
	        				loadDate = localStorage.getItem('menu');
	        			}else{
	        				loadDate = document.cookie;
	        				if(!loadDate) return;
	        					var sts = loadDate.split(';');var a =0;
	        					for(var i in sts){ 
	        						if(sts[i].indexOf('menu') != -1){
	        							a = i;
	        						}
	        					}
	        					loadDate = sts[a].split('=')[1];
	        			}
	        			if(!loadDate) return;			
	        			//清楚数据
	        			if(supports_storage){
	        				localStorage.removeItem('menu');
	        			}else{
	        			  	var exp = new Date();
	        			  	exp.setTime(exp.getTime() + (-1 * 24 * 60 * 60 * 1000));
	        			  	document.cookie = "menu=" + loadDate + "; expires=" + exp.toGMTString();				
	        			}
	        			loadDate = JSON.parse(loadDate); //格式化
	        			//清除所有菜单状态
	        				$('.menu>ul>li>a').each(function(){
	        					$(this).css('backgroundColor','inherit');
	        				});	
	        				$("#leftMenu a").removeClass("topLevel"); 
	        				$("#sidebar ul li").removeClass("active");
	        				var sia = $("#sidebar ul li a");
//	        				$('#leftMenu>.open').children("a").children("span").toggleClass("  glyphicon-menu-down");
	        				sia.parent().siblings().children("a").children("span").removeClass(" glyphicon glyphicon-menu-down").addClass(" glyphicon glyphicon-menu-right");
	        				$("#leftMenu a").removeClass("topLevel");
	        			//添加状态
	        				$('.menu>ul>li>a').each(function(){
	        					if(loadDate.st0 == $(this).text().trim()){
	        						$(this).css('backgroundColor','#00A3D6');
	        					}
	        				});
	        				//左侧菜单
	        				//load iframe
	        				$("#leftMenu>.submenu>a").each(function(){
	        					var top2 = $(this).text().trim();
	        					if(loadDate.st1 == $(this).text().trim()){
	        						$(this).addClass("topLevel");
	        						$(this).parent().addClass(' active');
	        						if(!loadDate.st2) $('#mainFrame').attr('src',this.href);
	        					}else{
	        						return;
	        					}
	        					if(!!loadDate.st2){
	        						$("#leftMenu a").removeClass("topLevel"); 
	        						var sia = $("#sidebar ul li a");
	    	        				sia.parent().siblings().children("a").children("span").removeClass(" glyphicon glyphicon-menu-down").addClass(" glyphicon glyphicon-menu-right");
	        						$(this).parent().find('ul').each(function(){
	        							if(loadDate.st2 == $(this).find('li').find('a').text().trim()){
	        								$('#mainFrame').attr('src',$(this).find('li').find('a')[0].href);
	        								$(this).find('li').find('a').addClass("topLevel");
	        								$(this).find('li').addClass(' open active');
	        								$(this).find('li').find('a').addClass(' active');
	        							}	
	        						});
	        					}else{
	        						$(this).addClass(' topLevel active');						
	        					}
	        				});
	        		};
	        		reload();
				});
				


	      //获取当前菜单
	      //通用js文件

	            var name = location.search.split("=")[1];
	            $('[name="'+name+'"]').css('backgroundColor',"#00A3D6");
	            /********************************************************************/
	           


	       
});