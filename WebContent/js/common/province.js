var OptionValue;
function setOptionValue(Value){
	OptionValue=Value;
};
var list = '<optgroup label="华南地区">'+
           '<option value="广东">广东</option><option value="广西">广西</option><option value="海南">海南</option>'+
           '</optgroup>'+
           '<optgroup label="华东地区">'+
           '<option value="山东">山东</option><option value="安徽">安徽</option><option value="浙江">浙江</option><option value="江苏">江苏</option><option value="福建">福建</option><option value="上海">上海</option>'+
           '</optgroup>'+
           '<optgroup label="华中地区">'+
           '<option value="湖南">湖南</option><option value="湖北">湖北</option><option value="江西">江西</option><option value="河南">河南</option>'+
           '</optgroup>'+
           '<optgroup label="华北地区">'+
           '<option value="北京">北京</option><option value="天津">天津</option><option value="河北">河北</option><option value="山西">山西</option><option value="内蒙古">内蒙古</option>'+
           '</optgroup>'+
           '<optgroup label="西北地区">'+
           '<option value="宁夏">宁夏</option><option value="新疆">新疆</option><option value="青海">青海</option><option value="陕西">陕西</option><option value="甘肃">甘肃</option>'+
           '</optgroup>'+
           '<optgroup label="西南地区">'+
           '<option value="四川">四川</option><option value="云南">云南</option><option value="贵州">贵州</option><option value="西藏">西藏</option><option value="重庆">重庆</option>'+
           '</optgroup>'+
           '<optgroup label="东北地区">'+
           '<option value="黑龙江">黑龙江</option><option value="辽宁">辽宁</option><option value="吉林">吉林</option>'+
           '</optgroup>'+
           '<optgroup label="港澳台">'+
           '<option value="香港">香港</option><option value="澳门">澳门</option><option value="台湾">台湾</option>'+
           '</optgroup>'+
	           

$(function(){
    	$("#province").append(list);
    });
