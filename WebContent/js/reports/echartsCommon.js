//公共函数
function randomData() {
    return Math.round(Math.random()*1000);
}
//bootstrap-table config
(function ($) {
    'use strict';
    $.fn.bootstrapTable.locales['zh-CN'] = {
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
            return '总共 ' + totalRows + ' 条记录';
        }
    };
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
})(jQuery);

//快速选择按钮事件
//$(document).on('click','.quiteTime>a',function(){
//    $('.quiteTime>a').removeClass('active');
//    $(this).addClass('active');
//});
var echarts2Color = [
                     '#379bcd','#f9b57d','#92ee7d','#f47d99','#d87a80',
                     '#8d98b3','#e5cf0d','#97b552','#95706d','#dc69aa',
                     '#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050',
                     '#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'
                 ];
/*
 * old
 * [
                     '#2ec7c9','#b6a2de','#5ab1ef','#ffb980','#d87a80',
                     '#8d98b3','#e5cf0d','#97b552','#95706d','#dc69aa',
                     '#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050',
                     '#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'
                 ];
 * 
 * */
// 柱状图
var buildBar = function(options,id){
	var option = {
			grid:{
				top:40,
				bottom:80,
				left:30,
				right:10
			},
			color: echarts2Color,
            xAxis: {            	
            	axisLine:{lineStyle:{color:'#ccd6eb'}},
        		axisTick:{
        			length:10,
        			alignWithLabel:false,
        			lineStyle:{color:'#ccd6eb'}
        		},
        		axisLabel:{textStyle:{color:'#000'}},
        		data:[]
            },
            tooltip:{
            	trigger: 'axis',
            	axisPointer : { 
                    type : 'shadow' 
                }
            },
            yAxis: [
            	{
            		axisLine:{show:false},
            		axisTick:{show:false,},
            		name : '(万笔)',
            		type : 'value',
        		}
            ],
            series: [{
                name: '订单量',
                type: 'bar',
                data:[]
            }]
        };
        if(options) option = $.extend(true,option,options); 
        var charts = echarts.init(document.getElementById(id));
		charts.setOption(option);
		return charts;
};

//饼图
var buildPie = function(options,id){
	var option = {
			color:echarts2Color,
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '50%'],
		            data:[
		                {value:335, name:'直接访问'},
		                {value:310, name:'邮件营销'},
		                {value:234, name:'联盟广告'},
		                {value:135, name:'视频广告'},
		                {value:1548, name:'搜索引擎'}
		            ],
		            label:{
		                normal:{
		                    formatter:"{b}\n {d}%"
		                }  
		            },
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	if(options) option = $.extend(true,option,options); 
	var charts = echarts.init(document.getElementById(id));
	charts.setOption(option);
	return charts;
};

// 中国地图
var buildMap = function(options,id){
var option = {
    tooltip: {
        trigger: 'item'
    },
    // legend: {
    //     orient: 'vertical',
    //     left: 'left',
    //     data:['iphone3','iphone4','iphone5']
    // },
    visualMap: {
        min: 0,
        max: 1500,
        left: 'left',
        top: 'bottom',
        text: ['高','低'],           // 文本，默认为数值文本
        calculable: true,
        inRange:{
            color:['#d0f4fc','#348fe4']
        }
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        }
    },
    series: [
        {
            name: '统计数',
            type: 'map',
            map: 'china',
            roam: false,
            label: {
                normal: {
                    show: true,
                },
                emphasis: {
                    show: true,
                }
            },
            data:[
                {name: '北京',value: randomData() },
                {name: '天津',value: randomData() },
                {name: '上海',value: randomData() },
                {name: '重庆',value: randomData() },
                {name: '河北',value: randomData() },
                {name: '河南',value: randomData() },
                {name: '云南',value: randomData() },
                {name: '辽宁',value: randomData() },
                {name: '黑龙江',value: randomData() },
                {name: '湖南',value: randomData() },
                {name: '安徽',value: randomData() },
                {name: '山东',value: randomData() },
                {name: '新疆',value: randomData() },
                {name: '江苏',value: randomData() },
                {name: '浙江',value: randomData() },
                {name: '江西',value: randomData() },
                {name: '湖北',value: randomData() },
                {name: '广西',value: randomData() },
                {name: '甘肃',value: randomData() },
                {name: '山西',value: randomData() },
                {name: '内蒙古',value: randomData() },
                {name: '陕西',value: randomData() },
                {name: '吉林',value: randomData() },
                {name: '福建',value: randomData() },
                {name: '贵州',value: randomData() },
                {name: '广东',value: randomData() },
                {name: '青海',value: randomData() },
                {name: '西藏',value: randomData() },
                {name: '四川',value: randomData() },
                {name: '宁夏',value: randomData() },
                {name: '海南',value: randomData() },
                {name: '台湾',value: randomData() },
                {name: '香港',value: randomData() },
                {name: '澳门',value: randomData() }
            ]
        }
    ]
};
    if(options) option = $.extend(true,option,options); 
    var myChart1 = echarts.init(document.getElementById(id));
		myChart1.setOption(option);
	return myChart1;
};


//折柱混合;
var buildHyper = function(options,id){
        var option = {
            color:echarts2Color,
            tooltip:{
                trigger: 'axis',
                axisPointer : { 
                    type : 'shadow' 
                }
            },
            legend: {
                data:['举报总量','增长量','增长率'],
                bottom:'1'
            },
            xAxis: {                
                axisLine:{lineStyle:{color:'#ccd6eb'}},
                axisTick:{
                    length:0,
                    alignWithLabel:false,
                    lineStyle:{color:'#ccd6eb'}
                },
                axisLabel:{textStyle:{color:'#000'}},
                data: ['1月','2月','3月','4月']
            },
            yAxis: [
                {
                	axisLine:{show:false},
                    axisTick:{show:false,},
                    type: 'value',
                },
                {
                	axisLine:{show:false},
                    axisTick:{show:false,},
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} %'
                    }
                }
            ],
            series: [
                {
                    name:'举报总量',
                    type:'bar',
                    data:[1000, 1900, 2000, 1500]
                },
                {
                    name:'增长量',
                    type:'bar',
                    data:[-600, -1000, -500, -800]
                },
                {
                    name:'增长率',
                    type:'line',
                    symbol:'circle',
                    yAxisIndex: 1,
                    data:[30, 40, 20, -30]
                }
            ]
        };
if(options) option = $.extend(true,option,options); 
var myChart1 = echarts.init(document.getElementById(id));
	myChart1.setOption(option);
	return myChart1;
};