$(function() {

	var json = [ {
		"name" : "英语学习",
		"code" : "YYXX",
		"icon" : "icon-th",
		"child" : [ {
			"name" : "单词速记",
			"code" : "English%20study",
			"icon" : "icon-minus-sign",
			"parentCode" : "YYXX",
			"child" : [ {
				"name" : "你好",
				"code" : "hi",
				"icon" : "",
				"parentCode" : "YYXX",
				"child" : []
			} ]
		} ]
	} ];

	function tree(data) {
		for (var i = 0; i < data.length; i++) {
			var data2 = data[i];
			if (data[i].icon == "icon-th") {
				$("#rootUL").append(
						"<li data-name='" + data[i].code + "'><span><i class='"
								+ data[i].icon + "'></i> " + data[i].name
								+ "</span></li>");
			} else {
				var children = $("li[data-name='" + data[i].parentCode + "']")
						.children("ul");
				if (children.length == 0) {
					$("li[data-name='" + data[i].parentCode + "']").append(
							"<ul></ul>");
				}
				$("li[data-name='" + data[i].parentCode + "'] > ul").append(
						"<li data-name='" + data[i].code + "'>"
								+ "<span><a onClick='bdfy(hi)'>" + "<i class='" + data[i].icon
								+ "'></i> " + data[i].name + "</a></span>"
								+ "</li>");
			}
			for (var j = 0; j < data[i].child.length; j++) {
				var child = data[i].child[j];
				var children = $("li[data-name='" + child.parentCode + "']")
						.children("ul");
				if (children.length == 0) {
					$("li[data-name='" + child.parentCode + "']").append(
							"<ul></ul>");
				}
				$("li[data-name='" + child.parentCode + "'] > ul").append(
						"<li data-name='" + child.code + "'>" + "<span>"
								+ "<i class='" + child.icon + "'></i> "
								+ child.name + "</span>" + "</li>");
				var child2 = data[i].child[j].child;
				tree(child2);
			}
			tree(data[i]);
		}

	}

	tree(json);

});
function bdfy(code) {
	console.log('http://fanyi.baidu.com/translate?aldtype=16047#en/zh/'+code);
	
//	$("#baidu")
//			.html(
//					"<iframe  width='100%' height='100%' src='http://fanyi.baidu.com/translate?aldtype=16047#en/zh/"
//							+ t + "'></iframe>");
}
