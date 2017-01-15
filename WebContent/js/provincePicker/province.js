var OptionValue;
function setOptionValue(Value){
	OptionValue=Value;
};
var list = '<optgroup label="华南地区">'+
           '<option value=广东>广东</option><option>广西</option><option>海南</option>'+
           '</optgroup>'+
           '<optgroup label="华东地区">'+
           '<option>山东</option><option>安徽</option><option>浙江</option><option>江苏</option><option>福建</option><option>上海</option>'+
           '</optgroup>'+
           '<optgroup label="华中地区">'+
           '<option>湖南</option><option>湖北</option><option>江西</option><option>河南</option>'+
           '</optgroup>'+
           '<optgroup label="华北地区">'+
           '<option>北京</option><option>天津</option><option>河北</option><option>山西</option><option>内蒙古</option>'+
           '</optgroup>'+
           '<optgroup label="西北地区">'+
           '<option>宁夏</option><option>新疆</option><option>青海</option><option>陕西</option><option>甘肃</option>'+
           '</optgroup>'+
           '<optgroup label="西南地区">'+
           '<option>四川</option><option>云南</option><option>贵州</option><option>西藏</option><option>重庆</option>'+
           '</optgroup>'+
           '<optgroup label="东北地区">'+
           '<option>黑龙江</option><option>辽宁</option><option>吉林</option>'+
           '</optgroup>'+
           '<optgroup label="港澳台">'+
           '<option>香港</option><option>澳门</option><option>台湾</option>'+
           '</optgroup>'+
	           

$(function(){
    	$("#province").append(list);
    });
