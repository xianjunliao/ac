<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<!--主要样式-->
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/test.js"></script>

<script type="text/javascript">
	$(function() {
		$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr(
				'title', '关闭');
		$('.tree li.parent_li > span').on(
				'click',
				function(e) {
					var children = $(this).parent('li.parent_li').find(
							' > ul > li');
					if (children.is(":visible")) {
						children.hide('fast');
						$(this).attr('title', '展开').find(' > i').addClass(
								'icon-plus-sign')
								.removeClass('icon-minus-sign');
					} else {
						children.show('fast');
						$(this).attr('title', '关闭').find(' > i').addClass(
								'icon-minus-sign')
								.removeClass('icon-plus-sign');
					}
					e.stopPropagation();
				});

		$("#dj").click(function() {

			$.ajax({
				url : "#", //这里是静态页的地址
				type : "GET", //静态页用get方法，否则服务器会抛出405错误
				dataType : "html",
				success : function(data) {
					$("#baidu").html(data);

				}
			});
		});
	});
</script>
</head>
<body>
	<table border="1" width="100%" height="100%">
		<tr height="5%">
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td width="25%">
				<div class="tree well">

					<ul id="rootUL">

					</ul>
				</div>
			</td>
			<td colspan="2" id="baidu"></td>
		</tr>

	</table>


	<!-- 	<div id="baidu"> -->
			
	<!-- 	</div> -->
</body>
</html>