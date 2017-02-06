$(function(){
		var axHeight=0;
		var fixHeight = 0;
	 if (!!window.ActiveXObject || "ActiveXObject" in window) {
		 // axHeight=68;
		fixHeight = 4;
	 }
	//左侧收起标示
	var lefthide = false;
	//控制页面布局
	var sWidth=window.screen.width;
	var sHeight=$(window).height();//	 
	if(sWidth<1366)
		sWidth=1366;
    // if(sHeight<400)
    //	sHeight=400;
	$("body").width(sWidth-fixHeight);
	$("body").height(sHeight-axHeight);
	// $(".icon-button").height(sHeight);
	$("#content").width($("body").width()-180);
	$("#leftMenu").height($("body").height()-71);
	$("#content").height($("body").height() - 71+18);
    $(window).resize(function(){
    	 sWidth=window.screen.width;
    	 sHeight=$(window).height();
    	 if(sWidth<1366) sWidth=1366;
    	 $("body").width(sWidth);
    		$("body").height(sHeight);
    		if(lefthide){
    			$("#content").width($("body").width()-0);
    		}else{
    			$("#content").width($("body").width()-180);
    		}		
    	$("#content").height($("body").outerHeight() - 71);
    	$("#leftMenu").height($("body").height()-71);
    	// alert($("#content").height());
    	// console.log($("#content").height());
      });
    
    
    $("#leftMenu").resize(function(){
    	console.log($("#leftMenu").scrollTop());
    	if($("#leftMenu").scrollTop() == 0) alert("go");
    });
    
    $('.dropdown-toggle').dropdown(); 
	  //侧边栏展开收起
		$("#sidebar .icon-button").click(function(){

			// if($("#sidebar").hasClass("showed")){
				lefthide = true;
				$("#sidebar").removeClass("showed");
				$("#sidebar").addClass("hideed");
				// $("#sidebar").fadeOut();
				$("#sidebar").animate({left:'-180px'},"1s");
				// $(this).toggleClass("  glyphicon-menu-right");
				// $("#content").width();
				$("#content").animate({width:$("body").width()-0,marginLeft:0},"1s",function(){
					try{
						mainFrame.window.chrildRefresh();
					}catch(e){
						
					}
				});
				$(".icon-button2").animate({left:'5px'},"1s");
			// }else{
				
				// $(document.body).animate({opacity:'1'},"1s");
				// $(this).toggleClass("  glyphicon-menu-right");
			// }
		});
		$("#sidebar .icon-button2").click(function () {
			lefthide = false;
				$("#sidebar").removeClass("hideed");
				$("#sidebar").addClass("showed");
				// $("#sidebar").fadeIn();
				$(".icon-button2").animate({left:'-120px'},"1s");
				$("#sidebar").animate({left:'0px'},"1s");
				$("#content").animate({width:$("body").width()-180,marginLeft:"180px"},"1s",function(){
					try{
						mainFrame.window.chrildRefresh();
					}catch(e){
						
					}
					
				});
		});


		$("#sidebar ul li a").click(function() {
			$("#leftMenu a").removeClass("active");
			$(this).addClass("active");
			$("#sidebar ul li").removeClass("active");
			$(this).parent().addClass("active");
			$(this).children().first().toggleClass(" glyphicon-menu-down");
			$(this).parent().siblings().children("a").children("span").removeClass(" glyphicon glyphicon-menu-down");
			$(this).parent().siblings().children("a").children("span").addClass(" glyphicon glyphicon-menu-right");
			
			
			$("#leftMenu a").removeClass("topLevel");
			$(this).addClass("topLevel");
			$("#sidebar ul li").removeClass("active");
			$(this).parent().addClass("active");
			 
		
		});
		var exClick = false;
		$("#sidebar ul li ul li a").click(function() {
			// $("#leftMenu a").removeClass("topLevel");
			// $(this).addClass("topLevel");
			removeHref(this);

			$(this).parent().parent().parent().addClass(" open active ");
			$(this).addClass("active");
		});
		function removeHref (thisDom){
			if(exClick) return false;
			exClick = true;
			var href;
			setTimeout(function(){
				href = $(thisDom).attr('href');
				$(thisDom).attr('href','javascript:;');
			},0);
			
			setTimeout(function(){
				exClick = false;
				$(thisDom).attr('href',href);
			},1000);
		}
		
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
	        				$("#leftMenu a").removeClass("active");
	        				$("#sidebar ul li").removeClass("open active");
	        				var sia = $("#sidebar ul li a");
//	        				$('#leftMenu>.open').children("a").children("span").toggleClass("  glyphicon-menu-down");
	        				sia.parent().siblings().children("a").children("span").removeClass(" glyphicon glyphicon-menu-down").addClass(" glyphicon glyphicon-menu-right");
	        				$("#leftMenu a").removeClass("active");
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
	        						$(this).parent().addClass('open active');
	        						$(this).find('span').addClass(" glyphicon glyphicon-menu-down");
	        						if(!loadDate.st2) $('#mainFrame').attr('src',this.href);
	        					}else{
	        						return;
	        					}
	        					if(!!loadDate.st2){
	        						$("#leftMenu a").removeClass("topLevel"); 
	        						var sia = $("#sidebar ul li a");
	    	        				//sia.parent().siblings().children("a").children("span").removeClass(" glyphicon glyphicon-menu-right").addClass(" glyphicon glyphicon-menu-down");
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
	            $('[name="'+name+'"]').css('backgroundColor',"rgb(5, 141, 224)");
//	            $('[name="'+name+'"]').css('backgroundColor',"#1ea0da");
	            /********************************************************************/
	            var header_roll = function(){
	    			var len_li = [];
	    			// !function(){
	    				var all_len = 0,width_val = 0,mix = 0;
	    				$('#header-nav li').each(function (index) {
	    					$(this).attr('data-index',index);
	    					len_li.push($(this).width());	
	    					all_len+=($(this).width());	
	    				});
	    				if(all_len>830) {
	    					$('.cartLeft').show();
	    					$('.cartRight').show();			
	    				}else{
	    					return;
	    				}
	    				$.each(len_li,function () {
	    					mix+=this;
	    					if(mix>=830) return false;
	    					width_val+=this;
	    				});
	    				// 累加
	    				$('#header-nav').width(width_val+1);
	    			// }();
	    			$('.cartRight').click(function(){
	    				var dom = [],all_lent = 0,sublen_li = [];
	    				$('#header-nav li').each(function () {
	    					if($(this).css('width') == '0px') return;
	    					dom.push($(this));
	    					all_lent+=$(this).width();			
	    				});
	    				var mix = 0,width_val = 0;			
	    				if(all_lent<$('#header-nav').width()) return;	
	    					fix_width('left');
	    				dom[0].animate({width:0},'fast',function(){
	    					$(this).hide();
	    				});	
	    		
	    			});
	    			function fix_width(direction) {
	    				var sublen_li = [],mix = 0,width_val = 0,subDom = [];
	    				$('#header-nav li').each(function (index) {
	    					if($(this).css('width') == '0px') return;
	    					subDom.push($(this));
	    					sublen_li.push($(this).width());		
	    				});	
	    				if(direction == 'left'){
	    					sublen_li.shift();
	    				}
	    				if(direction == 'right'){
	    					var index = subDom[0].data('index');
	    					if(index>0) index--;
	    					sublen_li.unshift(len_li[index]);
	    				}
	    				$.each(sublen_li,function () {
	    					mix+=this;
	    					if(mix>=830) return false;
	    					width_val+=this;
	    				});
	    				$('#header-nav').animate({width:width_val+1},'fast');
	    				// $('#header-nav').width(width_val+1);
	    			}	
	    			$('.cartLeft').click(function(){
	    				var dom = [],all_lent = 0;
	    				$('#header-nav li').each(function () {
	    					if($(this).css('width') != '0px') return;
	    					dom.push($(this));
	    					all_lent+=$(this).width();			
	    				});	
	    				if($('#header-nav').find('li').first().width() && $('#header-nav').find('li').first().css('display') != 'none') return;
	    				fix_width('right');
	    				dom[dom.length-1].show();
	    				dom[dom.length-1].animate({width:len_li[dom[dom.length-1].data('index')]},'fast');	
	    			});
	    		};
	    		header_roll();     
	       
});