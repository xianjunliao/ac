$(function() {
		$(".changePanel .tab a").mouseover(function() {
			$(this).addClass('on').siblings().removeClass('on');
			var index = $(this).index();
			number = index;
			$('.changePanel .content li').hide();
			$('.changePanel .content li:eq(' + index + ')').show();
		});

		var auto = 1;
		if (auto == 1) {
			var number = 0;
			var maxNumber = $('.changePanel .tab a').length;
			function autotab() {
				number++;
				number == maxNumber ? number = 0 : number;
				$('.changePanel .tab a:eq(' + number + ')').addClass('on')
						.siblings().removeClass('on');
				$('.changePanel .content ul li:eq(' + number + ')').show()
						.siblings().hide();
			}
			var tabChange = setInterval(autotab, 3000);
			//鼠标悬停暂停切换
			$('.changePanel').mouseover(function() {
				clearInterval(tabChange);
			});
			$('.changePanel').mouseout(function() {
				tabChange = setInterval(autotab, 3000);
			});
		}
	});