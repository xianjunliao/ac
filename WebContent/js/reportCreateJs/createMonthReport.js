//显示默认时间
function showtime() {
	var endTime = document.getElementById("endDate");
	var date = new Date();
	// alert(date.getMonth());
	endTime.value = date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate();
	getMonday();

}

function getMonday() {
	var today = new Date();
	var weekday = today.getDay();
	var monday = new Date(1000 * 60 * 60 * 24 * (1 - weekday) + today.getTime());
	$("#startDate").val(getDateStr(monday));
}
function getDateStr(dd) {
	var y = dd.getFullYear();

	var m = dd.getMonth() + 1;// 获取当前月份的日期
	m = parseInt(m, 10);
	if (m < 10) {
		m = "0" + m;
	}

	var d = dd.getDate();
	d = parseInt(d, 10);
	if (d < 10) {
		d = "0" + d;
	}
	return y + "-" + m + "-" + d;
}
