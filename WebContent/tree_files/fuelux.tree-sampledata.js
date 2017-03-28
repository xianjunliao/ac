var DataSourceTree = function(options) {
	this._data = options.data;
	this._delay = options.delay;
}

DataSourceTree.prototype.data = function(options, callback) {
	var self = this;
	var $data = null;

	if (!("name" in options) && !("type" in options)) {
		$data = this._data;// the root tree
		callback({
			data : $data
		});
		return;
	} else if ("type" in options && options.type == "folder") {
		if ("additionalParameters" in options
				&& "children" in options.additionalParameters)
			$data = options.additionalParameters.children;
		else
			$data = {}// no data
	}

	if ($data != null)// this setTimeout is only for mimicking some random
						// delay
		setTimeout(function() {
			callback({
				data : $data
			});
		}, parseInt(Math.random() * 500) + 200);

	// we have used static data here
	// but you can retrieve your data dynamically from a server using ajax call
	// checkout examples/treeview.html and examples/treeview.js for more info
};
var a = $('#menu1').val();

console.log(a);

var tree_data_2 = {
	'YYXX' : {
		name : '英语学习',
		type : 'folder',
		'icon-class' : 'red'
	},
	'QTXX' : {
		name : '其他学习',
		type : 'folder',
		'icon-class' : 'red'
	}
};
console.log(tree_data_2);

tree_data_2['YYXX']['additionalParameters'] = {
	'children' : {
		'dcsj' : {
			name : '单词速记',
			type : 'folder',
			'icon-class' : 'pink'
		},
		'yfsj' : {
			name : '语法速记',
			type : 'folder',
			'icon-class' : 'pink'
		}
	}
};
tree_data_2['YYXX']['additionalParameters']['children']['dcsj']['additionalParameters'] = {
	'children' : [
			{
				name : '<i class="icon-file-text brown"></i> <a onClick="bdfy('
						+ "'hi'" + ')"> hi:你好</a>',
				type : 'item'
			},
			{
				name : '<i class="icon-file-text brown"></i><a onClick="bdfy('
						+ "'hello'" + ')">  hello:你好</a>',
				type : 'item'
			}, ]
};
tree_data_2['YYXX']['additionalParameters']['children']['yfsj']['additionalParameters'] = {
	'children' : [
			{
				name : '<i class="icon-file-text brown"></i> <a onClick="bdbk('
						+ "'be动词'" + ')"> be动词</a>',
				type : 'item'
			}, ]
};

var treeDataSource2 = new DataSourceTree({
	data : tree_data_2
});

function bdfy(code) {
	var url = 'http://fanyi.baidu.com/translate?aldtype=16047#en/zh/' + code;
	$('#baidu').html(
			'<iframe width="100%" height="100%" src="' + url + '"></iframe>');
}

function bdbk(code) {
	var url = 'http://baike.baidu.com/item/' + code + '?sefr=enterbtn';
	$('#baidu').html(
			'<iframe width="100%" height="100%" src="' + url + '"></iframe>');
}