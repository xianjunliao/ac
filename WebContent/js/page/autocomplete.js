$(function() {
		$("#searchbox").bigAutocomplete({
			width : 280,
			data : [ {
				title : "中国好声音",
				result : {
					ff : "qq"
				}
			}, {
				title : "中国移动网上营业厅"
			}, {
				title : "中国银行"
			}, {
				title : "中国移动"
			}, {
				title : "中国好声音第三期"
			}, {
				title : "中国好声音 第一期"
			}, {
				title : "中国电信网上营业厅"
			}, {
				title : "中国工商银行"
			}, {
				title : "中国好声音第二期"
			}, {
				title : "中国地图"
			} ],
			callback : function(data) {
				alert(data.title);

			}
		});
	})