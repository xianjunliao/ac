var character = true;
var conter = 0, n = 0;
function iFrameHeight() {
	n++;
	$(this).addClass('loaded');
	// console.log(this);
	if (conter == n){
		$('.shadeWrap').hide();
	}		
	// }
}
window.onunload = unload;
function unload (e){
  window.scrollTo(0,0);
}
function setPickerVal(value){
	$('#province').selectpicker("val",value);
}
function refreshPage(){
	$('#branchTabContent').find('.content').remove();
}
$(function() {
	$('.selectpicker').selectpicker('render');//selected 插件初始化
	// 一级标题切换事件
	$('.header-navlist>li').click(function() {
		$('.header-navlist>li').removeClass('active');
		$(this).addClass('active');
		var ids = [ 'headquarters', 'branch', 'ECData' ];
		$.each(ids, function() {
			$('#' + this).hide();
		});
		var id = $(this).data('id');
		$('#' + id).show();
		// 加载首页
		loadIframe[id]();
	});
	// 二级菜单切换
	var subMenu = {};
	$('ul.nav-tabs>li').click(function(e) {
		var href = $(this).find('a')[0].hash.replace('#', "");
		if (href.indexOf('hq') !== -1) {
			loadIframe.headquarters(href);
		} else if (href.indexOf('branch') !== -1) {
			loadIframe.branch(href);
		}
	});
	
	//省切换事件
	$('#province').change(function(){
	    //获取激活页
	    $('#branch').find('.nav-tabs li').each(function(){
	        if($(this).hasClass('active')){
	            var ids = $(this).find('a')[0].hash.replace('#','');
	            $('#'+ids).find('iframe').each(function(){
	                $(this)[0].contentWindow.location.reload(true);
	            });
	        }
	    });
	});
});
	// iframe url配置
	var url = {
		headquarters : {
			hq_index : [ "/rpHomeController/hqHome1", "/rpHomeController/hqHome2", 
			             "/rpHomeController/hqHome3", "/rpHomeController/hqHome4" ],
			             
			hq_BBOOS : [ "/rpBbossController/hqBboss1", "/rpBbossController/hqBboss2", 
			             "/rpBbossController/hqBboss3", "/rpBbossController/hqBboss4", 
			             "/rpBbossController/hqBboss5", "/rpBbossController/hqBboss6", 
			             "/rpBbossController/hqBboss7", "/rpBbossController/hqBboss8",
			             "/rpBbossController/hqBboss9" ],
			             
			hq_timeOut : [ "/rpOrderOvertimeController/hqOrderOvertime1", "/rpOrderOvertimeController/hqOrderOvertime2", 
			               "/rpOrderOvertimeController/hqOrderOvertime3", "/rpOrderOvertimeController/hqOrderOvertime5", 
			               "/rpOrderOvertimeController/hqOrderOvertime4" ],
			               
			hq_error : [ "/rpAbnormalAccountController/hqAbnormalAccount1", "/rpAbnormalAccountController/hqAbnormalAccount2", 
			             "/rpAbnormalAccountController/hqAbnormalAccount3", "/rpAbnormalAccountController/hqAbnormalAccount4", 
			             "/rpAbnormalAccountController/hqAbnormalAccount5" ],
			
			hq_other : [ "/rpOtherGenreController/hqOtherGenre1", "/rpOtherGenreController/hqOtherGenre2", 
			             "/rpOtherGenreController/hqOtherGenre3", "/rpOtherGenreController/hqOtherGenre4", 
			             "/rpOtherGenreController/hqOtherGenre5" ],
			
			hq_showData : [ "/rpBusinessController/hqBusiness1", "/rpBusinessController/hqBusiness2", 
			                "/rpBusinessController/hqBusiness3", "/rpBusinessController/hqBusiness4", 
			                "/rpBusinessController/hqBusiness5", "/rpBusinessController/hqBusiness6", 
			                "/rpBusinessController/hqBusiness7" ]
		},
		branch : { 
			branch_index : [ "./view.html", "./view.html", "./view.html",
					"./view.html", "./view2.html" ],
			branch_BBOOS : [ "./view.html", "./view.html", "./view.html" ],
			branch_timeOut : [ "/rpOrderOvertimeController/peOrderOvertime1", "/rpOrderOvertimeController/peOrderOvertime2", "/rpOrderOvertimeController/peOrderOvertime3"],
			branch_error : [ "/rpAbnormalAccountController/peAbnormalAccount1", "/rpAbnormalAccountController/peAbnormalAccount2", "/rpAbnormalAccountController/peAbnormalAccount3"],
			branch_showData : [ "/rpBusinessController/hqBusiness4?province=123" ]

		},
		ECData : {
			index : [ '/rpBusinessController/hqBusiness4?province=123']
		}
	};
	// 高度配置
	var iframeHeight = {
		headquarters : {
			hq_index : [ "580", "580", "595", "595" ],
			hq_BBOOS : [ "580", "580", "580", "580", "580", "580", "580", "580", "595" ],
			hq_timeOut : [ "580", "580", "170", "580", "767" ],
			hq_error : [ "580", "580", "170","580", "767" ],
			hq_other : [ "580", "170", "580", "580", "595" ],
			hq_showData : [ "580", "580", "580", "580", "580", "580", "595" ]
		},
		branch : { 
			branch_index : [ "580", "580", "580", "580", "769" ],
			branch_BBOOS : [ "580", "580", "580" ],
			branch_timeOut : [ "580", "580", "170" ],
			branch_error : ["580", "580", "595"  ],
			branch_showData : [ "769" ]

		},
		ECData : {
			index : [ '467' ]
		}
	};
	// 版式配置
	var formatConfig = {
		headquarters : {
			hq_index : [ "harfMr2", "harf", "harfMr2", "harf" ],
			hq_BBOOS : [ "harfMr2", "harf", "harfMr2", "harf", "harfMr2", "harf", "harfMr2", "harf", "harfMr2" ],
			hq_timeOut : [ "harfMr2", "harf", "harfAll", "harfMr2", "heightAll" ],
			hq_error : ["harfMr2", "harf", "harfAll", "harfMr2", "heightAll"  ],
			hq_other : [ "harfMr2", "harf", "harf", "harfMr2", "harf" ],
			hq_showData : [ "harfMr2", "harf", "harfMr2", "harf", "harfMr2", "harf", "harfMr2" ]
		},
		branch : { 
			branch_index : [ "harfMr2", "harf", "harfMr2", "harf", "all" ],
			branch_BBOOS : [ "harfMr2", "harf", "all" ],
			branch_timeOut : [ "harfMr2", "harf", "harfMr2" ],
			branch_error : [ "harfMr2", "harf", "harfMr2" ],
			branch_showData : [ "all" ]
		},
		ECData : {
			index : [ 'all' ]
		}
	};
	
	// iframe 加载事件
	var loadIframe = {
		branch : function(id) {
			id = id ? id : "branch_index";
			if (!$('#branch #' + id + ' iframe').hasClass('loaded')) {
				conter = url.branch[id].length;
				n = 0;
				$('.shadeWrap').show();
				$.each(url.branch[id], function(i) {
					var type = formatConfig.branch[id][i];
					var div = $(divTag[type] + divTag.end);
					var iframe = creatIframe();
					iframe.attr('height', iframeHeight.branch[id][i]);
					iframe.on('load', iFrameHeight);
					var halfWidth = Math.floor((Math.floor($('.section').width()) - 20) / 2)-0.5;
					if(type == 'harfAll') {
						harfAllHeight = iframeHeight.headquarters[id][i];
						div.css('margin-right',halfWidth+'px');
					}
					if(type == 'heightAll') {
						div.css('margin-top',-harfAllHeight+'px');
					}
					$(iframe).appendTo(div);
					$(div).appendTo($('#branch #' + id));
					$('.halfContent').width(halfWidth);
					iframe.attr('src', this);
				});
			}

		},
		headquarters : function(id) {
			id = id ? id : "hq_index";
			if (!$('#headquarters #' + id + ' iframe').hasClass('loaded')) {
				conter = url.headquarters[id].length;
				n = 0;
				$('.shadeWrap').show();
				var harfAllHeight = 0;
				$.each(url.headquarters[id], function(i) {
					var type = formatConfig.headquarters[id][i];
					var div = $(divTag[type] + divTag.end);
					var iframe = creatIframe();
					iframe.attr('height', iframeHeight.headquarters[id][i]);
					var halfWidth = Math.floor((Math.floor($('.section').width()) - 20) / 2)-0.5;
					if(type == 'harfAll') {
						harfAllHeight = iframeHeight.headquarters[id][i];
						div.css('margin-right',halfWidth+'px');
					}
					if(type == 'heightAll') {
						div.css('margin-top',-harfAllHeight+'px');
					}
					$(iframe).appendTo(div);
					$(div).appendTo($('#headquarters #' + id));
					$('.halfContent').width(halfWidth);
					iframe.attr('src', this);
					iframe.on('load', iFrameHeight);
				});
			}
		},
		ECData : function() {
			if (!$('#ECData iframe').hasClass('loaded')) {
				conter = url.ECData.index.length;
				n = 0;
				$('.shadeWrap').show();
				$.each(url.ECData.index, function(i) {
					var type = formatConfig.ECData.index[i];
					var div = $(divTag[type] + divTag.end);
					var iframe = creatIframe();
					iframe.attr('height', iframeHeight.ECData.index[i]);
					iframe.attr('src', this);
					iframe.on('load', iFrameHeight);
					$(iframe).appendTo(div);
					$(div).appendTo($('#ECData'));
					$('.halfContent')
							.width(
									Math.floor((Math.floor($('.section')
											.width()) - 20) / 2));
				});
			}
		}
	};
	// 组装iframe标签
	var divTag = {
		harfMr2 : '<div class="content halfContent mr20">',
		harfAll : '<div class="content halfContent">',
		heightAll : '<div class="content halfContent">',
		harf : '<div class="content halfContent">',
		all : '<div class="content allContent">',
		end : '</div>'
	};
	var creatIframe = function() {
		var iframe = $('<iframe>');
		iframe.attr('marginheight', "0");
		iframe.attr('border', "0");
		iframe.attr('frameborder', "0");
		return iframe;
	};
	
	//页面初始化
	//loadIframe.headquarters();
