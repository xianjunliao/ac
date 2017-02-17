	$(function() {

		var menuLength = $(".menu").length;
	   if (menuLength <= 5) {

			$(".menus").css("top", "35%");
		}
		else if (menuLength <=15&&menuLength >=6) {

			$(".menus").css("top", "25%");
		}
		else if(menuLength >= 15) {

			$(".menus").css("top", "10%");
		}
		else if (menuLength >= 20) {

			$(".menus").css("top", "7%");
		}else {
			
			$(".menus").css("top", "2%");
		}
		$(".menu.info").hover(function() {
			$(this).css("opacity", "1");
			$(this).css("cursor", "pointer");
		}, function() {
			$(this).css("opacity", "0.5");
		});
	});