//var now = new Date($('#currDate',window.parent.document).val()); //当前日期
var now = new Date();
var nowDayOfWeek = now.getDay() == 0 ? 7 : now.getDay();//今天本周的第几天 
var nowDay = now.getDate(); //当前日
var nowMonth = now.getMonth(); //当前月
var nowYear = now.getYear(); //当前年
nowYear += (nowYear < 2000) ? 1900 : 0; //

var lastDate = new Date($('#currDate',window.parent.document).val());
lastDate.setDate(1);
lastDate.setMonth(lastDate.getMonth() - 1);
var lastYear = lastDate.getYear();
var lastMonth = lastDate.getMonth();

//格式化日期：yyyy-MM-dd
function formatDate(date) {
	var myyear = date.getFullYear();
	var mymonth = date.getMonth() + 1;
	var myweekday = date.getDate();

	if (mymonth < 10) {
		mymonth = "0" + mymonth;
	}
	if (myweekday < 10) {
		myweekday = "0" + myweekday;
	}
	return (myyear + "-" + mymonth + "-" + myweekday);
}
function formatMonthDate(date) {
	var myyear = date.getFullYear();
	var mymonth = date.getMonth() + 1;

	if (mymonth < 10) {
		mymonth = "0" + mymonth;
	}
	return (myyear + "-" + mymonth);
}

//获得某月的天数
function getMonthDays(myMonth) {
	var monthStartDate = new Date(nowYear, myMonth, 1);
	var monthEndDate = new Date(nowYear, myMonth + 1, 1);
	var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
	return days;
}

//获得本季度的开始月份
function getQuarterStartMonth() {
	var quarterStartMonth = 0;
	if (nowMonth < 3) {
		quarterStartMonth = 0;
	}
	if (2 < 6) {
		quarterStartMonth = 3;
	}
	if (5 < 9) {
		quarterStartMonth = 6;
	}
	if (nowMonth > 8) {
		quarterStartMonth = 9;
	}
	return quarterStartMonth;
}

//今天
var getCurrentDate = formatDate(new Date(nowYear, nowMonth, nowDay))
var getCurrentMonthDate = formatMonthDate(new Date(nowYear, nowMonth, nowDay))
//昨天
var getYesterdayDate = formatDate(new Date(nowYear, nowMonth, nowDay - 1));
//获得本周的开始日期
var getWeekStartDate = formatDate(new Date(nowYear, nowMonth, nowDay - nowDayOfWeek+1));
//获得本周的结束日期
var getWeekEndDate = formatDate(new Date(nowYear, nowMonth, nowDay));

//获得上周的开始日期
var getUpWeekStartDate = formatDate(new Date(nowYear, nowMonth, nowDay - nowDayOfWeek - 6));
//获得上周的结束日期
var getUpWeekEndDate = formatDate(new Date(nowYear, nowMonth, nowDay - nowDayOfWeek));


var getSeviceDate = {
		twelveDay:function(){  //12天
			return formatDate(new Date(now.getTime() - 11*24*60*60*1000))		
		},
		twelveWeek:function(){ //12周
			var lastWeekStart = new Date(getUpWeekStartDate);
			var nowDayOfWeek = lastWeekStart.getDay() == 0 ? 7 : lastWeekStart.getDay(),
				nowDay = lastWeekStart.getDate(),
				nowMonth = lastWeekStart.getMonth(),
				nowYear = lastWeekStart.getFullYear();
			var lastWeek;
			for(i=0;i<10;i++){
					lastWeek = new Date(new Date(nowYear, nowMonth, nowDay - nowDayOfWeek - 6));
				 	nowDayOfWeek = lastWeek.getDay() == 0 ? 7 : lastWeek.getDay();//今天本周的第几天 
				 	nowDay = lastWeek.getDate(); //当前日
				 	nowMonth = lastWeek.getMonth(); //当前月
				 	nowYear = lastWeek.getFullYear();				
			};
			return formatDate(lastWeek);
		},
		twelveMonth:function(){ //12月
			var surplus = nowMonth -11;
			if(surplus<0){
				surplus += 12;
				return formatMonthDate(new Date(nowYear-1, surplus, 1)); 
			}else{
				return formatMonthDate(new Date(nowYear, surplus, 1));
			}
		}
};


//获得本月的开始日期
var getMonthStartDate = formatDate(new Date(nowYear, nowMonth, 1));

//获得本月的结束日期
var getMonthEndDate = formatDate(new Date(nowYear, nowMonth, getMonthDays(nowMonth)));

//获得上月开始时间
var getLastMonthStartDate = formatDate(new Date(nowYear, lastMonth, 1));

//获得上月结束时间
var getLastMonthEndDate = formatDate(new Date(nowYear, lastMonth, getMonthDays(lastMonth)));