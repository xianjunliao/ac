$(function() {
	// 隐藏掉下拉内容
	$(".pullDown").hide();
	var nameText = $(".select input").val();
	// 标识符
	var flag = true;
	// 多选值操作
	$("#selectList").focus(function() {
		if(nameText != ""){
			var array = nameText.split(",");
			for (var int = 0; int < array.length; int++) {
				$(".pullDown li").each(function(index){
					if(array[int] == $.trim($(this).text())){
						$(this).addClass("ck");
					}
				});
			}
		}
		$(".pullDown").slideToggle("slow");
		
	});

	$(document).click(function(e) {
		var e = e || window.event;
		var element = e.target || e.srcElement;
		if ($(element).is("li")) {

		} else if ($(element).attr('id') == "selectList") {

		} else {
			$(".pullDown").slideUp("slow");
			$(".select input").blur();
		}

	});
	$(".pullDown li").click(function() {
		if ($(this).hasClass("ck")) {
			$(this).removeClass("ck");
			// 获取点击的值
			var $nameText = $.trim($(this).text());
			// 获取$nameText中的第一个角色值
			var text = nameText.substr(0, nameText.indexOf(","));
			if ($nameText == text || "" == text) {
				if (nameText.indexOf(",") == -1) {
					nameText = nameText.replace($nameText, "");
				} else {
					nameText = nameText.replace($nameText + ",", "");
				}
			} else {
				nameText = nameText.replace("," + $nameText, "");
			}
		} else {
			$(this).addClass("ck");
			var $nameText = "," + $.trim($(this).text());
			nameText = nameText + $nameText;
		}
		// 获取逗号
		var comma = nameText.substr(0, 1);
		if ("," == comma) {
			nameText = nameText.substr(1);
			flag = false;
		}
		$(".select input").val(nameText);
	});

});