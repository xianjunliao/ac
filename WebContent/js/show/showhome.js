$(function(){
	$('.nsnav li').on('click','a',function(){
		var index=$(this).parent().index();
		$(this).css('text-decoration','none');
		$(this).addClass('colored').parent().siblings().children('a').removeClass('colored');
		$('.showContent').eq(index).addClass('showed').siblings().removeClass('showed');
	});
	/*滚动条监听事件*/
	
	/*左侧栏折叠菜单*/
	var Accordion = function(el, multiple) {
			this.el = el || {};
			this.multiple = multiple || false;

			// Variables privadas
			var links = this.el.find('.link');
			// Evento
			links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
		}

		Accordion.prototype.dropdown = function(e) {
			var $el = e.data.el;
				$this = $(this),
				$next = $this.next();

			$next.slideToggle();
			$this.parent().toggleClass('open');

			if (!e.data.multiple) {
				$el.find('.sleftmenue li>ul').not($next).slideUp().parent().removeClass('open');
			};
		}	
		var accordion = new Accordion($('.menue-l'), false);
		$('.link').click(function(){
			$(this).addClass('color').parent().siblings().children('a').removeClass('color');
		});
});