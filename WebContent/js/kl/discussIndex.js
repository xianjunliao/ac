	//*************************************搜索模块********************************************************/
$(function(){
	var search = {
		init:function(){
			//this.enterEvent();
			this.showCurrent();
			this.pulllistEvent();
		},
		showCurrent:function(){
			var uri = location.href;
			var points = ['-5px','53px','110px'];
			var currentPage = (uri.indexOf('klExperienceCollectPage')!=-1&&'0')||(uri.indexOf('klCollectPage')!=-1&&'0')||(uri.indexOf('klSharePage')!=-1&&1)||(uri.indexOf('klMessagePage')!=-1&&2)||'-1';
			if(currentPage !== '-1'){
			    $('#red_bLine').show().css('left',points[currentPage]);
			}
		},
		pulllistEvent:function(){
			$('.showlist').click(function(e){
				e = e || window.event;
				var ele = e.target || e.srcElement;
				$('.assortment').html($.trim($(ele).text())+' <span class="caret"></span>');
				$('#searchInput').css('text-indent',$.trim($(ele).text()).length+2+'em');
			});
		}
		
	};
	search.init();
	/*************************************搜索模块********************************************************/
	//分类获取
	$.ajax({
			url:'/klIndexController/getTypeDetail',
			type:'get',
			success:function(data){
				$('.section>.classify ul.ify-first').html('');
				$('.search ul.showlist').html('');
				$('<li><a href="javascript:void(0);">全部分类</a></li>').appendTo($('.search ul.showlist'));
				var num = 0;
				for(var pname in data){
					var secondContent = '',
						subContent = '';
					$.each(data[pname],function(){
						secondContent+='<li><a href="/klIndexController/doSearch?typeName='+this+'">'+this+'</a></li>';
						subContent+='<li><a>'+this+'</a></li>';
					});
					var icon = ['yw','lc','ywi','gl']
					$('<li class="clearfix"><a href="/klIndexController/doSearch?bigTypeName='+pname+'" class="write-color"><i  class="minicon minicon-'+icon[num]+'"></i> '+pname+'</a><div><ul class="ify-second">'+secondContent+'</ul></div></li>').appendTo($('.section>.classify ul.ify-first'));
					$('<li class="dropdown-submenu"><a href="javascript:void(0);" onClick="">&nbsp;&nbsp;'+pname+'</a><ul class="dropdown-menu">'+subContent+'</ul></li>').appendTo($('.search ul.showlist'));
					num++;
				}
			}
		});		
	/************************************公告栏滚动******************************************/
	!function(){
		var content = [],	
		roll =$("#roll"),
		count = 0;
		//获取
		$("#roll").find('li').each(function(){
			content.push($(this).html());
			//$("<li>"+$(this).html()+"</li>").appendTo($('#roll'));
		});
		//展示
		var len = content.length;	
		roll.css('top','0');
		var timer = setInterval(loop,10);
		$(document).on('mouseenter','#roll a',function(){
			timer = clearInterval(timer);
			timer = null;
			console.log(timer);
			
		});	
		$(document).on('mouseout','#roll a',function(){
			timer = setInterval(loop,10);
			console.log('out');
		});
		function loop(){
			if(parseInt(roll.css('top')) == '-25') {		
				roll.children().first().remove();
				roll.css('top','-0.2px');
				$("<li>"+content[count]+"</li>").appendTo($('#roll'));
				count++;
				if(count == len) count = 0 ;
			}
			roll.css('top',(parseFloat(roll.css('top')) - 0.2 )+"px");
		}
	};
	/************************************公告栏滚动******************************************/
	//document事件

	/*************留言弹窗********************/
	$('#leaveWord').click(function(){
		layer.open({
			title: '留言',
			type: 2,
			area: ['800px', '560px'], //宽高
			content: 'toPage/klLeaveMessage'
		});
	});
});
	/********************************************/