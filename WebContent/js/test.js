$(function() {

	var json = [ {
		'name' : '英语学习',
		'code' : 'YYXX',
		'icon' : 'icon-th',
		'child' : [ {
			'name' : '单词速记',
			'code' : 'DCSJ',
			'icon' : 'icon-minus-sign',
			'parentCode' : 'YYXX',
			'child' : [ {
				'name' : 'hi',
				'code' : 'hi',
				'icon' : '',
				'parentCode' : 'DCSJ',
				'child' : [{
					'name' : '例句1',
					'code' : 'Hi! How are you doing?',
					'icon' : '',
					'parentCode' : 'hi',
					'child' : []
				}]
			} ,{
				'name' : 'I',
				'code' : 'I',
				'icon' : '',
				'parentCode' : 'DCSJ',
				'child' : []
			} ]
		}, ]
	} ,];

	function tree(data) {
		for (var i = 0; i < data.length; i++) {
			var hi = "'" + data[i].code + "'";
			if (data[i].icon == 'icon-th') {
				$('#rootUL').append(
						'<li data-name=' + data[i].code + '><span><i class='
								+ data[i].icon + '></i> ' + data[i].name
								+ '</span></li>');
			} else {
				var children = $('li[data-name=' + data[i].parentCode + ']')
						.children('ul');
				if (children.length == 0) {
					$('li[data-name=' + data[i].parentCode + ']').append(
							'<ul></ul>');
				}
				$('li[data-name=' + data[i].parentCode + '] > ul').append(
						'<li data-name=' + data[i].code + '>'
								+ '<span><a onClick="bdfy(' + hi + ')">'
								+ '<i class=' + data[i].icon + '></i> '
								+ data[i].name + '</a></span>' + '</li>');
			}
			for (var j = 0; j < data[i].child.length; j++) {
				var child = data[i].child[j];
				var hi2 = "'" +  child.code+ "'";
				var children = $('li[data-name=' + child.parentCode + ']')
						.children('ul');
				if (children.length == 0) {
					$('li[data-name=' + child.parentCode + ']').append(
							'<ul></ul>');
				}
				$('li[data-name=' + child.parentCode + '] > ul').append(
						'<li data-name=' + child.code + '>' + '<span><a onClick="bdfy(' +hi2+ ')">'
								+ '<i class=' + child.icon + '></i> '
								+ child.name + '</a></span>' + '</li>');
				var child2 = data[i].child[j].child;
				tree(child2);
			}
			tree(data[i]);
		}

	}

	tree(json);

});
function bdfy(code) {
	var url = 'http://fanyi.baidu.com/translate?aldtype=16047#en/zh/' + code;
	$('#baidu').html(
			'<iframe width="100%" height="100%" src="' + url + '"></iframe>');
}
